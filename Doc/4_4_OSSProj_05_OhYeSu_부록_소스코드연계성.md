# 기능별 연계성 설명

## 목차
1. [회원가입 기능 연계성](#1-회원가입-기능-연계성)
2. [로그인 기능 연계성](#2-로그인-기능-연계성)
3. [매칭 기능 연계성](#3-매칭-기능-연계성)
4. [채팅 기능 연계성](#4-채팅-기능-연계성)
5. [차단 기능 연계성](#5-차단-기능-연계성)
6. [신고 기능 연계성](#6-신고-기능-연계성)
7. [사용자 프로필/사용자 선호도 수정 기능 연계성](#7-사용자-프로필-사용자-선호도-수정-기능-연계성)
8. [로그아웃 기능](#8-로그아웃-기능)

---

## 1. 회원가입 기능 연계성

### 개요
회원가입 기능은 사용자가 필요한 정보를 입력하고, 서버와 통신하여 계정을 생성하는 기능을 포함합니다. 주요 연계성은 다음과 같습니다:

1. 사용자 입력 처리
2. 서버와의 통신
3. 응답 처리
4. UI 레이아웃 정의
5. 백엔드 처리

### 연계성 설명

#### 사용자 입력 처리

- **[SignupActivity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/sign/SignupActivity.kt)**
  - 사용자가 이름, 이메일, 비밀번호, 학번, 성별 등의 정보를 입력하고 회원가입 버튼을 클릭하면 서버에 요청을 보냅니다.

#### 서버와의 통신

- **[ApiFactory.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/entitiy/ApiFactory.kt)**
  - Retrofit을 사용하여 회원가입 API와 통신합니다. 다양한 서비스 객체들을 생성 및 관리합니다.
- **[KiriService.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/service/KiriService.kt)**
  - 회원가입 및 로그인 관련 Retrofit 인터페이스를 정의합니다.

#### 응답 처리

- **[signuprequest.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/request/signuprequest.kt)**
  - 서버로 전송되는 회원가입 요청 데이터를 정의합니다.
- **[signupresponse.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/response/signupresponse.kt)**
  - 서버로부터 받는 회원가입 응답 데이터를 정의합니다.

#### UI 레이아웃 정의

- **[activity_register.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_register.xml)**
  - 회원가입 화면의 레이아웃을 정의합니다. 사용자 입력 필드와 버튼이 포함되어 있습니다.

#### 백엔드 처리

- **[views.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/views.py)**
  - 회원가입 요청을 처리하는 Django 뷰를 정의합니다.
- **[models.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/models.py)**
  - 사용자 모델을 정의합니다.
- **[serializers.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/serializers.py)**
  - 회원가입 요청 데이터를 직렬화/역직렬화하는 시리얼라이저를 정의합니다.
- **[urls.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/urls.py)**
  - 회원가입 관련 엔드포인트를 정의합니다.

### 참고한 원천 코딩
- 새로운 코딩: [SignupActivity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/sign/SignupActivity.kt)
- 참고한 원천 코딩: [펀칭 프로젝트 SignupActivity.kt](https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/blob/main/app/src/main/java/com/example/testapplication/sign/SignupActivity.kt)

---

## 2. 로그인 기능 연계성

### 개요
로그인 기능은 사용자가 이메일과 비밀번호를 입력하여 서버와 통신하고, 사용자 인증을 수행하는 기능을 포함합니다. 주요 연계성은 다음과 같습니다:

1. 사용자 입력 처리
2. 서버와의 통신
3. 응답 처리
4. UI 레이아웃 정의
5. 백엔드 처리

### 연계성 설명

#### 사용자 입력 처리

- **[LoginActivity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/sign/LoginActivity.kt)**
  - 사용자가 이메일과 비밀번호를 입력하고 로그인 버튼을 클릭하면 서버에 요청을 보냅니다.

#### 서버와의 통신

- **[ApiFactory.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/entitiy/ApiFactory.kt)**
  - Retrofit을 사용하여 로그인 API와 통신합니다. 다양한 서비스 객체들을 생성 및 관리합니다.
- **[KiriService.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/service/KiriService.kt)**
  - 회원가입 및 로그인 관련 Retrofit 인터페이스를 정의합니다.

#### 응답 처리

- **[loginrequest.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/request/loginrequest.kt)**
  - 서버로 전송되는 로그인 요청 데이터를 정의합니다.
- **[loginresponse.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/response/loginresponse.kt)**
  - 서버로부터 받는 로그인 응답 데이터를 정의합니다.

#### UI 레이아웃 정의

- **[activity_login.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_login.xml)**
  - 로그인 화면의 레이아웃을 정의합니다. 이메일과 비밀번호 입력 필드, 로그인 버튼이 포함되어 있습니다.

#### 백엔드 처리

- **[views.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/views.py)**
  - 로그인 요청을 처리하는 Django 뷰를 정의합니다.
- **[models.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/models.py)**
  - 사용자 모델을 정의합니다.
- **[serializers.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/serializers.py)**
  - 로그인 요청 데이터를 직렬화/역직렬화하는 시리얼라이저를 정의합니다.
- **[urls.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/urls.py)**
  - 로그인 관련 엔드포인트를 정의합니다.

### 참고한 원천 코딩
- 일부 참고
- 새로운 코딩: [LoginActivity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/sign/LoginActivity.kt)
- 참고한 원천 코딩: [펀칭 프로젝트 LoginActivity.kt](https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/blob/main/app/src/main/java/com/example/testapplication/sign/LoginActivity.kt)

---

## 3. 매칭 기능 연계성

### 개요
매칭 기능은 사용자가 선호 조건을 입력하고, 서버와 통신하여 룸메이트를 매칭하는 기능을 포함합니다. 주요 연계성은 다음과 같습니다:

1. 사용자 입력 처리
2. 서버와의 통신
3. 결과 UI 업데이트
4. UI 레이아웃 정의
5. 백엔드 처리

### 연계성 설명

- [ProfileOption1Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/profile/ProfileOption1Activity.kt), [ProfileOption2Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/profile/ProfileOption2Activity.kt), [ProfileOption3Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/profile/ProfileOption3Activity.kt)
  - 사용자가 프로필을 입력받는 액티비티입니다. 

#### 사용자 선호도 입력 처리

- **[MatchingOption1Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/match/MatchingOption1Activity.kt)**, **[MatchingOption2Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/match/MatchingOption2Activity.kt)**, **[MatchingOption3Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/match/MatchingOption3Activity.kt)**, **[MatchingOption4Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/match/MatchingOption4Activity.kt)**
  - 사용자로부터 선호 조건을 입력받는 액티비티입니다.

#### 서버와의 통신

- **[ApiFactory.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/entitiy/ApiFactory.kt)**
  - Retrofit을 사용하여 매칭 관련 API와 통신합니다. 다양한 서비스 객체들을 생성 및 관리합니다.
- **[KiriService.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/service/KiriService.kt)**
  - 매칭 관련 Retrofit 인터페이스를 정의합니다.

#### 응답 처리

- **[MatchingRequest.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/request/MatchRequest.kt)**
  - 서버로 전송되는 매칭 요청 데이터를 정의합니다.
- **[MatchingResponse.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/response/MatchResponse.kt)**
  - 서버로부터 받는 매칭 응답 데이터를 정의합니다.


#### UI 업데이트

- **[MatchingViewModel.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/viewmodel/MatchingViewModel.kt)**
  - 매칭 결과를 LiveData로 관리하여 UI에 반영합니다.
- **[MatchingFragment.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/match/MatchingFragment.kt)**
  - 매칭 프로필을 표시하고 UI를 업데이트합니다.

#### UI 레이아웃 정의

- **[activity_matching_ing.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_matching_ing.xml)**
  - 매칭 알고리즘이 진행되는 동안 뜨는 화면을 정의합니다. 
- **[fragment_matching.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/fragment_matching.xml)**
  - 매칭 결과를 표시하는 프래그먼트의 레이아웃을 정의합니다.

#### 백엔드 처리

- **[views.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/views.py)**
  - 매칭 요청을 처리하는 Django 뷰를 정의합니다.
  - 매칭 알고리즘 코드가 정의되어있습니다. 
- **[models.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/models.py)**
  - 매칭 관련 모델을 정의합니다.
- **[serializers.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/serializers.py)**
  - 매칭 요청 데이터를 직렬화/역직렬화하는 시리얼라이저를 정의합니다.
- **[urls.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/urls.py)**
  - 매칭 관련 엔드포인트를 정의합니다.

### 참고한 원천 코딩
- 일부 참고
- 새로운 코딩: [MatchingOption1Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/match/MatchingOption1Activity.kt)
- 참고한 원천 코딩: [펀칭 프로젝트 MatchingOptionActivity.kt](https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/blob/main/app/src/main/java/com/example/testapplication/matching/MatchingOptionActivity.kt)

---

## 4. 채팅 기능 연계성

### 개요
채팅 기능은 사용자 간의 메시지 전송 및 수신을 처리합니다. 주요 연계성은 다음과 같습니다:

1. 메시지 전송
2. 메시지 수신
3. UI 업데이트
4. UI 레이아웃 정의
5. 백엔드 처리

### 연계성 설명

#### 메시지 전송

- **[ChatActivity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/chat/ChatActivity.kt)**
  - 사용자가 메시지를 입력하고 전송하는 액티비티입니다.

#### 메시지 수신

- **[WebSocketManager.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/websocket/WebsocketManager.kt)**
  - WebSocket을 통해 실시간으로 메시지를 수신합니다.

#### 서버와의 통신

- **[ApiFactory.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/entitiy/ApiFactory.kt)**
  - Retrofit을 사용하여 채팅 관련 API와 통신합니다. 다양한 서비스 객체들을 생성 및 관리합니다.
- **[KiriService.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/service/KiriService.kt)**
  - 채팅 관련 Retrofit 인터페이스를 정의합니다.

#### UI 업데이트

- **[ChatRoomViewModel.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/viewmodel/ChatRoomViewModel.kt)**
  - 메시지 목록을 LiveData로 관리하여 UI에 반영합니다.

#### UI 레이아웃 정의

- **[activity_chat.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_chat.xml)**
  - 채팅 화면의 레이아웃을 정의합니다. 메시지 입력 필드와 전송 버튼, 메시지 목록이 포함되어 있습니다.

#### 백엔드 처리

- **[views.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/views.py)**
  - 채팅 요청을 처리하는 Django 뷰를 정의합니다.
- **[models.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/models.py)**
  - 채팅 관련 모델을 정의합니다.
- **[serializers.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/serializers.py)**
  - 채팅 요청 데이터를 직렬화/역직렬화하는 시리얼라이저를 정의합니다.
- **[urls.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/urls.py)**
  - 채팅 관련 엔드포인트를 정의합니다.
- **[consumers.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/consumers.py)**
  - Django Channel을 사용하여 Websocket 통신을 처리하는 ChatConsumer클래스를 정의합니다.
- **[routing.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/routing.py)**
  - Django Channels를 사용하여 실시간 채팅을 처리하는 Websocket 경로 설정합니다.
- **[asgi.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_prj/wsgi.py)**
  - HTTP 요청과 Websocket 요청을 구분하고 라우팅을 수행합니다.

### 참고한 원천 코딩
- 새로 작성

---

## 5. 차단 기능 연계성

### 개요
차단 기능은 사용자가 특정 사용자를 차단하거나 차단 해제하는 기능을 포함합니다. 주요 연계성은 다음과 같습니다:

1. 사용자 입력 처리
2. 서버와의 통신
3. 응답 처리
4. UI 업데이트
5. UI 레이아웃 정의
6. 백엔드 처리

### 연계성 설명

#### 사용자 입력 처리

- **[BlockUserActivity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/chat/BlockUserActivity.kt)**
  - 사용자가 특정 사용자를 차단하는 액티비티입니다.

#### 서버와의 통신

- **[ApiFactory.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/entitiy/ApiFactory.kt)**
  - Retrofit을 사용하여 차단 관련 API와 통신합니다. 다양한 서비스 객체들을 생성 및 관리합니다.
- **[KiriService.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/service/KiriService.kt)**
  - 차단 관련 Retrofit 인터페이스를 정의합니다.

#### 응답 처리

- **[BlockData.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/request/BlockData.kt)**
  - 서버로 전송되는 차단 요청 데이터를 정의합니다.
- **[BlockResponse.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/response/BlockResponse.kt)**
  - 서버로부터 받는 차단 응답 데이터를 정의합니다.


#### UI 레이아웃 정의

- **[activity_block_user.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_block_user.xml)**
  - 사용자 차단 화면의 레이아웃을 정의합니다.


#### 백엔드 처리

- **[views.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/views.py)**
  - 차단 요청을 처리하는 Django 뷰를 정의합니다.
- **[models.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/models.py)**
  - 차단 관련 모델을 정의합니다.
- **[serializers.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/serializers.py)**
  - 차단 요청 데이터를 직렬화/역직렬화하는 시리얼라이저를 정의합니다.
- **[urls.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/urls.py)**
  - 차단 관련 엔드포인트를 정의합니다.

### 참고한 원천 코딩
- 직접 코딩

## 6. 신고 기능 연계성

### 개요
신고 기능은 사용자가 특정 사용자를 신고하여 부적절한 행동을 알리는 기능을 포함합니다. 주요 연계성은 다음과 같습니다:

1. 사용자 입력 처리
2. 서버와의 통신
3. 응답 처리
4. UI 레이아웃 정의
5. 백엔드 처리

### 연계성 설명

#### 사용자 입력 처리

- **[ReportUserActivity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/chat/ReportUserActivity.kt)**
  - 사용자가 신고할 사용자의 ID와 신고 내용을 입력받아 서버로 전송합니다.

#### 서버와의 통신

- **[ApiFactory.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/entitiy/ApiFactory.kt)**
  - Retrofit을 사용하여 신고 관련 API와 통신합니다. 다양한 서비스 객체들을 생성 및 관리합니다.
- **[KiriService.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/service/KiriService.kt)**
  - 신고 관련 Retrofit 인터페이스를 정의합니다.

#### 응답 처리

- **[ReportData.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/request/ReportData.kt)**
  - 서버로 전송되는 신고 요청 데이터를 정의합니다.
- **[ReportResponse.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/response/ReportResponse.kt)**
  - 서버로부터 받는 신고 응답 데이터를 정의합니다.

#### UI 레이아웃 정의

- **[activity_report_user.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_report_user.xml)**
  - 신고 화면의 레이아웃을 정의합니다. 사용자 입력 필드와 버튼이 포함되어 있습니다.

#### 백엔드 처리

- **[views.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/views.py)**
  - 신고 요청을 처리하는 Django 뷰를 정의합니다.
- **[models.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/models.py)**
  - 신고 관련 모델을 정의합니다.
- **[serializers.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/serializers.py)**
  - 신고 요청 데이터를 직렬화/역직렬화하는 시리얼라이저를 정의합니다.
- **[urls.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/urls.py)**
  - 신고 관련 엔드포인트를 정의합니다.

### 참고한 원천 코딩
- 직접 코딩

---

## 7. 사용자 프로필 / 사용자 선호도 수정 기능 연계성

### 개요
사용자 프로필과 선호도 수정 기능은 사용자가 자신의 프로필 정보를 수정하거나 선호도를 입력하고 저장하는 기능을 포함합니다. 주요 연계성은 다음과 같습니다:

1. 사용자 입력 처리
2. 서버와의 통신
3. 응답 처리
4. UI 레이아웃 정의
5. 백엔드 처리

### 연계성 설명

#### 사용자 입력 처리

- **[ProfileOption1Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/profile/ProfileOption1Activity.kt), [ProfileOption2Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/profile/ProfileOption2Activity.kt), [ProfileOption3Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/profile/ProfileOption3Activity.kt), [ProfileOption4Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/profile/ProfileOption4Activity.kt)**
  - 사용자가 프로필과 선호도를 입력하고 수정할 수 있는 옵션을 제공합니다.

#### 서버와의 통신

- **[ApiFactory.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/entitiy/ApiFactory.kt)**
  - Retrofit을 사용하여 프로필과 선호도 관련 API와 통신합니다. 다양한 서비스 객체들을 생성 및 관리합니다.
- **[KiriService.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/service/KiriService.kt)**
  - 프로필과 선호도 관련 Retrofit 인터페이스를 정의합니다.

#### 응답 처리

- **[profilerequest.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/request/profilerequest.kt), [roommaterequest.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/request/roommaterequest.kt)**
  - 서버로 전송되는 프로필과 선호도 요청 데이터를 정의합니다.
- **[profileresponse.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/response/profileresponse.kt), [roommateresponse.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/response/roommateresponse.kt)**
  - 서버로부터 받는 프로필과 선호도 응답 데이터를 정의합니다.

#### UI 레이아웃 정의

- **[activity_profile_option_1.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_profile_option_1.xml), [activity_profile_option_2.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_profile_option_2.xml), [activity_profile_option_3.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_profile_option_3.xml), [activity_profile_option_4.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_profile_option_4.xml)**
  - 프로필과 선호도 입력 화면의 레이아웃을 정의합니다.

#### 백엔드 처리

- **[views.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/views.py)**
  - 프로필과 선호도 생성/수정 요청을 처리하는 Django 뷰를 정의합니다.
- **[models.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/models.py)**
  - 프로필과 선호도 모델을 정의합니다.
- **[serializers.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/serializers.py)**
  - 프로필과 선호도 요청 데이터를 직렬화/역직렬화하는 시리얼라이저를 정의합니다.
- **[urls.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/urls.py)**
  - 프로필과 선호도 관련 엔드포인트를 정의합니다.

### 참고한 원천 코딩
- 새로운 코딩: [ProfileOption1Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/profile/ProfileOption1Activity.kt)
- 참고한 원천 코딩: [펀칭 프로젝트 ProfileOption1Activity.kt](https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/blob/main/app/src/main/java/com/example/testapplication/matching/MatchingOptionActivity.kt)

---

## 8. 로그아웃 기능

### 개요
로그아웃 기능은 사용자가 현재 세션을 종료하고, 로그인 화면으로 돌아가는 기능을 포함합니다. 주요 연계성은 다음과 같습니다:

1. 사용자 입력 처리
2. 서버와의 통신
3. UI 업데이트
4. 백엔드 처리

### 연계성 설명

#### 사용자 입력 처리

- **[MainActivity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/MainActivity.kt)**
  - 사용자가 로그아웃 버튼을 클릭하면 로그아웃 요청을 서버로 전송합니다.

#### 서버와의 통신

- **[ApiFactory.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/entitiy/ApiFactory.kt)**
  - Retrofit을 사용하여 로그아웃 API와 통신합니다. 다양한 서비스 객체들을 생성 및 관리합니다.
- **[KiriService.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/service/KiriService.kt)**
  - 로그아웃 관련 Retrofit 인터페이스를 정의합니다.

#### UI 업데이트

- **[MainActivity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/MainActivity.kt)**
  - 로그아웃 성공 시 로그인 화면으로 이동합니다.

#### 백엔드 처리

- **[views.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/views.py)**
  - 로그아웃 요청을 처리하는 Django 뷰를 정의합니다.
- **[models.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/models.py)**
  - 세션 관리를 위한 모델을 정의합니다.
- **[serializers.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/serializers.py)**
  - 로그아웃 요청 데이터를 직렬화/역직렬화하는 시리얼라이저를 정의합니다.
- **[urls.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/urls.py)**
  - 로그아웃 관련 엔드포인트를 정의합니다.

### 참고한 원천 코딩
- 새로운 코딩: [MainActivity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/MainActivity.kt)
- 참고한 원천 코딩: [펀칭 프로젝트 MainActivity.kt](https://github.com/CSID-DGU/2023-1-OSSP1-criminal6-1/blob/main/app/src/main/java/com/example/testapplication/MainActivity.kt)
