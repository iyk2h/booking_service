### [HTTPStatus 응답 명세서](./HTTP_status_code.md)

---

### 로그인

- #### request
```http
POST /students/login 
Content-Type: application/json

{
  "sid":2,
  "pw":"pwd"
}
```

- #### response
```http
State : 201 
Content-Type: application/json

성공
```



### 로그아웃

- #### request
``` http
GET /students/logout 
```

- #### response
``` http
State : 204 
Content-Type: application/json
```
[예외. 로그인 확인](#예외-로그인-확인)



### main 홈

- #### request
```http
GET /booking 
```

- #### response
```http
State : 200 
Content-Type: application/json

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

- #### request
``` http
GET /booking/2 
Content-Type: application/json
```

- #### response
``` http
State : 200 
Content-Type: application/json

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

- #### request
``` http
POST /booking/2/date 
Content-Type: application/json

{
  "date	":"2022-01-01"
}
```

- #### response
``` http
State : 201 
Content-Type: application/json

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

[예외. 예약 목록이 없는 경우](#예외-예약-목록이-없는-경우)

[예외. 시간 입력 형태가 잘못된 경우](#예외-시간-입력-00시-보다-적은-시간,시간-형태가-잘못된-경우)



### 예약하기 

- #### request
``` http
POST /booking/2 
Content-Type: application/json

{
  "date":"2022-01-01",
  "selectedTime":0
}
```

#### 예약 성공

- #### response
```http
State : 201 
Content-Type: application/json

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

[예외. 로그인 확인](#예외-로그인-확인)

[예외. 시간 입력 잘못했을 경우](#예외-시간-입력-잘못했을-경우)

[예외. 시설 입력 잘못했을 경우](#예외-시설-입력-잘못했을-경우)

[예외. 시간 입력 00시 보다 적은 시간,시간 형태가 잘못된 경우](#예외-시간-입력-00시-보다-적은-시간,시간-형태가-잘못된-경우)

[예외. 시간 입력 24시 보다 큰 시간](#예외-시간-입력-24시-보다-큰-시간)




### 사용자 자신이 예약한 리스트 보기

- #### request
``` http
GET /booking/students 
Content-Type: application/json
```

- #### response
``` http
State : 200 
Content-Type: application/json

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

[예외. 로그인 확인](#예외-로그인-확인)

[예외. 예약 목록이 없는 경우](#예외-예약-목록이-없는-경우)

[예외. 본인이 예약한 예약이 아닌 다른값이 들어갈 경우](#예외-본인이-예약한-예약이-아닌-다른값이-들어갈-경우)



### 예약 삭제

``` http
DELETE /booking/2 
```

- #### response
```http
State : 204 
Content-Type: application/json
```

[예외. 로그인 확인](#예외-로그인-확인)

[예외. 본인이 예약한 예약이 아닌 다른값이 들어갈 경우](#예외-본인이-예약한-예약이-아닌-다른값이-들어갈-경우)




### 예외
[예외. 로그인 확인](#예외-로그인-확인)

[예외. 시간 입력 잘못했을 경우](#예외-시간-입력-잘못했을-경우)

[예외. 시설 입력 잘못했을 경우](#예외-시설-입력-잘못했을-경우)

[예외. 예약 목록이 없는 경우](#예외-예약-목록이-없는-경우)

[예외. 본인이 예약한 예약이 아닌 다른값이 들어갈 경우](#예외-본인이-예약한-예약이-아닌-다른값이-들어갈-경우)

[예외. 시간 입력 00시 보다 적은 시간,시간 형태가 잘못된 경우](#예외-시간-입력-00시-보다-적은-시간,시간-형태가-잘못된-경우)

[예외. 시간 입력 24시 보다 큰 시간](#예외-시간-입력-24시-보다-큰-시간)



#### 예외. 로그인 확인
- #### response

``` http
 State : 401 
Content-Type: application/json

로그인 후 이용해 주세요.
```



#### 예외. 본인이 예약한 예약이 아닌 다른값이 들어갈 경우
- #### response
``` http
 State : 404 
Content-Type: application/json

예약을 번호를 확인해 주세요.
```



#### 예외. 시간 입력 잘못했을 경우
- #### response
``` http
 State : 404 
Content-Type: application/json

예약 시간을 확인해 주세요.
```



#### 예외. 시설 입력 잘못했을 경우
- #### response
```http
 State : 404 
Content-Type: application/json

시설을 확인해 주세요.
```



#### 예외. 예약 목록이 없는 경우

- #### response
``` http
State : 204 
Content-Type: application/json
```



#### 예외. 시간 입력 00시 보다 적은 시간,시간 형태가 잘못된 경우

- #### request
``` http
POST /booking/2 
Content-Type: application/json

{
  "date":"2022-01-01",
  "selectedTime":-1
}
```

- #### request
``` http
POST /booking/2 
Content-Type: application/json

{
  "date":"2022-01-01",
  "selectedTime":"029:300"
}
```
- #### response
``` http
 State : 400 
Content-Type: application/json

{
  "timestamp": "2022-01-21T06:38:13.286+00:00",
  "status": 400,
  "error": "Bad Request",
  "path": "/booking/2"
}
```



#### 예외. 시간 입력 24시 보다 큰 시간

- #### request
``` http
POST /booking/2 
Content-Type: application/json

{
  "date":"2022-01-01",
  "selectedTime":"24:00"
}
```
- #### response
``` http
 State : 400 
Content-Type: application/json

{
  "timestamp": "2022-01-21T06:38:13.286+00:00",
  "status": 400,
  "error": "Bad Request",
  "path": "/booking/2"
}

```
