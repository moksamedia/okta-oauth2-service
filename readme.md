#### Okta Provider Plugin for Grails Spring Security OAuth2 Plugin

Defines an Okta OAuth2 provider. Allows Grails combined with Spring Security and Spring Security OAuth2 to authenticate via Okta.

Requires
* grails-spring-security-oauth2 (https://github.com/MatrixCrawler/grails-spring-security-oauth2)
* grails-spring-security-core (https://grails-plugins.github.io/grails-spring-security-core/)

```
    compile 'org.grails.plugins:spring-security-core:3.2.1+'
    compile 'org.grails.plugins:spring-security-oauth2:1.1.0+'
```

Application.yml

```yaml

    plugin:
        springsecurity:
            oauth2:
                active: true    #whether the whole plugin is active or not
                registration:
                    //askToLinkOrCreateAccountUri: '/oauth2/ask' # The URI that is called to aks the user to either create a new account or link to an existing account
                    roleNames: ['ROLE_USER'] #A list of role names that should be automatically granted to an OAuth User. The roles will be created if they do not exist
                providers:
                    okta:
                        api_key: '<your key>'         
                        api_secret: '<your secret>'
                        userInfoUrl: 'https://<your Okta URL>.com/oauth2/v1/userinfo'
                        authorizeUrl: 'https://<your Okta URL>.com/oauth2/v1/authorize'
                        tokenUrl: 'https://<your Okta URL>.com/oauth2/v1/token                      
```