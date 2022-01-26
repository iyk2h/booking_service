# 학교 시설 예약 프로그램

- ## 프로젝트 추진 배경

현재 교내에서의 여러 시설물의 예약 관리시스템의 대부분이 수기로 작성되어 진행되어 소프트웨어로 대체



- ## 최종 목표 및 세부 목표

RESTApi 형식으로 구현 -> Web api 형식으로 구현되어 추후 버전 2.0 으로 구현 예정

Representational State Transfer의 약자로 원(resource)의 표현(representation)에 의한 상태(정보)를 주고받을 것
HTTP 프로토콜을 그대로 활용해 웹의 장점을 최대한 활용할 수 있는 아키텍처 스타일로 구현할 것
네트워크 상에서 Client와 Server 사이의 통신 방식을 익힐 것



- ## 기능

#### [사용자]

[사용자] 회원가입 / 로그인 / 정보 조회 / 정보 수정 /회원 탈퇴 
[시설] 조회(main home)
[시설 예약] 예약 신청 / 예약 조회  / 예약 취소 / 시간별 예약 가능 보기 / 회원 별 예약 목록 보기

#### [관리자]

[관리자] 관리자등록 / 로그인 / 정보 조회 / 정보 수정 / 탈퇴
[시설 관리] 조회 / 등록 / 수정 / 삭제 / 시설별 예약 리스트 보기
[예약 관리] 예약 조회  / 예약 취소
[사용자 관리] 사용자 리스트 보기, 사용자별 예약 리스트 보기, 사용자 정보 보기, 사용자 정보 수정, 사용자 탈퇴 



- ## 개발 환경

#### [Back-end ]

- vscode
- Spring Boot 2.5.4
- Java 11
- Maven
- Jar packages
- PostgreSQL

#### [[Front-end]](https://github.com/iyk2h/booking_service_front)

- vscode
- React



- ## 명세서



#### [사용자 예약 로직 명세서](../masterd/Documents/student_booking_api.md)

사용자 로그인, 로그아웃, 예약 가능 리스트 보기, 예약하기, 사용자가 예약한 목록 보기, 사용자가 예약한 예약 삭제



#### [사용자 로직 명세서](../master/Documents/student_api.md)

로그인, 로그아웃, 회원가입, 회원 id로 조회, 회원 정보 수정, 회원 탈퇴





