# OverView

## 목차
1. [회원가입 기능 연계성](#1-회원가입-기능-연계성)
2. [로그인 기능 연계성](#2-로그인-기능-연계성)
3. [매칭 기능 연계성](#3-매칭-기능-연계성)
4. [채팅 기능 연계성](#4-채팅-기능-연계성)
5. [차단 기능 연계성](#5-차단-기능-연계성)
6. [사용자 관리 기능 연계성](#6-사용자-관리-기능-연계성)

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
- 일부 참고
- 새로운 코딩: [SignupActivity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/sign/SignupActivity.kt)
- 참고한 원천 코딩: [펀칭 프로젝트 SignupActivity.kt](https://github.com/punching-project-url/path/to/SignupActivity.kt)

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
- 참고한 원천 코딩: [펀칭 프로젝트 LoginActivity.kt](https://github.com/punching-project-url/path/to/LoginActivity.kt)

---

## 3. 매칭 기능 연계성

### 개요
매칭 기능은 사용자가 선호 조건을 입력하고, 서버와 통신하여 룸메이트를 매칭하는 기능을 포함합니다. 주요 연계성은 다음과 같습니다:

1. 사용자 입력 처리
2. 서버와의 통신
3. UI 업데이트
4. UI 레이아웃 정의
5. 백엔드 처리

### 연계성 설명

#### 사용자 입력 처리

- **[MatchingOption1Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/match/MatchingOption1Activity.kt)**
  - 사용자로부터 선호 조건을 입력받는 액티비티입니다.

#### 서버와의 통신

- **[ApiFactory.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/entity/ApiFactory.kt)**
  - Retrofit을 사용하여 매칭 관련 API와 통신합니다. 다양한 서비스 객체들을 생성 및 관리합니다.
- **[KiriService.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/service/KiriService.kt)**
  - 매칭 관련 Retrofit 인터페이스를 정의합니다.

#### UI 업데이트

- **[MatchingViewModel.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/viewmodel/MatchingViewModel.kt)**
  - 매칭 결과를 LiveData로 관리하여 UI에 반영합니다.
- **[MatchingFragment.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/match/MatchingFragment.kt)**
  - 매칭 프로필을 표시하고 UI를 업데이트합니다.

#### UI 레이아웃 정의

- **[activity_matching_option1.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_matching_option1.xml)**
  - 매칭 옵션 입력 화면의 레이아웃을 정의합니다.
- **[fragment_matching.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/fragment_matching.xml)**
  - 매칭 결과를 표시하는 프래그먼트의 레이아웃을 정의합니다.

#### 백엔드 처리

- **[views.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/views.py)**
  - 매칭 요청을 처리하는 Django 뷰를 정의합니다.
- **[models.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/models.py)**
  - 매칭 관련 모델을 정의합니다.
- **[serializers.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/serializers.py)**
  - 매칭 요청 데이터를 직렬화/역직렬화하는 시리얼라이저를 정의합니다.
- **[urls.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/urls.py)**
  - 매칭 관련 엔드포인트를 정의합니다.

### 참고한 원천 코딩
- 일부 참고
- 새로운 코딩: [MatchingFragment.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/match/MatchingFragment.kt)
- 참고한 원천 코딩: [펀칭 프로젝트 MatchingFragment.kt](https://github.com/punching-project-url/path/to/MatchingFragment.kt)

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

- **[WebSocketManager.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/websocket/WebSocketManager.kt)**
  - WebSocket을 통해 실시간으로 메시지를 수신합니다.

#### UI 업데이트

- **[ChatViewModel.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/viewmodel/ChatViewModel.kt)**
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

- **[ApiFactory.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/entity/ApiFactory.kt)**
  - Retrofit을 사용하여 차단 관련 API와 통신합니다. 다양한 서비스 객체들을 생성 및 관리합니다.
- **[KiriService.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/service/KiriService.kt)**
  - 차단 관련 Retrofit 인터페이스를 정의합니다.

#### 응답 처리

- **[blockrequest.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/request/blockrequest.kt)**
  - 서버로 전송되는 차단 요청 데이터를 정의합니다.
- **[blockresponse.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/model/response/blockresponse.kt)**
  - 서버로부터 받는 차단 응답 데이터를 정의합니다.

#### UI 업데이트

- **[BlockListActivity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/websocket/BlockListActivity.kt)**
  - 차단된 사용자 목록을 표시하고 관리하는 화면입니다.

#### UI 레이아웃 정의

- **[activity_block_user.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_block_user.xml)**
  - 사용자 차단 화면의 레이아웃을 정의합니다.
- **[activity_block_list.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_block_list.xml)**
  - 차단된 사용자 목록을 표시하는 화면의 레이아웃을 정의합니다.

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
- 새로 작성

---

## 6. 사용자 관리 기능 연계성

### 개요
사용자 관리 기능은 사용자 프로필 생성, 수정 및 삭제를 처리합니다. 주요 연계성은 다음과 같습니다:

1. 프로필 생성
2. 프로필 수정
3. 프로필 삭제
4. UI 레이아웃 정의
5. 백엔드 처리

### 연계성 설명

#### 프로필 생성

- **[ProfileOption1Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/profile/ProfileOption1Activity.kt)**
  - 사용자가 프로필을 생성하는 옵션을 입력받는 액티비티입니다.

#### 프로필 수정

- **[ProfileOption2Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/profile/ProfileOption2Activity.kt)**
  - 사용자가 기존 프로필을 수정하는 액티비티입니다.

#### 프로필 삭제

- **[ProfileOption3Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/profile/ProfileOption3Activity.kt)**
  - 사용자가 프로필을 삭제하는 액티비티입니다.

#### UI 레이아웃 정의

- **[activity_profile_option1.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_profile_option1.xml)**
  - 프로필 생성 화면의 레이아웃을 정의합니다.
- **[activity_profile_option2.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_profile_option2.xml)**
  - 프로필 수정 화면의 레이아웃을 정의합니다.
- **[activity_profile_option3.xml](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/res/layout/activity_profile_option3.xml)**
  - 프로필 삭제 화면의 레이아웃을 정의합니다.

#### 백엔드 처리

- **[views.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/views.py)**
  - 프로필 생성/수정/삭제 요청을 처리하는 Django 뷰를 정의합니다.
- **[models.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/models.py)**
  - 사용자 프로필 모델을 정의합니다.
- **[serializers.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/serializers.py)**
  - 프로필 요청 데이터를 직렬화/역직렬화하는 시리얼라이저를 정의합니다.
- **[urls.py](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Kiri_app/urls.py)**
  - 프로필 관련 엔드포인트를 정의합니다.

### 참고한 원천 코딩
- 일부 참고
- 새로운 코딩: [ProfileOption1Activity.kt](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/main/java/com/example/mytestapp/profile/ProfileOption1Activity.kt)
- 참고한 원천 코딩: [펀칭 프로젝트 ProfileOption1Activity.kt](https://github.com/punching-project-url/path/to/ProfileOption1Activity.kt)
