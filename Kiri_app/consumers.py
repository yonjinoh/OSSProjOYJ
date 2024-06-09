import json
from channels.generic.websocket import AsyncWebsocketConsumer
from channels.db import database_sync_to_async

class ChatConsumer(AsyncWebsocketConsumer):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        from .models import ChatRoom, AppUser, Chat  # 여기에 모델 임포트

    async def connect(self):
        self.room_id = self.scope['url_route']['kwargs']['room_id']
        self.room_group_name = f'chat_{self.room_id}'

        await self.channel_layer.group_add(
            self.room_group_name,
            self.channel_name
        )

        await self.accept()

    async def disconnect(self, close_code):
        await self.channel_layer.group_discard(
            self.room_group_name,
            self.channel_name
        )

    async def receive(self, text_data):
        print(f"Received message: {text_data}")
        data = json.loads(text_data)
        CHistoryID = data['CHistoryID']
        senderID = data['senderID']
        receiverID = data['receiverID']
        content = data['content']
        await self.save_message(CHistoryID, senderID, receiverID, content)
        await self.channel_layer.group_send(
            self.room_group_name,
            {
                'type': 'chat_message',
                'CHistoryID': CHistoryID,
                'senderID': senderID,
                'receiverID': receiverID,
                'content': content
            }
        )

    async def chat_message(self, event):
        print(f"Sending message: {event}")
        await self.send(text_data=json.dumps({
            'CHistoryID': event['CHistoryID'],
            'senderID': event['senderID'],
            'receiverID': event['receiverID'],
            'content': event['content']
        }))

    @database_sync_to_async
    def save_message(self, CHistoryID, senderID, receiverID, content):
        from .models import ChatRoom, AppUser, Chat  # 여기에 모델 임포트
        chat_room = ChatRoom.objects.get(HistoryID=CHistoryID)
        sender = AppUser.objects.get(iD=senderID)
        receiver = AppUser.objects.get(userID=receiverID)
        chat = Chat.objects.create(
            CHistoryID=chat_room,
            senderID=sender,
            receiverID=receiver,
            content=content
        )
        chat.save()