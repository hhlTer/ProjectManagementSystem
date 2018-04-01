package controler;

import model.Developer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Temp {
    public static void main(String[] args) {
        JDBCStorage storage = new JDBCStorage();
        Developer developer = storage.getDeveloperById(2L);
        System.out.println(developer);
    }

    public Developer getDeveloperById(Long id){
        String select = "SELECT * FROM developers WHERE ID = " + id;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(select);
            if (resultSet.first()){
                Developer developer = new Developer();
                developer.setId(resultSet.getLong(1));
                developer.setName(resultSet.getString(2));
                developer.setAge(resultSet.getInt(3));
                developer.setSex(resultSet.getBoolean(4));
                developer.setSalary(resultSet.getBigDecimal(5));
                return developer;
            }else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (resultSet != null) resultClose(resultSet);
        }
    }

    private void resultClose(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

}
