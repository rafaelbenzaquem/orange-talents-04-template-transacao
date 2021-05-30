package br.com.zup.academy.benzaquem.transacao.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests(authorizeRequests -> {
                    authorizeRequests
                            .antMatchers(HttpMethod.GET, "/transacoes/**").hasAuthority("SCOPE_benzaquem-transacao-scope")
                            .anyRequest().authenticated();
                }
        )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}
