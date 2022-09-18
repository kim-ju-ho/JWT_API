# JWT _ API

JWT(Json Web Token) API

## registerUser

### Request

`POST /user/signup/`
    
    {
        "username" : "jwt",
        "password" : "1234",
        "email"    : "jwt@jwt.com
    }

### Response

    HTTP/1.1 200 OK
    Date: Fri, 29 Jul 2022 20:38:33 GMT
    Status: 200 OK
    Connection: keep-alive
    Content-Type: application/json
    Content-Length: 94

    {
        "id": 1,
        "username": "jwt1234",
        "password": "$2a$10$1JWLw0E9tyND7jK2tRqKFeQPHp1lbhUKnF2jeFS8StGsZGXfVe6tK",
        "email": "jwt123@jwt.com",
        "role": "USER"
    }

## login

### Request

`POST /user/login/`

    {
        "username" : "jwt",
        "password" : "1234"
    }

### Response

    HTTP/1.1 201 Created
    Date: Fri, 29 Jul 2022 20:43:34 GMT
    Authorization: BEARER eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJFWFBJUkVEX0RBVEUiOjE2NTkzODY2MTQsImlzcyI6Imp3dDEyMzQiLCJVU0VSX05BTUUiOiJqd3QifQ._nD2GWytlwIR5h8HYa-ZAYhU_p__Irw8mjBan_ql5Yo
    Status: 200 ok
    Connection: keep-alive
    Content-Type: application/json
    Content-Length: 0

    {}
    
## getUserInfo

### Request

`POST /user/userinfo`

    Authorization: BEARER eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJFWFBJUkVEX0RBVEUiOjE2NTkzODY2MTQsImlzcyI6Imp3dDEyMzQiLCJVU0VSX05BTUUiOiJqd3QifQ._nD2GWytlwIR5h8HYa-ZAYhU_p__Irw8mjBan_ql5Yo
    Connection: keep-alive
    Content-Length: 0

### Response

    HTTP/1.1 200 ok
    Date: Fri, 29 Jul 2022 20:47:36 GMT
    Status: 200 ok
    Connection: keep-alive
    Content-Type: application/json
    Content-Length: 0

    {
    "username": "jwt",
    "admin": false
    }
