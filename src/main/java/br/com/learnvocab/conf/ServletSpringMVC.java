/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.learnvocab.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author Fred
 */
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override	
    protected Class<?>[] getRootConfigClasses() {
            return new Class[]{SecurityConfiguration.class, 
                            AppWebConfiguration.class, JPAConfiguration.class,
                            JPAProductionConfiguration.class};
    }
    
    /*   
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {AppWebConfiguration.class, JPAConfiguration.class,SecurityConfiguration.class};
    }
    */
    
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        
        return new Filter[]{characterEncodingFilter};
    }
    
    @Override
    protected void customizeRegistration(Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement(""));
    }
/*
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext); 
        servletContext.addListener(RequestContextListener.class);
        servletContext.setInitParameter("spring.profiles.active", "dev");
    }
    */
}
