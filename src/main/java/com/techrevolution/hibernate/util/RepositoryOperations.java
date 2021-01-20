package com.techrevolution.hibernate.util;

import com.techrevolution.hibernate.configuration.SpringConfiguration;
import com.techrevolution.hibernate.onetoone.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Objects;

public class RepositoryOperations<T> {

    private final ApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    private final SessionFactory sessionFactory
            = applicationContext.getBean("sessionFactory", SessionFactory.class);
    private final Session session
            = sessionFactory.openSession();
    private final Transaction transaction
            = session.beginTransaction();


    public T getEntity(int byId){
        Query sessionQuery = session.createQuery("from Instructor where id=" + byId);
        Object uniqueResult = sessionQuery.uniqueResult();
        return (T) uniqueResult;
    }


    public boolean save(T t) {
        try(sessionFactory ; session) {
            Objects.requireNonNull(t);
            session.save(t);
            transaction.commit();
            System.out.println("Successfully saved data.");
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById(Class<T> tClass , int id) {
        try(sessionFactory ; session) {
            T t = session.get(tClass, id);
            Objects.requireNonNull(t);
            session.delete(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
