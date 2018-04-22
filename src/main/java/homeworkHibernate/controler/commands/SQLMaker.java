package homeworkHibernate.controler.commands;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SQLMaker<T> {
    T selectFromTableById(long id) throws SQLException;
    void insertIntoTable(T t) throws SQLException;
    void updateInTable(T t) throws SQLException;
    void eraseTable() throws SQLException;
    void deleteCortege(long id) throws SQLException;
    ArrayList<T> getAllDataTable() throws SQLException;


//    void handlerCommand(JDBCStorage jdbcStorage, TypeTable tableName, String[] param, String[] data);
}
