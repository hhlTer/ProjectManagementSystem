package controler.commands;

import controler.main.JDBCStorage;
import enumerated.TypeCRUD;
import enumerated.TypeTable;
import model.Developer;
import model.Project;
import view.Table;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class ProjectSQLMaker extends MainMaker implements SQLMaker<Project>, AdvancedSQL {

    public ProjectSQLMaker(JDBCStorage initJdbcStorage) {
        super(initJdbcStorage);
    }


    @Override
    public Project selectFromTableById(long id) throws SQLException {
        Project project = new Project();
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.projects)
                .get(TypeCRUD.READ_BY_ID);

        preparedStatement.setLong(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (!rs.first()){
            System.out.println("Wrong id");
            return null;
        } else {
            project.setId(id);
            project.setProject_name(rs.getString(Project.getParam()[1]));
            project.setDescription(rs.getString(Project.getParam()[2]));
            project.setCost(rs.getBigDecimal(Project.getParam()[3]));
        }
        return project;
    }

    @Override
    public void insertIntoTable(Project project) throws SQLException {
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.projects)
                .get(TypeCRUD.CREATE);

//        preparedStatement.setLong(1, developer.getId());
        preparedStatement.setString(1, project.getProject_name());
        preparedStatement.setString(2, project.getDescription());
        preparedStatement.setBigDecimal(3, project.getCost());

        preparedStatement.executeUpdate();
    }

    @Override
    public void updateInTable(Project project) throws SQLException {
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.projects)
                .get(TypeCRUD.UPDATE);

//        preparedStatement.setLong(1, developer.getId());
        preparedStatement.setString(1, project.getProject_name());
        preparedStatement.setString(2, project.getDescription());
        preparedStatement.setBigDecimal(3, project.getCost());
        preparedStatement.setLong(4, project.getId());

        preparedStatement.executeUpdate();
    }

    @Override
    public void eraseTable() throws SQLException {
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.projects)
                .get(TypeCRUD.ERASE);

        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteCortege(long id) throws SQLException {
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.projects)
                .get(TypeCRUD.DELETE);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public ArrayList<Project> getAllDataTable() throws SQLException {
        ArrayList<Project> projectArrayList = new ArrayList<>();
        PreparedStatement ps = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.projects)
                .get(TypeCRUD.READ);

        String[] param = Project.getParam();

        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Project project = new Project();
            project.setId(rs.getLong(param[0]));
            project.setProject_name(rs.getString(param[1]));
            project.setDescription(rs.getString(param[2]));
            project.setCost(rs.getBigDecimal(param[3]));

            projectArrayList.add(project);
        }
        return projectArrayList;
    }

    @Override
    public void getSalary(Project project) {
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
}
