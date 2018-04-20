package hibertest;

import controler.commands.DoItHibernate;
import controler.commands.HiberInterface;
import model.Developer;
import model.GenerallyTable;
import model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import view.Table;

import java.sql.SQLException;


public class Hibertest {
    private static SessionFactory sessionFactory;
    public Hibertest(){

        System.out.println("1");
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static void main(String[] args) throws SQLException {
//        Developer developer = new Developer();
//        developer.setName("Oleksandrochka");
//        developer.setSex(false);
//        developer.setAge(21);
//        developer.setSalary(new BigDecimal(888));
//        HiberInterface<Developer> dev = new DoItHibernate();
//        dev.insertIntoTable(developer);

        GenerallyTable developer;
        HiberInterface<GenerallyTable> dev = new DoItHibernate<>();
        developer = dev.getFromTableById(Project.class, 2);
        Table.printAsTable(developer);

//        Project project = new Project();
//        project.setProject_name("Hibernate");
//        project.setDescription("Bna9JIy");
//        project.setCost(new BigDecimal(34212));
//        HiberInterface<Project> pro = new DoItHibernate();
//        pro.insertIntoTable(project);
//        new Hibertest().createDeveloper(developer);
    }

    public void createDeveloper(Developer developer){
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(developer);
//        transaction.commit();
//        session.close();
    }

    public Developer getDeveloperById(long id){
        Developer developer = null;
        Session session = sessionFactory.openSession();

        developer = session.get(Developer.class, id);
        session.close();
        return developer;
    }

    public void updateDeveloper(Developer developer){
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        session.update(developer);
//        transaction.commit();
//        session.close();
    }

    public void deleteDeveloper(Developer developer){
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        session.delete(developer);
//        transaction.commit();
//        session.close();
    }
}
