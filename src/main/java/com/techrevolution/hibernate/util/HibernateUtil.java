package com.techrevolution.hibernate.util;

import com.techrevolution.hibernate.hard.Player;
import com.techrevolution.hibernate.hard.PlayerTeamRegistration;
import com.techrevolution.hibernate.hard.Team;
import com.techrevolution.hibernate.onetoone.Instructor;
import com.techrevolution.hibernate.onetoone.InstructorDetails;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Properties;

@Configuration
public class HibernateUtil {

    @Bean
    @Scope(value = "singleton")
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.setProperties(getProperties());
        configuration
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetails.class)
                .addAnnotatedClass(Player.class)
                .addAnnotatedClass(Team.class)
                .addAnnotatedClass(PlayerTeamRegistration.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/techrevolution?useSSL=false");
        properties.put(Environment.USER, "techrevolution");
        properties.put(Environment.PASS, "Welcome@123");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        return properties;
    }

}
