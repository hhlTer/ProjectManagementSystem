package homeworkHibernate.controler.main;

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
            System.out.println("create session factory");
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            System.out.println("done creating session fa");
        } catch (Exception e) {
            e.printStackTrace();
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
