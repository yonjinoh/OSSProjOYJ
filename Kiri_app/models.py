# -*- coding: utf-8 -*-

from django.db import models
from django.utils import timezone

# Create your models here.
#class 모델 클래스를 설정하면 데이터베이스에 테이블이 생성된다.





#AppUser 모델 정의
class AppUser(models.Model):
    userID = models.AutoField(primary_key = True)
    id = models.CharField(max_length = 45, unique = True)
    password = models.CharField(max_length = 45)
    name = models.CharField(max_length = 45, default = '')
    # 학번 추가.
    studentId = models.CharField(max_length = 45, default ='')
    roomID = models.CharField(max_length = 1000, default = '')

    isProfile = models.BooleanField(default = False) # 사용자 정보 입력 여부
    isUserPref = models.BooleanField(default = False) # 선호도 정보 입력 여부
    isRestricted = models.BooleanField(default = False) # 플랫폼 차단 여부 추가 (False: 차단 안됨, True: 차단됨)

    def __str__(self):
        return self.name


# KSH : 채팅방 모델 정의
class ChatRoom(models.Model):
    user1 = models.ForeignKey(AppUser, related_name='chatrooms_user1', on_delete=models.CASCADE)
    # User - 매치 결과의 유저로 수정 필요
    user2 = models.ForeignKey(User, related_name='chatrooms_user2', on_delete=models.CASCADE)
    created_at = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return f"ChatRoom between {self.user1.username} and {self.user2.username}"

# KSH : 채팅 모델 정의
class Chat(models.Model):
    chatroom = models.ForeignKey(ChatRoom, on_delete=models.CASCADE, related_name='chats')
    sender = models.ForeignKey(AppUser, on_delete=models.CASCADE, related_name='senders')
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return f"Chat {self.id} in {self.chatroom.id}"



# KSH: Profile 모델 정의 추가
class Profile(models.Model):
    profileId = models.AutoField(primary_key = True)
    userId = models.ForeignKey(AppUser, on_delete=models.CASCADE, related_name='profiles')
    # default를 각각 E,S,T,J 로 설정
    Embti = models.BooleanField(default = True)
    Smbti = models.BooleanField(default = True)
    Tmbti = models.BooleanField(default = True)
    Jmbti = models.BooleanField(default = True)

    firstLesson = models.BooleanField()
    smoke = models.BooleanField()
    sleepHabit = models.BooleanField()
    grade = models.IntegerField()
    shareNeeds = models.BooleanField()
    inComm = models.BooleanField()
    heatSens = models.BooleanField()
    coldSens = models.BooleanField()
    drinkFreq = models.IntegerField()
    cleanliness = models.IntegerField()
    noiseSens = models.IntegerField()
    sleepSche = models.IntegerField()

    def __str__(self):
        return self.userId

# KSH: UserPref 모델 정의 추가
class UserPref(models.Model):
    prefId = models.AutoField(primary_key = True)
    UuserId = models.ForeignKey(AppUser, on_delete=models.CASCADE, related_name='userprefs')
    # default를 'E' : True로 설정
    Umbti = models.BooleanField(default = True)
    UfirstLesson = models.BooleanField()
    Usmoke = models.BooleanField()
    UsleepHabit = models.BooleanField()
    Ugrade = models.IntegerField()
    UshareNeeds = models.BooleanField()
    UinComm = models.BooleanField()
    UheatSens = models.BooleanField()
    UcoldSens = models.BooleanField()
    UdrinkFreq = models.IntegerField()
    Ucleanliness = models.IntegerField()
    UnoiseSens = models.IntegerField()
    UsleepSche = models.IntegerField()

    def __str__(self):
        return self.UserId

# KSH: Matching 모델 정의 추가
class Match(models.Model):
    matchId = models.AutoField(primary_key = True)
    userId = models.ForeignKey(AppUser, on_delete=models.CASCADE, related_name='matchings')
    matchScore = models.FloatField()
    matchStatus = models.CharField(max_length = 45, default = 'pending')
    createdAt = models.DateTimeField(default = timezone.now)
    updateAt = models.DateTimeField(default = timezone.now)
    # related_name 참조 변경 필요
    userId1 = models.ForeignKey(AppUser, on_delete=models.CASCADE, related_name='matchings1')
    userId2 = models.ForeignKey(AppUser, on_delete=models.CASCADE, related_name='matchings2')
    userId3 = models.ForeignKey(AppUser, on_delete=models.CASCADE, related_name='matchings3')


    def __str__(self):
        return self.matchId

# KSH: Message 모델 정의 추가

# KSH: ChatHistory 모델 정의 추가



# KSH: Report 모델 정의 추가
class Report(models.Model):
    reportId = models.AutoField(primary_key = True)
    reporterId = models.ForeignKey(AppUser, on_delete=models.CASCADE, related_name='reports')
    reason = models.TextField()
    timestamp = models.DateTimeField(default = timezone.now)
    # reporterId = models.ForeignKey(AppUser, on_delete=models.CASCADE, related_name='reporters')
    # Chat 모델에서 포린키 가져와야함 수정 필요
    # reportedId = models.ForeignKey(AppUser, on_delete=models.CASCADE, related_name='reporters')

    def __str__(self):
        return self.reportId


# KSH: Block 모델 정의 추가
class Block (models.Model):
    blockId = models.AutoField(primary_key = True)
    timestamp = models.DateTimeField(default = timezone.now)
    blockerId = models.ForeignKey(AppUser, on_delete=models.CASCADE, related_name='blocks')
    # Chat 모델에서 포린키 가져와야함 수정 필요
    blockedId = models.ForeignKey(AppUser, on_delete=models.CASCADE, related_name='blockers')







    # userId = models.ForeignKey(AppUser, on_delete=models.CASCADE, related_name='blocks')
    # timestamp = models.DateTimeField(default = timezone.now)
    # blockerId = models.ForeignKey(AppUser, on_delete=models.CASCADE, related_name='blockers')
    # # Chat 모델에서 포린키 가져와야함 수정 필요
    # blockedId = models.ForeignKey(AppUser, on_delete=models.CASCADE, related_name='blockers')

    def __str__(self):
        return self.blockId
