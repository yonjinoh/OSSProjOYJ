# 프로젝트명
## 룸메이트 매칭 앱 "끼리끼리"

동국대학교 룸메이트 매칭 안드로이드 애플리케이션(Android Application)


## I. 프로젝트 수행팀 개요

* 수행 학기: 2024년 1학기
* 주제 분류: 자유주제
* 프로젝트명: 동국대학교 기숙사 룸메이트 매칭 시스템 "끼리끼리"  
* Key Words :  룸메이트, 추천, 매칭, 기숙사 생활 만족도, 안드로이드 애플리케이션  
* 팀명: 오예수    
    

구분 | 성명 | 학번 | 소속학과 | 연계전공 | 이메일
------|-------|-------|-------|-------|-------
팀장 | 조예림 | 2020111500 | 회계학과 | 융합소프트웨어 | em514@naver.com         
팀원 | 김수현 | 2019112426 | 산업시스템공학과 | 융합소프트웨어 | kimsteven728@gmail.com         
팀원 | 오연진 | 2020110869 | 경제학과 | 데이터사이언스소프트웨어 | 2020110869@dgu.ac.kr                

* 지도교수: 융합SW교육원 이길섭 교수님, 박효순 교수님       

## Team
|<img src="https://avatars.githubusercontent.com/u/143872214?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/144208568?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/144078388?v=4" width="150" height="150"/>|
|:-:|:-:|:-:|
|오연진[@yonjinoh](https://github.com/yonjinoh) |김수현[@SuHyunKKim](https://github.com/SuHyunKKim)|조예림[@YeRimmm-Cho](https://github.com/YeRimmm-Cho)|
|풀스택 |풀스택, 배포 |풀스택 |



## II. 프로젝트 수행 결과  

### 1. 프로젝트 개요  

- 동국대학교 기숙사 이용 학생들을 위한 성향과 선호도 기반 맞춤 룸메이트 매칭 플랫폼 개발

#### 1.1 개발 동기  

  - 플랫폼의 부재와 매칭 방식의 한계를 개선하기 위해 고도화된 알고리즘으로 룸메이트 매칭 서비스를 제공하는 플랫폼을 구현하고자 함


#### 1.2 개발 목표  

*  동국대학교 기숙사를 이용하는 학생들을 위한 안전하고 효율적인 룸메이트 매칭 서비스 개발
*  최적의 룸메이트 매칭을 통한 학생들의 기숙사 생활 만족도 향상

#### 1.3 최종결과물  

![README-001](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/assets/143872214/b5b5acd9-d33c-422c-a8a4-c71c4d89f875)
- *프로젝트를 통해 개발된 최종 결과물에 대한 설명을 기술*  
- *그림 또는 사진을 제시하고 설명을 기술*  
- *필요한 경우 중간제목을 추가 가능*  

#### 1.4 기대 효과  

| 효과 유형       | 설명                                                                                                             |
|----------------|----------------------------------------------------------------------------------------------------------------|
| **사회적 효과** | - **기숙사 생활의 향상**: 성향이 맞는 룸메이트 매칭을 통해 기숙사 생활의 만족도 증가.<br>- **사회적 네트워킹**: 대학 생활 중 새로운 사회적 연결을 형성. |
| **경제적 효과** | - **B2B 라이센스 모델**: 학교 및 기숙사 매칭을 필요로 하는 기관에 서비스를 제공하여 수익을 창출.<br>- **비용 절감**: 룸메이트 매칭 과정의 자동화로 인한 행정적 수고와 비용 절감. |
| **기술적 효과** | - **알고리즘 고도화**: 최적의 룸메이트 매칭을 위한 고도화된 알고리즘 적용.<br>- **플랫폼 제공**: 차단 및 채팅 기능을 포함한 통일된 플랫폼 제공으로 사용자 경험 향상. |
| **서비스 전략** | - **확장 가능성**: 동국대학교를 포함한 다양한 교육 기관에서 서비스 제공 가능.                                                        |



### 2. 프로젝트 수행 내용  

#### 2.1 프로젝트 진행과정 

##### 2.1.1 프로젝트 진행 일정
[간트차트](https://docs.google.com/spreadsheets/d/1Cg2ZGhDLYc_9B0cQlZq9NR1kXrh4SuzbqTWlH-DBCLs/edit?usp=sharing) 

##### 2.1.2 업무분장

| 역할               | 담당자         | 주요 책임                                    |
|--------------------|----------------|----------------------------------------------|
| **프론트엔드 개발자**  | 오연진, 조예림 | 사용자 인터페이스 설계 및 구현                |
| **백엔드 개발자**      | 김수현, 오연진 | 서버 로직 및 데이터베이스 설계 및 구현         |
| **배포 담당자**        | 김수현 | 배포 파이프라인 구축 및 관리, 배포 자동화 스크립트 작성 |
 **프로젝트 관리자**    | 김수현, 조예림, 오연진 | 프로젝트 계획 수립 및 일정 관리                |

##### 2.1.3 이슈관리
[노션 칸반 보드](https://www.notion.so/4ebfd02a62dd428aad0df3da42971d07?pvs=4)

#### 2.2 프로젝트 구현내용  

- *프로젝트 설계, 구현 등 구현과 관련된 내용을 기술*  

### 3. 프로젝트 자료  

#### 3.1 프로젝트 OSS 구성  

- *프로젝트 오픈소스 구성내역과 상세안내자료 링크를 제시*  

#### 3.2 프로젝트 주요 문서

- [![수행계획서](https://img.shields.io/badge/수행계획서-D5D8F9?style=for-the-badge&logo=Github&logoColor=black)](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Doc/1_1_OSSProj_05_OhYeSu_%EC%88%98%ED%96%89%EA%B3%84%ED%9A%8D%EC%84%9C.md) [![제안발표자료](https://img.shields.io/badge/제안발표자료-D5D8F9?style=for-the-badge&logo=Github&logoColor=black)](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Doc/1_2_OSSProj_05_OhYeSu_%EC%A0%9C%EC%95%88%EB%B0%9C%ED%91%9C%EC%9E%90%EB%A3%8C.pptx)
- [![중간보고서](https://img.shields.io/badge/중간보고서-D0FA58?style=for-the-badge&logo=Github&logoColor=black)](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Doc/2_1_OSSProj_05_OhYeSu_%EC%A4%91%EA%B0%84%EB%B3%B4%EA%B3%A0%EC%84%9C.md) [![중간발표자료](https://img.shields.io/badge/중간발표자료-D0FA58?style=for-the-badge&logo=Github&logoColor=black)](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Doc/2_2_OSSProj_05_OhYeSu_%EC%A4%91%EA%B0%84%EB%B0%9C%ED%91%9C%EC%9E%90%EB%A3%8C.pptx)
- [![최종보고서](https://img.shields.io/badge/최종보고서-F7BE81?style=for-the-badge&logo=Github&logoColor=black)](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Doc/3_1_OSSProj_05_OhYeSu_%EC%B5%9C%EC%A2%85%EB%B3%B4%EA%B3%A0%EC%84%9C.md) [![최종발표자료](https://img.shields.io/badge/최종발표자료-F7BE81?style=for-the-badge&logo=Github&logoColor=black)](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Doc/2_2_OSSProj_05_OhYeSu_%EC%A4%91%EA%B0%84%EB%B0%9C%ED%91%9C%EC%9E%90%EB%A3%8C.pptx)
- [![범위일정이슈관리](https://img.shields.io/badge/범위_일정_이슈관리-81DAF5?style=for-the-badge&logo=Github&logoColor=black)](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Doc/2_1_OSSProj_05_OhYeSu_%EC%A4%91%EA%B0%84%EB%B3%B4%EA%B3%A0%EC%84%9C.md) [![회의록](https://img.shields.io/badge/회의록-81DAF5?style=for-the-badge&logo=Github&logoColor=black)](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Doc/2_2_OSSProj_05_OhYeSu_%EC%A4%91%EA%B0%84%EB%B0%9C%ED%91%9C%EC%9E%90%EB%A3%8C.pptx) [![제품구성배포운영자료](https://img.shields.io/badge/제품_구성_배포_운영_자료-81DAF5?style=for-the-badge&logo=Github&logoColor=black)](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/Doc/2_2_OSSProj_05_OhYeSu_%EC%A4%91%EA%B0%84%EB%B0%9C%ED%91%9C%EC%9E%90%EB%A3%8C.pptx) [![OVERVEIW](https://img.shields.io/badge/OVERVEIW-81DAF5?style=for-the-badge&logo=Github&logoColor=black)](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/blob/main/app/src/overveiw.md)


#### 3.3 참고자료  

✔️설계 구조 참고 프로젝트: [Github - ShareMate](https://github.com/ShareMate)  
✔️도메인 참고 프로젝트 : 
[GitHub - 펀칭](https://github.com/CSID-DGU/2022-2-OSSP1-Exit-1)  


<img src="https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/assets/143872214/f804a6ed-e79e-4093-a23a-be91b800586a" alt="프로젝트 이미지" style="width: 350px; height: auto;">


### 4. 개발 환경 및 기술 스택 


<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"> <img src="https://img.shields.io/badge/Django-092E20?style=for-the-badge&logo=Django&logoColor=white"> <img src="https://img.shields.io/badge/Andriod Studio-3DDC84?style=for-the-badge&logo=Andriod Studio&logoColor=white"> <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=Kotlin&logoColor=white"> <img src="https://img.shields.io/badge/python-3776AB?style=for-the-badge&logo=python&logoColor=white">

<img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"> <img src="https://img.shields.io/badge/amazon-FF9900?style=for-the-badge&logo=amazon&logoColor=white">

<img src="https://img.shields.io/badge/POSTMAN-FF6C37?style=for-the-badge&logo=POSTMAN&logoColor=white"> <img src="https://img.shields.io/badge/Retrofit-00B899?style=for-the-badge&logo=Retrofit&logoColor=white">

<img src="https://img.shields.io/badge/Github-181717?style=for-the-badge&logo=Github&logoColor=white"> <img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white"> <img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=Slack&logoColor=white"> <img src="https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=Figma&logoColor=white">




### 5. 프로젝트 구조
#### 5.1 시스템 구조
![시스템 구조도](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/assets/143872214/bc6d45b7-436e-44be-81b9-0487dd78977d)  

#### 5.2 ERD
![Kiri (2)](https://github.com/CSID-DGU/2024-1-OSSProj-OhYeSu-05/assets/143872214/bd28a217-6ed0-4875-9a4e-d42b7c7277b9)



## 6. 사용 방법
(배포 후 작성)


