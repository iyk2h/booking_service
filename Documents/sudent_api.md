### login
- #### request
```http
POST /students/login 
Content-Type: application/json

{
  "sid":123123,
  "pw":"1234"
}
```
- #### response
```html
 State : 200 
Content-Type: application/json

성공
```

### logout

- #### request

```http
GET /students/logout 
Content-Type: application/json
```
- #### response
```http
 State : 204
Location: /booking
```

### 회원가입 
- #### request
```http
POST /students/join 
Content-Type: application/json

{
  "sid":123123,
  "pw":"1234",
  "name":"이름",
  "phone":"010010"
}
```
- #### response
```http
 State : 201 
Content-Type: application/json
Transfer-Encoding: chunked

{
  "sid": 123123,
  "pw": "1234",
  "name": "이름",
  "phone": "010010"
}
```

### 회원 id 조회
- #### request
```http
GET /students/{sid}
Content-Type: application/json
```
- #### response
```http
 State : 200 
Content-Type: application/json
Transfer-Encoding: chunked

{
  "sid": 123123,
  "pw": "1234",
  "name": "이름",
  "phone": "010010"
}
```

### 회원 정보 수정 

요소에 null값 넣을시 전에 있던 값 그대로 사용
- #### request
```http
PUT /students/{sid}
Content-Type: application/json

{
  "pw":"1234",
  "name":"새로운이름",
  "phone":"010010"
}
```
- #### response
```http
 State : 200 
Content-Type: application/json
Transfer-Encoding: chunked

{
  "sid": 123123,
  "pw": "1234",
  "name": "새로운이름",
  "phone": "010010"
}
```

### 회원 탈퇴
- #### request
```http
DELETE /students/{sid}
```
- #### response
```http
 State : 204
```

