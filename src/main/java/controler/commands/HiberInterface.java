package controler.commands;

import model.GenerallyTable;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HiberInterface<T extends GenerallyTable> {
        T getFromTableById(Class<? extends GenerallyTable> clazz, long id) throws SQLException;
        void insertIntoTable(T t) throws SQLException;
        void updateInTable(T t) throws SQLException;
        void eraseTable(Class<? extends GenerallyTable> tableName) throws SQLException;
        void deleteCortege(T t) throws SQLException;
        ArrayList<T> getAllDataTable(Class<? extends GenerallyTable> clazz) throws SQLException;
}