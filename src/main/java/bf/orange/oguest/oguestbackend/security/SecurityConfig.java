package bf.orange.oguest.oguestbackend.security;

import bf.orange.oguest.oguestbackend.security.jwt.AuthEntryPointJwt;
import bf.orange.oguest.oguestbackend.security.jwt.AuthTokenFilter;
import bf.orange.oguest.oguestbackend.security.services.UserDetailsServiceImpl;
import bf.orange.oguest.oguestbackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = false,
        jsr250Enabled = false,
        prePostEnabled = true)
public class SecurityConfig {

    @Value("${spring.ldap.urls}")
    private String ldapUrls;

    @Value("${spring.ldap.base}")
    private String ldapBaseDn;

    @Value("${spring.ldap.username}")
    private String ldapSecurityPrincipal;

    @Value("${spring.ldap.password}")
    private String ldapPrincipalPassword;

    @Value("${spring.ldap.user.dn.pattern}")
    private String ldapUserDnPattern;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Configuration
    public class LDAPSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            System.out.println("::>>>>>>>>>>> Ldap Auth");
            auth
                    .ldapAuthentication()
                    .contextSource()
                    .url(ldapUrls + ldapBaseDn)
                    .managerDn(ldapSecurityPrincipal)
                    .managerPassword(ldapPrincipalPassword)
                    .and()
                    .userSearchFilter(ldapUserDnPattern);
        }

        @Qualifier("ldapBean")
        @Bean("ldapAuthenticationManager")
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            System.out.println("::>>>>>>>>>>> Ldap Auth HttpSecurity");
            http.cors().and().csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .authorizeRequests()
                    .antMatchers("/api/auth/ldap/**", "/backend/api/user/list/**").permitAll()
                    //.antMatchers("/backend/test/**", "/backend/auth/account/**", "/swagger-ui/**", "/v3/api-docs/**", "/javainuse-openapi/**").permitAll()
                    //.antMatchers().permitAll()
                    //.antMatchers("/api/users/**").hasRole(roleService.findAdminRole().getSlug())
                    .anyRequest().authenticated();
                    //.anyRequest().permitAll();
            http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        }

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

}
