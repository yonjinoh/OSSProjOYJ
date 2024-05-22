from django.shortcuts import render, redirect
# Create your views here.
from rest_framework import viewsets
from .serializers import UserSerializer, ChatRoomSerializer, ChatSerializer
# 시리얼라이저 profile, userpref, match, report, block 추가
from .serializers import ProfileSerializer, UPrefSerializer, MatchSerializer, ReportSerializer, BlockSerializer
from .models import AppUser, ChatRoom, Chat
# 모델 profile, userpref, match, report, block 추가
from .models import Profile, UserPref, Match, Report, Block
from rest_framework import status
from rest_framework.decorators import api_view
from django.http.response import HttpResponse
from rest_framework.response import Response
from django.contrib import auth

from django.contrib.auth.models import User
from django.contrib.auth import authenticate,login
from django.contrib.sessions.models import Session
import numpy as np,random


# from django.contrib.auth import authenticate
#확인
class UserViewSet(viewsets.ModelViewSet):
    queryset = AppUser.objects.all()
    serializer_class = UserSerializer

    #회원가입 시 호출되는 함수
    @api_view(['POST'])
    def signup(request):
        id = request.data.get('id')
        password = request.data.get('password')
        name = request.data.get('name')
        # 학번 추가
        studentid = request.data.get('studentId')
        #회원가입시 아이디, 비번, 이름 등록

        # studentid 추가
        if id and password and name and studentid:
            try:
                # Check if a user with the provided username already exists
                existing_user = AppUser.objects.get(id = id)
                return Response({'success': 'False'}, status=status.HTTP_400_BAD_REQUEST)
            except AppUser.DoesNotExist:
                # Create a new user
                user_count = AppUser.objects.count() + 1
                # studentid 추가
                user = AppUser.objects.create(userID = user_count,id = id, password=password, name = name, studentId = studentid)
                room_count = Room.objects.count()
                roomID = '0' * room_count
                user.roomID = roomID
                user.save()
                return Response({'success': True}, status=status.HTTP_201_CREATED)
        else:
            return Response({'success': False}, status=status.HTTP_400_BAD_REQUEST)


    #로그인 시 호출되는 함수
    @api_view(['POST'])
    def login_api(request):
        id = request.data.get('id')
        password = request.data.get('password')

        if id and password:
            try:
                # 로그인 성공
                existing_user = AppUser.objects.get(id=id, password=password)
                user_id = existing_user.id
                request.session['user_id'] = user_id
                user_id1=request.session.get('user_id')
                print(user_id1)
                return Response({'success': True, 'user_id': user_id}, status=status.HTTP_200_OK)
            except AppUser.DoesNotExist:
                #로그인 실패
                return Response({'success': False}, status=status.HTTP_400_BAD_REQUEST)
        else:
            return Response({'success': False}, status=status.HTTP_400_BAD_REQUEST)

    @api_view(['POST'])
    def get_user_id(request):
        # 세션에서 사용자 ID 가져오기
        user_id = request.session.get('user_id')
        print(user_id)
        if user_id:
            return Response({'user_id': user_id}, status=status.HTTP_200_OK)
        else:
            return Response({'success': False}, status=status.HTTP_404_NOT_FOUND)

    # KSH : 로그아웃 기능 구현 예정
    @api_view(['POST'])
    def logout(request):
        request.session.flush()
        return Response({'success': True}, status=status.HTTP_200_OK)




# 채팅방 생성
class ChatRoomListViewSet(viewsets.ModelViewSet):
    queryset = ChatRoom.objects.all()
    serializer_class = ChatRoomSerializer

# 채팅 내역 저장 - 보낼때마다 저장
# 채팅 내역 불러오기

class ChatViewSet(viewsets.ModelViewSet):
    queryset = Chat.objects.all()
    serializer_class = ChatSerializer




