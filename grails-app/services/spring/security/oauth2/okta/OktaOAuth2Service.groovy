package spring.security.oauth2.okta

import com.github.scribejava.core.builder.api.DefaultApi20
import com.github.scribejava.core.model.OAuth2AccessToken
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.oauth2.exception.OAuth2Exception
import grails.plugin.springsecurity.oauth2.service.OAuth2AbstractProviderService
import grails.plugin.springsecurity.oauth2.token.OAuth2SpringToken
import spring.security.oauth2.okta.OktaApi

@Transactional
class OktaOAuth2Service extends OAuth2AbstractProviderService {

    String getProviderID() {
        'okta'
    }

    Class<? extends DefaultApi20> getApiClass() {
        OktaApi.class
    }

    String getProfileScope() {
        'https://dev-533919.oktapreview.com/oauth2/v1/userinfo'
    }

    String getScopes() {
        'user'
    }

    String getScopeSeparator() {
        ' '
    }

    OAuth2SpringToken createSpringAuthToken(OAuth2AccessToken accessToken) {
        def user
        def response = getResponse(accessToken)
        try {
            log.debug("JSON response body: {}", accessToken.rawResponse)
            user = JSON.parse(response.body)
        } catch (Exception e) {
            log.error("Error parsing response from {}. Response:\n{}", providerID, response.body)
            throw new OAuth2Exception("Error parsing response from " + providerID, e)
        }
        if (user && !user['email']) {
            log.error("No user email from {}. Response was:\n{}", providerID, response.body)
            throw new OAuth2Exception("No user id from " + providerID)
        }

        new OktaOauth2SpringToken(accessToken, (String) user['email'], providerID)

    }
}
