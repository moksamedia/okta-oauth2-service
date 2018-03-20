# Okta Provider Plugin for Grails Spring Security OAuth2 Plugin

Defines an Okta OAuth2 provider for using with Grails Spring Security.

### Requires
* grails-spring-security-oauth2 (https://github.com/MatrixCrawler/grails-spring-security-oauth2)
* grails-spring-security-core (https://grails-plugins.github.io/grails-spring-security-core/)

```
    compile 'org.grails.plugins:spring-security-core:3.2.1+'
    compile 'org.grails.plugins:spring-security-oauth2:1.1.0+'
```

### Installation

TODO: link to tutorial or write out simple steps here

### Configuration

##### application.groovy

If you want to override the default login form and always redirect to login with Okta, 
add the following to the application.groovy file:

```
grails.plugin.springsecurity.auth.loginFormUrl = '/springSecurityOAuth2/authenticate?provider=okta'
```

##### Application.yml

You must configure your API key and secret from Okta as well as the following 3 URLS:
* `/userinfo`
* `/authorize`
* `/token`

The OAuth2 callback defaults to `/oauth2/callback`

```yaml

    plugin:
        springsecurity:
            oauth2:
                active: true
                registration:
                    roleNames: ['ROLE_USER'] # this is the role assigned to new users registered through OAuth
                    okta:
                        api_key: '<your key>'         
                        api_secret: '<your secret>'
                        userInfoUrl: 'https://<your Okta URL>.com/oauth2/v1/userinfo'
                        authorizeUrl: 'https://<your Okta URL>.com/oauth2/v1/authorize'
                        tokenUrl: 'https://<your Okta URL>.com/oauth2/v1/token'    
                        scopes: 'email profile openid'
                 
```