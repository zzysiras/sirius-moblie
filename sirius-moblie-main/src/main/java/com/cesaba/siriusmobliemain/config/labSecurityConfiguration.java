package com.cesaba.siriusmobliemain.config;

//import com.fasterxml.jackson.core.filter.TokenFilter;
//import com.cesaba.siriusmobliemain.filter.TokenFilter;
import com.cesaba.siriusmobliemain.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


//@EnableWebSecurity
//@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class labSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;
    //@Autowired
    //@Qualifier("userDetailsServiceImpl")
    //private UserDetailsService userDetailsService;

    @Autowired
    private TokenFilter tokenFilter;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){return new BCryptPasswordEncoder();}


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//禁用session

        http.authorizeRequests()
                /*.antMatchers("/","/favicon.ico", "/druid/**","/labWeb","/*.html",
                        "/backstagelogin","/introducation","/static/**","/img/img/login/**",
                        "/css/**","/css/css/**","/img/**","/js/**","/libs/**","/labweb/**",
                        "/fonts/**","/header","/footer","/pages/**","/pages/dashboard",
                        "/layui/**","static/css/css/**","static/js/**","static/js/js/**")*/
                .antMatchers("/","/favicon.ico", "/*.html","/css/**","/js/**","/fonts/**","/img/**",
                        "/labweb/**","/layui/**","/libs/**","/pages/**","/plugins/**","/druid/**","/v2/api-docs/**",
                        "/swagger-resources/**","/webjars/**","/static/**","/statics/**","/article/**","/demo/**")
                .permitAll().anyRequest().authenticated();
                //.antMatchers("/user/**","/user/update/","/dept/**","/query/user/**","/query/user/json").hasAnyRole("USER","ADMIN")
                //.antMatchers().access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')");

        http.formLogin().loginProcessingUrl("/login").permitAll(true)
                .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
                .and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);
        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);

        http.headers().frameOptions().disable();
        http.headers().cacheControl();

        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);


        /*http    .headers().frameOptions().disable();
        http    .headers().cacheControl();

        http
                .formLogin().loginPage("/labLogin").loginProcessingUrl("/login/form").failureUrl("/testlogin-error").permitAll()
                 .and().logout().permitAll()
                .and()
                .authorizeRequests().antMatchers("/login").hasRole("MANAGER")
                .and()
                .authorizeRequests()
                .antMatchers("/rolelogin","/labWeb","/*.html","/index","/backstagelogin","/introducation","/static/**","/img/img/login/**","/css/**","/css/css/**","/img/**","/js/**","/libs/**","/labweb/**","/fonts/**","/header","/footer","/pages/**","/pages/dashboard","/layui/**").permitAll()
                 .anyRequest().authenticated()
                .and()
                .csrf().disable();*/
    }

    @Override        //强行配置静态账号密码
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        /*auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder()).withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("MANAGER")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder()).withUser("guest").password(new BCryptPasswordEncoder().encode("guest")).roles("GUEST");*/
    }



    //@Bean
    /*public LogoutSuccessHandler logoutSuccessHandler(){
        return new LogoutSuccessHandler() {

            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                /*try {
                    SecurityUser user = (SecurityUser) authentication.getPrincipal();
                    logger.info("USER : " + user.getUsername() + " LOGOUT SUCCESS !  ");
                } catch (Exception e) {
                    logger.info("LOGOUT EXCEPTION , e : " + e.getMessage());
                }


                httpServletResponse.sendRedirect("/labWeb");
            }
        };
    }*/




}
