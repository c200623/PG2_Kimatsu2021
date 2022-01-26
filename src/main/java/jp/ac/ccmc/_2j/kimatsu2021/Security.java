package jp.ac.ccmc._2j.kimatsu2021;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter{
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
        .authorizeRequests()
        .antMatchers("/", "/webjars/bootstrap/5.0.1/css/bootstrap.min.css").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .permitAll();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        String password = passwordEncoder().encode("c200623");
        auth
        .inMemoryAuthentication()
        .passwordEncoder(passwordEncoder())
        .withUser("tuan2j")
        .password(password)
        .roles("USER");
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
   

