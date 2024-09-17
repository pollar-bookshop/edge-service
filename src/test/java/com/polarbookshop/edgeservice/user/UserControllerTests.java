package com.polarbookshop.edgeservice.user;

import com.polarbookshop.edgeservice.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebFluxTest(UserController.class)
@Import(SecurityConfig.class) // 애플리케이션의 보안 설정을 임포트한다.
public class UserControllerTests {
    @Autowired
    WebTestClient webClient;

    @MockBean // 클라이언트 등록에 대한 정보를 가져올 때 키클록과의 상호작용을 실제로 하지 않기 위한 모의 빈
    ReactiveClientRegistrationRepository clientRegistrationRepository;

    @Test
    void whenAuthenticatedThenReturnUser() {
        var expectedUser = new User("Jon.snow", "Jon", "Snow", List.of("employee", "customer"));

        webClient
                .mutateWith(configureMockOidcLogin(expectedUser))
                .get()
                .uri("/user")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(User.class)
                .value(user -> assertThat(user).isEqualTo(expectedUser));
    }

    private SecurityMockServerConfigurers.OidcLoginMutator configureMockOidcLogin(User expectedUser) {
        return SecurityMockServerConfigurers.mockOidcLogin().idToken(
                builder -> { // 모의 ID 토큰을 생성
                    builder.claim(StandardClaimNames.PREFERRED_USERNAME,
                            expectedUser.username());
                    builder.claim(StandardClaimNames.GIVEN_NAME,
                            expectedUser.firstName());
                    builder.claim(StandardClaimNames.FAMILY_NAME,
                            expectedUser.lastName());
                    builder.claim("roles", expectedUser.roles()); // 모의 ID 토큰에 roles 클래임을 추가한다.
                });
    }
}
