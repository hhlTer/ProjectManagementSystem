package view;

import controler.commands.DeveloperSQLMaker;
import controler.commands.SQLMaker;
import controler.main.JDBCStorage;
import model.Developer;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainJDBC {
    public static void main(String[] args) throws SQLException {

        JDBCStorage storage = new JDBCStorage();
        SQLMaker d = new DeveloperSQLMaker(storage);

        ArrayList developers = new ArrayList<>();
        developers = d.getAllDataTable();
        Table.printAsTable(developers);
    }
}