# KSH : ProfileViewSet 추가
class ProfileViewSet(viewsets.ModelViewSet):
    queryset = Profile.objects.all()
    serializer_class = ProfileSerializer

    @api_view(['POST'])
    def profilecreate(request):
        userId = request.data.get('userId')

        Embti = request.data.get('Embti')
        Smbti = request.data.get('Smbti')
        Tmbti = request.data.get('Tmbti')
        Jmbti = request.data.get('Jmbti')

        firstLesson = request.data.get('firstLesson')
        smoke = request.data.get('smoke')
        sleepHabit = request.data.get('sleepHabit')
        grade = request.data.get('grade')
        shareNeeds = request.data.get('shareNeeds')
        inComm = request.data.get('inComm')
        heatSens = request.data.get('heatSens')
        coldSens = request.data.get('coldSens')
        drinkFreq = request.data.get('drinkFreq')
        cleanliness = request.data.get('cleanliness')
        noiseSens = request.data.get('noiseSens')
        sleepSche = request.data.get('sleepSche')

        if userId and Embti and Smbti and Tmbti and Jmbti and firstLesson and smoke and sleepHabit and grade and shareNeeds and inComm and heatSens and coldSens and drinkFreq and cleanliness and noiseSens and sleepSche:

            user_count = Profile.objects.count() + 1
            user_profile = Profile.objects.create(profileId = user_count, userId = userId,
                                                  Embti = Embti,
                                                  Smbti = Smbti,
                                                  Tmbti = Tmbti,
                                                  Jmbti = Jmbti,
                                                  firstLesson = firstLesson,
                                                  smoke = smoke,
                                                  sleepHabit = sleepHabit,
                                                  grade = grade,
                                                  shareNeeds = shareNeeds,
                                                  inComm = inComm,
                                                  heatSens = heatSens,
                                                  coldSens = coldSens,
                                                  drinkFreq = drinkFreq,
                                                  cleanliness = cleanliness,
                                                  noiseSens = noiseSens,
                                                  sleepSche = sleepSche)
            user_profile.save()

            user = AppUser.objects.get(userID = userId)
            user.isProfile = True
            user.save()

            return Response({'success': True}, status=status.HTTP_201_CREATED)
        else:
            return Response({'success': False}, status=status.HTTP_400_BAD_REQUEST)

    @api_view(['PATCH'])
    def profileupdate(request):
        userId = request.data.get('userId')

        Embti = request.data.get('Embti')
        Smbti = request.data.get('Smbti')
        Tmbti = request.data.get('Tmbti')
        Jmbti = request.data.get('Jmbti')

        firstLesson = request.data.get('firstLesson')
        smoke = request.data.get('smoke')
        sleepHabit = request.data.get('sleepHabit')
        grade = request.data.get('grade')
        shareNeeds = request.data.get('shareNeeds')
        inComm = request.data.get('inComm')
        heatSens = request.data.get('heatSens')
        coldSens = request.data.get('coldSens')
        drinkFreq = request.data.get('drinkFreq')
        cleanliness = request.data.get('cleanliness')
        noiseSens = request.data.get('noiseSens')
        sleepSche = request.data.get('sleepSche')

        if userId and Embti and Smbti and Tmbti and Jmbti and firstLesson and smoke and sleepHabit and grade and shareNeeds and inComm and heatSens and coldSens and drinkFreq and cleanliness and noiseSens and sleepSche:

            user_profile = Profile.objects.get(userId = userId)
            user_profile.Embti = Embti
            user_profile.Smbti = Smbti
            user_profile.Tmbti = Tmbti
            user_profile.Jmbti = Jmbti
            user_profile.firstLesson = firstLesson
            user_profile.smoke = smoke
            user_profile.sleepHabit = sleepHabit
            user_profile.grade = grade
            user_profile.shareNeeds = shareNeeds
            user_profile.inComm = inComm
            user_profile.heatSens = heatSens
            user_profile.coldSens = coldSens
            user_profile.drinkFreq = drinkFreq
            user_profile.cleanliness = cleanliness
            user_profile.noiseSens = noiseSens
            user_profile.sleepSche = sleepSche
            user_profile.save()

            return Response({'success': True}, status=status.HTTP_200_OK)
        else:
            return Response({'success': False}, status=status.HTTP_400_BAD_REQUEST)

