/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.learnvocab.conf;

import br.com.learnvocab.dao.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 *
 * @author Fred
 */
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserDAOImpl userDao;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        http.authorizeRequests()
        .antMatchers("/produtos/form").hasRole("ADMIN")
        .antMatchers("/carrinho/**").permitAll()
        .antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/produtos").hasRole("ADMIN")
        .antMatchers("/produtos/**").permitAll()
        .antMatchers("/resources/**").permitAll()
        .antMatchers("/").permitAll()
        .anyRequest().authenticated()
        .and().formLogin().loginPage("/login").permitAll()
        .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                */
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);
        
        http.authorizeRequests()
            .antMatchers("/idiom/*").hasRole("ADMIN")
            .antMatchers("/idiom").hasRole("ADMIN")
            .antMatchers("/word").hasRole("ADMIN")
            .antMatchers("/word/*").hasRole("ADMIN")
            .antMatchers("/box").hasRole("ADMIN")
            .antMatchers("/box/*").hasRole("ADMIN")
            .antMatchers("/user").hasRole("ADMIN")
            .antMatchers("/user/*").hasRole("ADMIN")
            .antMatchers("/resources/**").permitAll()
            .antMatchers("/*").permitAll()
            .antMatchers("/url-magica-maluca-oajksfbvad6584i57j54f9684nvi658efnoewfmnvowefnoeijn").permitAll()
            .anyRequest().authenticated()
            .and().formLogin().loginPage("/login").defaultSuccessUrl("/idiom", false).permitAll()            
            .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
//            .and().logout().logoutSuccessUrl("/login?logout")
//                ;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDao)
        .passwordEncoder(new BCryptPasswordEncoder());
    }
    
}
