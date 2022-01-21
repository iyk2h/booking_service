### [HTTPStatus 응답 명세서](./HTTP_status_code.md)

---

### 로그인

```http
POST http://127.0.0.1:8000/students/login HTTP/1.1
Content-Type: application/json

{
  "sid":2,
  "pw":"pwd"
}
```

```http
HTTP/1.1 200 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Set-Cookie: SID=889163EA29EE8EBC56326816612E1099; Max-Age=2592000; Expires=Fri, 18-Feb-2022 06:15:38 GMT; Path=/; HttpOnly
Content-Type: application/json
Content-Length: 6
Date: Wed, 19 Jan 2022 06:15:38 GMT
Connection: close

성공
```



### 로그아웃

``` http
GET http://127.0.0.1:8000/students/logout HTTP/1.1
```

``` http
HTTP/1.1 204 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Date: Fri, 21 Jan 2022 06:41:04 GMT
Connection: close
```
[예외. 로그인 확인](#예외.-로그인-확인)



### main 홈

```http
GET http://127.0.0.1:8000/booking HTTP/1.1
```

```http
HTTP/1.1 200 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 19 Jan 2022 06:14:06 GMT
Connection: close

[
  {
    "fno": 1,
    "name": "name",
    "place": "pwd",
    "scale": "user"
  },
  {
    "fno": 2,
    "name": "name2",
    "place": "pwd",
    "scale": "user"
  },
...
]
```

[예외 예약 목록이 없는 경우](#예외-예약-목록이-없는-경우)



### 시설 별 예약 리스트 보기

``` http
GET http://127.0.0.1:8000/booking/2 HTTP/1.1
Content-Type: application/json
```

``` http
HTTP/1.1 200 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 21 Jan 2022 07:15:36 GMT
Connection: close

[
  {
    "bno": 34,
    "fno": 4,
    "sid": 123123,
    "startTime": "2022-01-20 14:00",
    "endTime": "2022-01-20 14:59",
    "date": null,
    "selectedTime": null
  },
  ...
]

```

[예외. 시설 입력 잘못했을 경우](#예외-시설-입력-잘못했을-경우)

[예외 예약 목록이 없는 경우](#예외-예약-목록이-없는-경우)




### 특정 날짜 예약 리스트 보기  

``` http
POST http://127.0.0.1:8000/booking/2/date HTTP/1.1
Content-Type: application/json

{
  "date	":"2022-01-01"
}
```

``` http
HTTP/1.1 201 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 19 Jan 2022 06:16:53 GMT
Connection: close

[
  {
    "bno": 13,
    "fno": 2,
    "sid": 2,
    "startTime": "2022-01-01 00:00",
    "endTime": "2022-01-01 00:59",
    "date": null,
    "selectedTime": null
  },
  {
    "bno": 9,
    "fno": 2,
    "sid": 2,
    "startTime": "2022-01-01 01:00",
    "endTime": "2022-01-01 01:59",
    "date": null,
    "selectedTime": null
  },
...
]

```

[예외. 시설 입력 잘못했을 경우](#예외-시설-입력-잘못했을-경우)

[예외 예약 목록이 없는 경우](#예외-예약-목록이-없는-경우)

[예외. 시간 입력 형태가 잘못된 경우](#예외.-시간-입력-00시-보다-적은-시간,시간-형태가-잘못된-경우)



### 예약하기 

``` http
POST http://127.0.0.1:8000/booking/2 HTTP/1.1
Content-Type: application/json

{
  "date":"2022-01-01",
  "selectedTime":0
}
```

#### 예약 성공

```http
HTTP/1.1 201 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 19 Jan 2022 06:21:10 GMT
Connection: close

{
  "bno": 14,
  "fno": 2,
  "sid": 2,
  "startTime": "2022-01-01 14:00",
  "endTime": "2022-01-01 14:59",
  "date": null,
  "selectedTime": null
}

```

[예외. 로그인 확인](#예외.-로그인-확인)

[예외. 시간 입력 잘못했을 경우](#예외.-시간-입력-잘못했을-경우)

[예외. 시설 입력 잘못했을 경우](#예외-시설-입력-잘못했을-경우)

[예외. 시간 입력 00시 보다 적은 시간,시간 형태가 잘못된 경우](#예외.-시간-입력-00시-보다-적은-시간,시간-형태가-잘못된-경우)

[예외. 시간 입력 24시 보다 큰 시간](#예외.-시간-입력-24시-보다-큰-시간)




### 사용자 자신이 예약한 리스트 보기

``` http
GET http://127.0.0.1:8000/booking/students HTTP/1.1
Content-Type: application/json
```

``` http
HTTP/1.1 200 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 19 Jan 2022 06:43:55 GMT
Connection: close

[
  {
    "bno": 6,
    "fno": 2,
    "sid": 2,
    "startTime": "2022-01-16 02:00",
    "endTime": "2022-01-16 03:00",
    "date": null,
    "selectedTime": null
  },
  {
    "bno": 7,
    "fno": 2,
    "sid": 2,
    "startTime": "2022-01-11 02:00",
    "endTime": "2022-01-11 03:00",
    "date": null,
    "selectedTime": null
  },
  ...
]

```

[예외. 로그인 확인](#예외.-로그인-확인)

[예외 예약 목록이 없는 경우](#예외-예약-목록이-없는-경우)

[예외. 본인이 예약한 예약이 아닌 다른값이 들어갈 경우](#예외.-본인이-예약한-예약이-아닌-다른값이-들어갈-경우)



### 예약 삭제

``` http
DELETE http://127.0.0.1:8000/booking/2 HTTP/1.1
```

```http
HTTP/1.1 204 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Date: Fri, 21 Jan 2022 06:41:04 GMT
Connection: close
```

[예외. 로그인 확인](#예외.-로그인-확인)

[예외. 본인이 예약한 예약이 아닌 다른값이 들어갈 경우](#예외.-본인이-예약한-예약이-아닌-다른값이-들어갈-경우)




### 예외
[예외. 로그인 확인](#예외.-로그인-확인)

[예외. 시간 입력 잘못했을 경우](#예외.-시간-입력-잘못했을-경우)

[예외. 시설 입력 잘못했을 경우](#예외-시설-입력-잘못했을-경우)

[예외 예약 목록이 없는 경우](#예외-예약-목록이-없는-경우)

[예외. 본인이 예약한 예약이 아닌 다른값이 들어갈 경우](#예외.-본인이-예약한-예약이-아닌-다른값이-들어갈-경우)

[예외. 시간 입력 00시 보다 적은 시간,시간 형태가 잘못된 경우](#예외.-시간-입력-00시-보다-적은-시간,시간-형태가-잘못된-경우)

[예외. 시간 입력 24시 보다 큰 시간](#예외.-시간-입력-24시-보다-큰-시간)


#### 예외. 로그인 확인

``` http
HTTP/1.1 401 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Set-Cookie: SID=488F40915CF93F562E02CF0A88ED3515; Max-Age=2592000; Expires=Fri, 18-Feb-2022 06:18:00 GMT; Path=/; HttpOnly
Content-Type: application/json
Content-Length: 34
Date: Wed, 19 Jan 2022 06:18:00 GMT
Connection: close

로그인 후 이용해 주세요.
```

#### 예외. 본인이 예약한 예약이 아닌 다른값이 들어갈 경우

``` http
HTTP/1.1 404 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Content-Length: 40
Date: Fri, 21 Jan 2022 06:41:54 GMT
Connection: close

예약을 번호를 확인해 주세요.
```

#### 예외. 시간 입력 잘못했을 경우

``` http
HTTP/1.1 404 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Content-Length: 33
Date: Wed, 19 Jan 2022 06:19:05 GMT
Connection: close

예약 시간을 확인해 주세요.
```

#### 예외. 시설 입력 잘못했을 경우

```http
HTTP/1.1 404 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Content-Length: 30
Date: Fri, 21 Jan 2022 06:43:28 GMT
Connection: close

시설을 확인해 주세요.
```

#### 예외. 예약 목록이 없는 경우

``` http
HTTP/1.1 204 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Date: Fri, 21 Jan 2022 06:44:23 GMT
Connection: close
```

#### 예외. 시간 입력 00시 보다 적은 시간,시간 형태가 잘못된 경우

``` http
POST http://127.0.0.1:8000/booking/2 HTTP/1.1
Content-Type: application/json

{
  "date":"2022-01-01",
  "selectedTime":-1
}
```

``` http
POST http://127.0.0.1:8000/booking/2 HTTP/1.1
Content-Type: application/json

{
  "date":"2022-01-01",
  "selectedTime":"029:300"
}
```

``` http
HTTP/1.1 400 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 21 Jan 2022 06:38:13 GMT
Connection: close

{
  "timestamp": "2022-01-21T06:38:13.286+00:00",
  "status": 400,
  "error": "Bad Request",
  "path": "/booking/2"
}
```

#### 예외. 시간 입력 24시 보다 큰 시간

``` http
POST http://127.0.0.1:8000/booking/2 HTTP/1.1
Content-Type: application/json

{
  "date":"2022-01-01",
  "selectedTime":"24:00"
}
```

``` http
HTTP/1.1 400 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 21 Jan 2022 06:38:13 GMT
Connection: close

{
  "timestamp": "2022-01-21T06:38:13.286+00:00",
  "status": 400,
  "error": "Bad Request",
  "path": "/booking/2"
}

```
