package io.security.oauth2.spring_security_oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Arrays;
import java.util.List;

/**
 * yml 설정이 아닌 java config 방식으로도 ClientRegistrationRepository 를 등록할 수 있다.
 * java config 방식 사용시 아래 @Configuration 주석 해제후 사용할것
 */
//@Configuration
public class CustomClientRegistrationRepositoryConfig {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.createRegisteredClients());
    }

    private List<ClientRegistration> createRegisteredClients() {
        return Arrays.asList(customKeycloakClientRegistration(),
                customKeycloakClientRegistration2(),
                issuerLocationKeycloakClientRegistration());
    }

    private ClientRegistration customKeycloakClientRegistration() {
        return ClientRegistration.withRegistrationId("config-keycloak")
                .clientId("oauth2-client-app")
                .clientSecret("UAVYZDOh5rdpQxuK4EQ9sQaZh3K9mlVi")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:8081/login/oauth2/code/keycloak")
                .scope("openid", "profile", "email", "address", "phone")
                .authorizationUri("http://localhost:8080/realms/oauth2/protocol/openid-connect/auth")
                .tokenUri("http://localhost:8080/realms/oauth2/protocol/openid-connect/token")
                .userInfoUri("http://localhost:8080/realms/oauth2/protocol/openid-connect/userinfo")
                .jwkSetUri("http://localhost:8080/realms/oauth2/protocol/openid-connect/certs")
                .userNameAttributeName("preferred_username")
                .clientName("keycloak")
                .build();
    }

    private ClientRegistration customKeycloakClientRegistration2() {
        return ClientRegistration.withRegistrationId("config-keycloak2")
                .clientId("oauth3-client-app")
                .clientSecret("NIjFfDn85bLivzKYV1ABGsPcLvdRgzlI")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:8081/login/oauth2/code/keycloak2")
                .scope("openid", "profile", "email", "address", "phone")
                .authorizationUri("http://localhost:8080/realms/oauth3/protocol/openid-connect/auth")
                .tokenUri("http://localhost:8080/realms/oauth3/protocol/openid-connect/token")
                .userInfoUri("http://localhost:8080/realms/oauth3/protocol/openid-connect/userinfo")
                .jwkSetUri("http://localhost:8080/realms/oauth3/protocol/openid-connect/certs")
                .userNameAttributeName("preferred_username")
                .clientName("keycloak2")
                .build();
    }

    /**
     * 아래와 같은 방식을 사용하면 편하다.
     * authorizationUri, tokenUri, userInfoUri, jwkSetUri 를 알아서 등록해주기 때문
     */
    private ClientRegistration issuerLocationKeycloakClientRegistration() {
        return ClientRegistrations.fromIssuerLocation("http://localhost:8080/realms/oauth2")
                .registrationId("isser-location-keycloak")
                .clientId("oauth2-client-app")
                .clientName("issuer-keycloak")
                .clientSecret("UAVYZDOh5rdpQxuK4EQ9sQaZh3K9mlVi")
                .userNameAttributeName("preferred_username")
                .redirectUri("http://localhost:8081/login/oauth2/code/keycloak")
                .build();
    }
}
