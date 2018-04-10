package controler.commands;

import controler.main.JDBCStorage;
import model.Project;
import view.Table;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdvanceSQLMaker extends MainMaker implements AdvancedSQL{
    public AdvanceSQLMaker(JDBCStorage initJdbcStorage) {
        super(initJdbcStorage);
    }

    @Override
    public void showDevelopersCost(Project project) {
        try {
            PreparedStatement ps = jdbcStorage.getAllSalaryPrepareStatement();
            ps.setLong(1, project.getId());

            String[] column = new String[]{
                    "id",
                    "project",
                    "sum salary"
            };
            String[] param = new String[3];

            ResultSet rs = ps.executeQuery();
            if (rs.first()){
                param[0] = String.valueOf(rs.getLong(1));
                param[1] = rs.getString(2);
                param[2] = String.valueOf(rs.getBigDecimal(3));
            }
            Table.printAsTable(column, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showDevelopersJobs(Project project){
        try {
            PreparedStatement ps = jdbcStorage.getListDevelopersOfProject();
            ps.setLong(1, project.getId());
            ResultSet rs = ps.executeQuery();

            ArrayList<String[]> strings = new ArrayList<>();
            if (rs.first()){
                while (rs.next()){
                    strings.add(new String[]{
                            String.valueOf(rs.getLong(1)),
                            rs.getString(2)
                    });
                }
            }
            String[] column = new String[]{
                    "id",
                    "Developer name"
            };
            Table.printAsTable(column, strings);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
