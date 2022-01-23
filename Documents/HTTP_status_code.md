## 목차
### [2XX](#2XX)
#### [200 OK](#200_OK)

#### [201 Created](#201-Created)

#### [204 No Content](#204_No_Content)

### [4XX](#4XX)

#### [400 Bad Request](#400 Bad Request)

#### [401 Unauthorized](#401_Unauthorized)
#### [403 Forbidden](#403_Forbidden)
#### [404 Not Found](#404_Not_Found#)


---
---


## 2XX
### 200 OK
> 200 OK
> 클라이언트의 요청을 서버가 정상적으로 처리했다.

### 201 Created
> 201 Created
> 클라이언트의 요청을 서버가 정상적으로 처리했고 새로운 리소스가 생성했다.

성공과 동시에 새로운 리소스가 생성되었다는 의미
`post` , `put` 요청의 응답으로 많이 사용

### 204 No Content
> 204 No Content
> 클라이언트의 요청은 정상적이다. 하지만 컨텐츠를 제공하지 않는다.

더 이상 응답할 컨텐츠가 없기 때문에 컨텐츠가 없는 204로 응답
ex) Delete

---
## 4XX
### 400 Bad Request

> 400 Bad Request
>
> 클라이언트 오류로 인해 서버가 요청을 처리할 수 없거나 처리하지 않을 것임을 나타낸다.

예: 잘못된 요청 구문, 잘못된 요청 메시지 프레이밍 또는 기만적인 요청 라우팅

### 401 Unauthorized

> 401 Unauthorized 
> 유효한 권한이 없기 때문에 요청이 적용되지 않았음을 나타낸다.

비인증 상태를 뜻한다

### 403 Forbidden
> 403 Forbidden
> 클라이언트가 권한이 없기 때문에 작업을 진행할 수 없는 경우

관리자 권한이 없는 경우 사용

### 404 Not Found
> 404 Not Found
> 클라이언트가 요청한 자원이 존재하지 않다.
> 
> 경로가 존재하지 않거나 자원이 존재하지 않는 경우

입력한 값이 존재하지 않을시

### 406 Not Acceptable

> 406 Not Acceptable
>
> 서버 주도 콘텐츠 협상 후, 헤더 에 정의된 허용 가능한 값 목록과 일치하는 응답을 생성할 수 없으며 서버가 기본 표현을 제공할 의사가 없음을 나타낸다.

정보 수정 실패시


### Reference
[HTTPStatus](https://developer.mozilla.org/ko/docs/Web/HTTP/Status)