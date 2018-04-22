package homeworkHibernate.controler.commands;

import homeworkHibernate.controler.SessionGenerate;
import homeworkHibernate.model.GenerallyTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoItHibernate<T extends GenerallyTable> implements HiberInterface<T> {

    private static SessionFactory sessionFactory = SessionGenerate.getInstance().getSessionFactory();

//    public DoItHibernate() {
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure() // configures settings from hibernate.cfg.xml
//                .build();
//        try {
//            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        } catch (Exception e) {
//// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
//// so destroy it manually.
//            StandardServiceRegistryBuilder.destroy(registry);
//        }
//    }

    @Override
    public T getFromTableById(Class<? extends GenerallyTable> clazz, long id) throws SQLException {
        T t = null;
        Session session = openSession();
        GenerallyTable table = session.get(clazz, id);
        t = (T) table;
        session.close();
        return t;
    }

    @Override
    public void insertIntoTable(T t) throws SQLException {
        Session session = openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateInTable(T t) throws SQLException {
        Session session = openSession();
        Transaction transaction = session.beginTransaction();
        session.update(t);
        transaction.commit();
        session.close();
    }

    @Override
    public void eraseTable(Class<? extends GenerallyTable> clazz) throws SQLException {
        Session session = openSession();
        Transaction transaction = session.beginTransaction();
        String q = "delete from " + clazz.getName().toLowerCase();
        Query query = session.createQuery(q);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteCortege(T t) throws SQLException {
        Session session = openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(t);
        transaction.commit();
        session.close();
    }

    @Override
    public ArrayList<T> getAllDataTable(Class<? extends GenerallyTable> clazz) throws SQLException {
        Session session = openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery((Class<T>) clazz);
        Root<T> root = criteriaQuery.from((Class<T>)clazz);
        criteriaQuery.select(root);
        Query<T> query = session.createQuery(criteriaQuery);
        return (ArrayList<T>) query.getResultList();
    }

    private Session openSession(){
        return SessionGenerate.getInstance().createSession();
    }

    private Session openTransactSession() {
        Session session = openSession();
        session.beginTransaction();
        return session;
    }

    private void closeTransactSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }
}
