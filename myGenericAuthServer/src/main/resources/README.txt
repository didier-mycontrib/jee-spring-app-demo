OAuthServer endpoints (with @EnableAuthorizationServer):

fooClientIdPassword:secret@localhost:8081/basic-oauth-server/oauth/token
 to call with -d grant_type=client_credentials
           or -d grant_type=password -d username=user1 -d password=pwd1
  