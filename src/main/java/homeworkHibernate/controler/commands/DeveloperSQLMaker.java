package homeworkHibernate.controler.commands;

import homeworkHibernate.controler.main.JDBCStorage;
import homeworkHibernate.enumerated.TypeCRUD;
import homeworkHibernate.enumerated.TypeTable;
import homeworkHibernate.model.Developer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeveloperSQLMaker extends MainMaker implements SQLMaker<Developer> {

    public DeveloperSQLMaker(JDBCStorage initJdbcStorage) {
        super(initJdbcStorage);
    }

    @Override
    public Developer selectFromTableById(long id) throws SQLException {
        Developer developer = new Developer();
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.developers)
                .get(TypeCRUD.READ_BY_ID);

        preparedStatement.setLong(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (!rs.first()){
            System.out.println("Wrong id");
            return null;
        } else {
            developer.setId(id);
            developer.setName(rs.getString("first_name"));
            developer.setAge(rs.getInt("age"));
            developer.setSex(rs.getBoolean("sex"));
            developer.setSalary(rs.getBigDecimal("salary"));
        }
        return developer;
    }

    @Override
    public void insertIntoTable(Developer developer) throws SQLException{
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.developers)
                .get(TypeCRUD.CREATE);
//        preparedStatement.setLong(1, developer.getId());
        preparedStatement.setString(1, developer.getName());
        preparedStatement.setInt(2, developer.getAge());
        preparedStatement.setBoolean(3, developer.isSex());
        preparedStatement.setBigDecimal(4, developer.getSalary());

        preparedStatement.executeUpdate();
    }

    @Override
    public void updateInTable(Developer developer) throws SQLException {
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.developers)
                .get(TypeCRUD.UPDATE);
        preparedStatement.setString(1, developer.getName());
        preparedStatement.setInt(2, developer.getAge());
        preparedStatement.setBoolean(3, developer.isSex());
        preparedStatement.setBigDecimal(4, developer.getSalary());
        preparedStatement.setLong(5, developer.getId());

        preparedStatement.executeUpdate();
    }

    @Override
    public void eraseTable() throws SQLException {
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.developers)
                .get(TypeCRUD.ERASE);
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteCortege(long id) throws SQLException {
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.developers)
                .get(TypeCRUD.DELETE);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public ArrayList<Developer> getAllDataTable() throws SQLException{
        ArrayList<Developer> developerArrayList = new ArrayList<>();
        PreparedStatement ps = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.developers)
                .get(TypeCRUD.READ);

        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Developer developer = new Developer();
            developer.setId(rs.getLong("id"));
            developer.setName(rs.getString("first_name"));
            developer.setAge(rs.getInt("age"));
            developer.setSex(rs.getBoolean("sex"));
            developer.setSalary(rs.getBigDecimal("salary"));
            developerArrayList.add(developer);
        }
        //TODO get all data in arrayList;
        return developerArrayList;
    }
}