# KSH : UserPrefViewSet 추가
class UserPrefViewSet(viewsets.ModelViewSet):
    queryset = UserPref.objects.all()
    serializer_class = UPrefSerializer

    @api_view(['POST'])
    def userprefcreate(request):
        UuserId = request.data.get('UuserId')

        Umbti = request.data.get('Umbti')
        UfirstLesson = request.data.get('UfirstLesson')
        Usmoke = request.data.get('Usmoke')
        UsleepHabit = request.data.get('UsleepHabit')
        Ugrade = request.data.get('Ugrade')
        Ugrade = request.data.get('Ugrade')
        UshareNeeds = request.data.get('UshareNeeds')
        UinComm = request.data.get('UinComm')
        UheatSens = request.data.get('UheatSens')
        UcoldSens = request.data.get('UcoldSens')
        UdrinkFreq = request.data.get('UdrinkFreq')
        Ucleanliness = request.data.get('Ucleanliness')
        UnoiseSens = request.data.get('UnoiseSens')
        UsleepSche = request.data.get('UsleepSche')

        if UuserId and Umbti and UfirstLesson and Usmoke and UsleepHabit and Ugrade and UshareNeeds and UinComm and UheatSens and UcoldSens and UdrinkFreq and Ucleanliness and UnoiseSens and UsleepSche:
            user_count = UserPref.objects.count() + 1
            user_pref = UserPref.objects.create(prefId = user_count, UuserId = UuserId,
                                                Umbti = Umbti,
                                                UfirstLesson = UfirstLesson,
                                                Usmoke = Usmoke,
                                                UsleepHabit = UsleepHabit,
                                                Ugrade = Ugrade,
                                                UshareNeeds = UshareNeeds,
                                                UinComm = UinComm,
                                                UheatSens = UheatSens,
                                                UcoldSens = UcoldSens,
                                                UdrinkFreq = UdrinkFreq,
                                                Ucleanliness = Ucleanliness,
                                                UnoiseSens = UnoiseSens,
                                                UsleepSche = UsleepSche)
            user_pref.save()

            user = AppUser.objects.get(userID = UuserId)
            user.isUserPref = True
            user.save()

            return Response({'success': True}, status=status.HTTP_201_CREATED)
        else:
            return Response({'success': False}, status=status.HTTP_400_BAD_REQUEST)

    @api_view(['PATCH'])
    def userprefupdate(request):
        UuserId = request.data.get('UuserId')

        Umbti = request.data.get('Umbti')
        UfirstLesson = request.data.get('UfirstLesson')
        Usmoke = request.data.get('Usmoke')
        UsleepHabit = request.data.get('UsleepHabit')
        Ugrade = request.data.get('Ugrade')
        UshareNeeds = request.data.get('UshareNeeds')
        UinComm = request.data.get('UinComm')
        UheatSens = request.data.get('UheatSens')
        UcoldSens = request.data.get('UcoldSens')
        UdrinkFreq = request.data.get('UdrinkFreq')
        Ucleanliness = request.data.get('Ucleanliness')
        UnoiseSens = request.data.get('UnoiseSens')
        UsleepSche = request.data.get('UsleepSche')

        if UuserId and Umbti and UfirstLesson and Usmoke and UsleepHabit and Ugrade and UshareNeeds and UinComm and UheatSens and UcoldSens and UdrinkFreq and Ucleanliness and UnoiseSens and UsleepSche:
            user_pref = UserPref.objects.get(UuserId = UuserId)
            user_pref.Umbti = Umbti
            user_pref.UfirstLesson = UfirstLesson
            user_pref.Usmoke = Usmoke
            user_pref.UsleepHabit = UsleepHabit
            user_pref.Ugrade = Ugrade
            user_pref.UshareNeeds = UshareNeeds
            user_pref.UinComm = UinComm
            user_pref.UheatSens = UheatSens
            user_pref.UcoldSens = UcoldSens
            user_pref.UdrinkFreq = UdrinkFreq
            user_pref.Ucleanliness = Ucleanliness
            user_pref.UnoiseSens = UnoiseSens
            user_pref.UsleepSche = UsleepSche
            user_pref.save()

            return Response({'success': True}, status=status.HTTP_200_OK)
        else:
            return Response({'success': False}, status=status.HTTP_400_BAD_REQUEST)


