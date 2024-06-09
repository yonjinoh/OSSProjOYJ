import json
from channels.generic.websocket import AsyncWebsocketConsumer
from channels.db import database_sync_to_async
from .models import ChatRoom, Chat, AppUser

class ChatConsumer(AsyncWebsocketConsumer):
    async def connect(self):
        self.room_id = self.scope['url_route']['kwargs']['room_id']
        self.room_group_name = f'chat_{self.room_id}'

        print(f"Connecting to WebSocket: room_id={self.room_id}, room_group_name={self.room_group_name}")

        await self.channel_layer.group_add(
            self.room_group_name,
            self.channel_name
        )
        await self.accept()
        print(f"Connected to WebSocket: room_id={self.room_id}, room_group_name={self.room_group_name}")

    async def disconnect(self, close_code):
        await self.channel_layer.group_discard(
            self.room_group_name,
            self.channel_name
        )
        print(f"Disconnected from WebSocket: room_id={self.room_id}, room_group_name={self.room_group_name}")

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
        chat_room = ChatRoom.objects.get(HistoryID=CHistoryID)
        sender = AppUser.objects.get(iD=senderID)
        receiver = AppUser.objects.get(iD=receiverID)
        chat = Chat.objects.create(
            CHistoryID=chat_room,
            senderID=sender,
            receiverID=receiver,
            content=content
        )
        chat.save()
        print(f"Message saved: {chat}")
