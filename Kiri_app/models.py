# -*- coding: utf-8 -*-

from django.db import models
from django.utils import timezone

# Create your models here.
#class 모델 클래스를 설정하면 데이터베이스에 테이블이 생성된다.



#Room 모델 정의
class Room(models.Model):
    roomID = models.AutoField(primary_key = True)
    title = models.CharField(max_length = 45, default = '')
    roomIntro = models.TextField(max_length = 200, null=True)
    date = models.CharField(max_length = 45)
    region = models.CharField(max_length = 45, default = '')
    genre = models.IntegerField(default = 0)
    difficulty = models.FloatField(default = 0)
    fear = models.FloatField(default = 0)
    activity = models.FloatField(default = 0)
    #필드 정의 (방의 속성을 나타냄)

    def __str__(self):
        return str(self.roomID)

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
#Chat 모델 정의
class Chat(models.Model):
    chatID = models.IntegerField()
    senderID = models.ForeignKey(AppUser, on_delete=models.CASCADE,related_name='chats')
    content = models.TextField()
    createAT = models.DateTimeField()
    roomId = models.ForeignKey(Room, on_delete=models.CASCADE,related_name='chats')

    def __str__(self):
        return self.chatID


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
    blockId = models.AutoField(primarg_key = True)
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
