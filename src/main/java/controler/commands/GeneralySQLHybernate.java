package controler.commands;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class GeneralySQLHybernate implements SQLMaker {
    private static SessionFactory sessionFactory;
    @Override
    public Object selectFromTableById(long id) throws SQLException {
        Object developer = null;
        Session session = sessionFactory.openSession();
        developer = session.get(Object.class, id);
        session.close();
        return developer;
    }

    @Override
    public void insertIntoTable(Object o) throws SQLException {

    }

    @Override
    public void updateInTable(Object o) throws SQLException {

    }

    @Override
    public void eraseTable() throws SQLException {

    }

    @Override
    public void deleteCortege(long id) throws SQLException {

    }

    @Override
    public ArrayList getAllDataTable() throws SQLException {
        return null;
    }
}
