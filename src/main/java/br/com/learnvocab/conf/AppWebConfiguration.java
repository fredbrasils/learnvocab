/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.learnvocab.conf;


import br.com.learnvocab.controller.HomeController;
import br.com.learnvocab.dao.BaseDAO;
import br.com.learnvocab.service.BaseService;
import br.com.learnvocab.validation.IdiomValidation;
import com.google.common.cache.CacheBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author Fred
 */
@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, BaseService.class , BaseDAO.class, IdiomValidation.class})
@EnableCaching
public class AppWebConfiguration extends WebMvcConfigurerAdapter{
    
    @Override // utilizar os recursos
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setContentType("text/html;charset=UTF-8");
        
        // permite utilizar qualquer bean em qualquer parte do sistema
//        resolver.setExposeContextBeansAsAttributes(true);
        
//        resolver.setExposedContextBeanNames("carrinhoCompras");
        
        return resolver;
    }
    
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/WEB-INF/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(1);
        return messageSource;
    }
    
    @Bean
    public FormattingConversionService mvcConversionService(){
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
        formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
        formatterRegistrar.registerFormatters(conversionService);

        return conversionService;
    }
    
    // trabalhar com arquivo no form
    @Bean
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }
    
    
    /* Para trabalhar com rest */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    
    // cache para teste
    /*@Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager();
    }*/
    
    /* Fazer o cache das consultas*/
    @Bean
    public CacheManager cacheManager(){
      CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5, TimeUnit.MINUTES);
      GuavaCacheManager manager = new GuavaCacheManager();
      manager.setCacheBuilder(builder);
      return manager;
    }
    
    
    /* Para trabalhar com visualizações diferentes (html / json)*/
    @Bean
    public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager){
        List<ViewResolver> viewResolvers = new ArrayList<>();
        viewResolvers.add(internalResourceViewResolver());
        viewResolvers.add(new JsonViewResolver());

        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setViewResolvers(viewResolvers);
        resolver.setContentNegotiationManager(manager);
        return resolver;
    }

    /* Locale das telas */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
    }
    
    @Bean //internacionalização
    public LocaleResolver localeResolver(){
        return new CookieLocaleResolver();
    }
    
    @Bean
    public MailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setUsername("alura.springmvc@gmail.com");
        mailSender.setPassword("alura2015");
        mailSender.setPort(587);

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", true);
        mailSender.setJavaMailProperties(mailProperties);

        return mailSender;
    }
}
