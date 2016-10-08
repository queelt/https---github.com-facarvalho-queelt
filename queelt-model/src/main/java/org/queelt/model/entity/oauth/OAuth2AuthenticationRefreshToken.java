package org.queelt.model.entity.oauth;

import org.queelt.common.Functions;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@Document(collection="refresh_token")
public class OAuth2AuthenticationRefreshToken extends BaseEntity {

	private static final long serialVersionUID = 3029821062726517824L;
	private String tokenId;
    private OAuth2RefreshToken oAuth2RefreshToken;
    private byte[] authentication;

    public OAuth2AuthenticationRefreshToken(){
    	
    }
    
    public OAuth2AuthenticationRefreshToken(OAuth2RefreshToken oAuth2RefreshToken, OAuth2Authentication authentication) {
        this.oAuth2RefreshToken = oAuth2RefreshToken;
        this.authentication = Functions.serialize(authentication);
        this.tokenId = oAuth2RefreshToken.getValue();
    }

    public String getTokenId() {
        return tokenId;
    }

    public OAuth2RefreshToken getoAuth2RefreshToken() {
        return oAuth2RefreshToken;
    }

    public OAuth2Authentication getAuthentication() {
    	return Functions.deserialize(authentication);
    }
}
