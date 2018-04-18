package controler.commands;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HyberInterface<T> {
        T selectFromTableById(Class<?> clazz, long id) throws SQLException;
        void insertIntoTable(T t) throws SQLException;
        void updateInTable(Class<T> t) throws SQLException;
        void eraseTable() throws SQLException;
        void deleteCortege(long id) throws SQLException;
        ArrayList<Class<T>> getAllDataTable() throws SQLException;
}