# KSH :  MatchViewSet 추가
class MatchViewSet(viewsets.ModelViewSet):
    queryset = Match.objects.all()
    serializer_class = MatchSerializer




# KSH : ReportViewSet 추가
class ReportViewSet(viewsets.ModelViewSet):
    queryset = Report.objects.all()
    serializer_class = ReportSerializer

    @api_view(['POST'])
    def reportuser(request):
        reporterId = request.data.get('reporterId')
        reason = request.data.get('reason')
        timestamp = request.data.get('timestamp')
        reportedId = request.data.get('reportedId')

        if reporterId and reason and timestamp and reportedId:
            report_count = Report.objects.count() + 1
            report = Report.objects.create(reportId = report_count, reporterId = reporterId, reason = reason, timestamp = timestamp, reportedId = reportedId)
            report.save()
            return Response({'success': True}, status=status.HTTP_201_CREATED)
        else:
            return Response({'success': False}, status=status.HTTP_400_BAD_REQUEST)



# KSH : BlockViewSet 추가
class BlockViewSet(viewsets.ModelViewSet):
    queryset = Block.objects.all()
    serializer_class = BlockSerializer

    # KSH : blockuser 함수에 차단목록 추가/삭제 기능 한곳에 구현함
    @api_view(['POST', 'DELETE'])
    def blockuser(request):
        if request.method == 'POST':
            timestamp = request.data.get('timestamp')
            blockerId = request.data.get('blockerId')
            blockedId = request.data.get('blockedId')

            if timestamp and blockerId and blockedId:
                block_count = Block.objects.count() + 1
                block = Block.objects.create(blockId = block_count, timestamp = timestamp, blockerId = blockerId, blockedId = blockedId)
                block.save()
                return Response({'success': True}, status=status.HTTP_201_CREATED)
            else:
                return Response({'success': False}, status=status.HTTP_400_BAD_REQUEST)
        elif request.method == 'DELETE':
            blockerId = request.data.get('blockerId')
            blockedId = request.data.get('blockedId')

            if blockerId and blockedId:
                try:
                    block = Block.objects.get(blockerId = blockerId, blockedId = blockedId)
                    block.delete()
                    return Response({'success': True}, status=status.HTTP_200_OK)
                except Block.DoesNotExist:
                    return Response({'success': False}, status=status.HTTP_404_NOT_FOUND)
            else:
                return Response({'success': False}, status=status.HTTP_400_BAD_REQUEST)
    @api_view(['GET'])
    def getblocklist(request):
        blockerId = request.query_params.get('blockerId')  # 쿼리 파라미터에서 'blockerId'를 가져옵니다.

        if blockerId:
            block_list = Block.objects.filter(blockerId=blockerId)
            serializer = BlockSerializer(block_list, many=True)  # 시리얼라이저를 사용하여 쿼리셋을 JSON으로 변환합니다.
            return Response({'block_list': serializer.data}, status=status.HTTP_200_OK)  # 변환된 데이터를 응답합니다.
        else:
            return Response({'success': False}, status=status.HTTP_400_BAD_REQUEST)