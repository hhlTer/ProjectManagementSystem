package homeworkHibernate.controler.commands;

import homeworkHibernate.controler.main.JDBCStorage;
import org.hibernate.Session;

class MainMaker {
    static JDBCStorage jdbcStorage;
    static Session session;
    MainMaker(JDBCStorage initJdbcStorage){
        if (jdbcStorage == null)
        jdbcStorage = initJdbcStorage;
    }
    MainMaker(Session initSession){
        if (session == null)
        session = initSession;
    }
}
