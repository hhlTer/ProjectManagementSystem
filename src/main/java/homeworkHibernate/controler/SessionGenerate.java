package homeworkHibernate.controler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionGenerate {
    private SessionFactory sessionFactory;
    private static SessionGenerate sessionGenerate = new SessionGenerate();

    private SessionGenerate(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static SessionGenerate getInstance(){
        return sessionGenerate;
    }

    public Session createSession(){
        return sessionFactory.openSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void closeSessionFactory(){
        sessionFactory.close();
    }
}
