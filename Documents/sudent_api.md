### login

```http
POST http://127.0.0.1:8000/students/login HTTP/1.1
Content-Type: application/json

{
  "sid":123123,
  "pw":"1234"
}
```

```html
HTTP/1.1 200 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Set-Cookie: SID=57634CEE90B12F8548DCD485E50D3241; Max-Age=2592000; Expires=Fri, 18-Feb-2022 09:02:50 GMT; Path=/; HttpOnly
Content-Type: application/json
Content-Length: 6
Date: Wed, 19 Jan 2022 09:02:50 GMT
Connection: close

성공
```

### logout

```http
GET http://127.0.0.1:8000/students/logout HTTP/1.1
Content-Type: application/json
```

```http
HTTP/1.1 200 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Set-Cookie: SID=2A5CD6A5FFBEBD367F4A64C82E1774BE; Max-Age=2592000; Expires=Fri, 18-Feb-2022 08:59:45 GMT; Path=/; HttpOnly
Location: /booking
Content-Length: 0
Date: Wed, 19 Jan 2022 08:59:45 GMT
Connection: close
```

### 회원가입 

```http
POST http://127.0.0.1:8000/students/join HTTP/1.1
Content-Type: application/json

{
  "sid":123123,
  "pw":"1234",
  "name":"이름",
  "phone":"010010"
}
```

```http
HTTP/1.1 201 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 19 Jan 2022 09:11:44 GMT
Connection: close

{
  "sid": 123123,
  "pw": "1234",
  "name": "이름",
  "phone": "010010"
}
```

### 회원 id 조회

```http
GET http://127.0.0.1:8000/students/123123 HTTP/1.1
Content-Type: application/json
```

```http
HTTP/1.1 200 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 19 Jan 2022 09:05:52 GMT
Connection: close

{
  "sid": 123123,
  "pw": "1234",
  "name": "이름",
  "phone": "010010"
}
```

### 회원 정보 수정 

요소에 null값 넣을시 전에 있던 값 그대로 사용

```http
PUT http://127.0.0.1:8000/students/123123 HTTP/1.1
Content-Type: application/json

{
  "pw":"1234",
  "name":"새로운이름",
  "phone":"010010"
}
```

```http
HTTP/1.1 200 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 19 Jan 2022 09:12:21 GMT
Connection: close

{
  "sid": 123123,
  "pw": "1234",
  "name": "새로운이름",
  "phone": "010010"
}
```

### 회원 탈퇴

```http
DELETE http://127.0.0.1:8000/students/123123 HTTP/1.1
```

```http
HTTP/1.1 200 
Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
Content-Length: 0
Date: Wed, 19 Jan 2022 09:11:32 GMT
Connection: close
```

