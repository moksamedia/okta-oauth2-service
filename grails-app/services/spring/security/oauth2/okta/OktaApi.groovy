package spring.security.oauth2.okta

import com.github.scribejava.core.builder.api.DefaultApi20
import com.github.scribejava.core.model.OAuthConfig
import com.github.scribejava.core.oauth.OAuth20Service
import groovy.util.logging.Slf4j

@Slf4j
class OktaApi extends DefaultApi20 {
    
    private static final String AUTHORIZE_URL = "https://dev-533919.oktapreview.com/oauth2/v1/authorize";
    private static final String REQUEST_TOKEN_URL = "https://dev-533919.oktapreview.com/oauth2/v1/token";

    protected OktaApi() {
    }

    private static class InstanceHolder {
        private static final OktaApi INSTANCE = new OktaApi();
    }

    public static OktaApi instance() {
        return InstanceHolder.INSTANCE;
    }
    
    public String getAccessTokenEndpoint() {
        REQUEST_TOKEN_URL
    }

    protected String getAuthorizationBaseUrl() {
        AUTHORIZE_URL
    }

    @Override
    public OAuth20Service createService(OAuthConfig config) {

        log.info "OauthCallback = " + config.callback;
        return new OAuth20Service(this, config);
    }

}
