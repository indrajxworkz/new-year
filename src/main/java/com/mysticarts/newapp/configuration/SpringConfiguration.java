package com.mysticarts.newapp.configuration;

import com.mysticarts.newapp.dto.ServiceConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan("com.mysticarts.newapp")
public class SpringConfiguration implements WebMvcConfigurer {

    public SpringConfiguration(){

        System.out.println("Default Constructor");
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(getDataSource());
        bean.setPackagesToScan("com.mysticarts.newapp.entity");
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setJpaProperties(getProperties());
        return bean;
    }

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource  =  new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/newYear");
        dataSource.setUsername("root");
        dataSource.setPassword("Indru@1903");
        return dataSource;
    }

    @Bean
    public Properties getProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        return properties;
    }
    @Bean
    public ViewResolver viewResolver(){

        return new InternalResourceViewResolver("/",".jsp");
    }

    @Bean
    public MultipartResolver multipartResolver(){
        return  new StandardServletMultipartResolver();
    }

    @Bean
    public JavaMailSender getMail(){

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(ServiceConstant.FROM_MAIL.getPath());
        javaMailSender.setPassword(ServiceConstant.PASSWORD.getPath());
        javaMailSender.setPort(587);
        javaMailSender.setHost("smtp.gmail.com");
        Properties  properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug","true");
        return javaMailSender;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/URLToReachStaticFolder/**")
                .addResourceLocations("/static/");

    }
}

