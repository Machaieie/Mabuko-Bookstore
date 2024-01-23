package com.livrariamabuko.Livraria.Mabuko.security.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication
@ConfigurationProperties("cpn")
public class CpnConfigs {
    
    private Security security = new Security();

	public Security getSecurity() {
		return security;
	}

	public static class Security {
        
		private String allowedOrigin;
		private int accessTokenValiditySeconds;
		private int refreshTokenValiditySeconds;
		private boolean httpSecuredCookie;
		private String jwtAccessTokenConverterSecret;

		public String getAllowedOrigin() {
			return allowedOrigin;
		}

		public void setAllowedOrigin(String allowedOrigin) {
			this.allowedOrigin = allowedOrigin;
		}

		public int getAccessTokenValiditySeconds() {
			return accessTokenValiditySeconds;
		}

		public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
			this.accessTokenValiditySeconds = accessTokenValiditySeconds;
		}

		public int getRefreshTokenValiditySeconds() {
			return refreshTokenValiditySeconds;
		}

		public void setRefreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
			this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
		}

		public boolean isHttpSecuredCookie() {
			return httpSecuredCookie;
		}

		public void setHttpSecuredCookie(boolean httpSecuredCookie) {
			this.httpSecuredCookie = httpSecuredCookie;
		}

		public String getJwtAccessTokenConverterSecret() {
			return this.jwtAccessTokenConverterSecret;
		}

		public void setJwtAccessTokenConverterSecret(String jwtAccessTokenConverterSecret) {
			this.jwtAccessTokenConverterSecret = jwtAccessTokenConverterSecret;
		}

	}

}
