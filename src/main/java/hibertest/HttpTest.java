package hibertest;

import controler.commands.DoItHibernate;
import model.Developer;
import model.GenerallyTable;
import model.Project;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import view.Table;

import java.sql.SQLException;
import java.util.ArrayList;


public class HttpTest {
    private static SessionFactory sessionFactory;
    public HttpTest(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
// so destroy it manually.
            System.out.println("error in sessionFactory init");
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }
    public static void main(String[] args) throws SQLException {
//        new HttpTest();
//        Session session = sessionFactory.openSession();
//        for (int i = 0; i < 2; i++) {
////            Session session = sessionFactory.openSession();
//            Developer developer = session.get(Developer.class, 3);
//            Table.printAsTable(developer);
//            session.evict(developer);
////            session.close();
//        }
//        session.close();
        ArrayList<Developer> developers = new ArrayList<>();
        DoItHibernate<Developer> hib = new DoItHibernate<>();
        developers = hib.getAllDataTable(Developer.class);
        Table.printAsTable(developers);

        System.exit(0);
    }

    private long checkpoint;
    public static void testCashingLevel2() throws SQLException {
        DoItHibernate<GenerallyTable> hib = new DoItHibernate<>();
        Project p = (Project) hib.getFromTableById(Project.class, 3L);
        Table.printAsTable(p);

    }

    private void start(){checkpoint = System.currentTimeMillis();}
    private long getStop(){return System.currentTimeMillis() - checkpoint;}
}
