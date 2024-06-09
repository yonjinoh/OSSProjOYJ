import json
from channels.generic.websocket import AsyncWebsocketConsumer
from django.contrib.auth import get_user_model
from channels.db import database_sync_to_async
from .models import ChatRoom, Chat, AppUser

class ChatConsumer(AsyncWebsocketConsumer):
    async def connect(self):
        self.user_id = self.scope['url_route']['kwargs']['user_id']
        self.receiver_id = self.scope['url_route']['kwargs']['receiver_id']

        self.chat_room = await self.get_chat_room(self.user_id, self.receiver_id)

        if not self.chat_room:
            await self.close()

        await self.accept()


    async def disconnect(self, close_code):
        await self.channel_layer.group_discard(
            self.room_group_name,
            self.channel_name
        )


    async def receive(self, text_data):
        data = json.loads(text_data)
        CHistoryID = data['CHistoryID']
        senderID = data['senderID']
        receiverID = data['receiverID']
        content = data['content']

        # 데이터베이스에 메세지 저장
        await self.save_message(CHistoryID, senderID, receiverID, content)

        # receiver에게 메세지 전송
        await self.send(text_data=json.dumps({
            'CHistoryID': CHistoryID,
            'message': content,
            'senderID': senderID,
            'receiverID': receiverID
        }))


    @database_sync_to_async
    def save_message(self, CHistoryID, senderID, receiverID, content):
        CHistoryID = ChatRoom.objects.get(HistoryID=CHistoryID)

        senderID = AppUser.objects.get(iD=senderID)
        senderID = senderID.iD

        receiverID = AppUser.objects.get(iD=receiverID)
        receiverID = receiverID.iD

        content = content

        chat_count = Chat.objects.count() + 1
        chat = Chat.objects.create(
            messageID = chat_count,
            CHistoryID=CHistoryID,
            senderID=senderID,
            receiverID=receiverID,
            content=content
        )
        chat.save()
