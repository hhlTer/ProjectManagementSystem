package controler.commands;

import model.Developer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.ArrayList;

public class DoItHibernate<T> implements HyberInterface<T> {

    private static SessionFactory sessionFactory;
    public DoItHibernate(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
// so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Override
    public T selectFromTableById(Class<?> clazz, long id) throws SQLException {
        T t = null;
        Session session = sessionFactory.openSession();

        clazz = Developer.class;
        Developer developer = session.get(Developer.class, id);
        t = (T) developer;
        session.close();
        return t;
    }

    @Override
    public void insertIntoTable(T t) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
    }



    @Override
    public void updateInTable(Class t) throws SQLException {

    }

    @Override
    public void eraseTable() throws SQLException {

    }

    @Override
    public void deleteCortege(long id) throws SQLException {

    }

    @Override
    public ArrayList<Class<T>> getAllDataTable() throws SQLException {
        return null;
    }
}
