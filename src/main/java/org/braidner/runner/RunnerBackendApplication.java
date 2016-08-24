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

        private final Filter googleFilter;
        private final Filter runnerFilter;

        @Autowired
        public WebSecurityConfig(
                @Qualifier("googleAuthenticationFilter") Filter googleFilter,
                @Qualifier("runnerAuthenticationFilter") Filter runnerFilter
        ) {
            this.googleFilter = googleFilter;
            this.runnerFilter = runnerFilter;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .headers().frameOptions().sameOrigin()
                    .and()
                    .addFilterAfter(googleFilter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers("/api/*").authenticated();

            http
                    .headers().frameOptions().sameOrigin()
                    .and()
                    .addFilterAfter(runnerFilter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers("/auth/*").authenticated();

            http.csrf().disable();
        }
    }
}
