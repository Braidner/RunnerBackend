package org.braidner.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@SpringBootApplication
public class RunnerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunnerBackendApplication.class, args);
    }

    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    protected static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private final Filter tokenFilter;

        @Autowired
        public WebSecurityConfig(@Qualifier("tokenFilter") Filter tokenFilter) {
            this.tokenFilter = tokenFilter;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .headers().frameOptions().sameOrigin()
                    .and()
                    .addFilterAfter(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers("/api/*").authenticated();

            http.csrf().disable();
        }
    }
}
