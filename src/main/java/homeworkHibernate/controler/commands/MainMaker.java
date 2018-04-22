package homeworkHibernate.controler.commands;

import homeworkHibernate.controler.main.JDBCStorage;

class MainMaker {
    static JDBCStorage jdbcStorage;
    MainMaker(JDBCStorage initJdbcStorage){
        if (jdbcStorage == null)
        jdbcStorage = initJdbcStorage;
    }
}
