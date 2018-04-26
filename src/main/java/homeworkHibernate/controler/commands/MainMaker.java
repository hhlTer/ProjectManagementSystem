package homeworkHibernate.controler.commands;

import org.hibernate.Session;

class MainMaker {
    static Session session;
    MainMaker(Session initSession){
        if (session == null)
        session = initSession;
    }
}
