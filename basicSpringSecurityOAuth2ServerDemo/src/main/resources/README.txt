OAuthServer endpoints (with @EnableAuthorizationServer):

acme:acmesecret@localhost:8080/oauth/token
 to call with -d grant_type=client_credentials
           or -d grant_type=password -d username=user -d password=pwdhsdbfhjfqk...dsd
           
localhost:8080/oauth/authorize?client_id=acme&redirect_uri=http://localhost:9999/login&response_type=code&state=YXnsd1           