package controler.commands;

import controler.main.JDBCStorage;
import enumerated.TypeCRUD;
import enumerated.TypeTable;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static controler.commands.MainMaker.jdbcStorage;

public class CustomerSQLMaker extends MainMaker implements SQLMaker<Customer> {

    public CustomerSQLMaker(JDBCStorage initJdbcStorage) {
        super(initJdbcStorage);
    }

    @Override
    public Customer selectFromTableById(long id) throws SQLException {
        Customer customer = new Customer();
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.customers)
                .get(TypeCRUD.READ_BY_ID);

        preparedStatement.setLong(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (!rs.first()){
            System.out.println("Wrong id");
            return null;
        } else {
            customer.setId(id);
            customer.setCustomer_name(rs.getString(Customer.getParam()[1]));
            customer.setAdress(rs.getString(Customer.getParam()[2]));
        }
        return customer;
    }

    @Override
    public void insertIntoTable(Customer customer) throws SQLException {
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.customers)
                .get(TypeCRUD.CREATE);

//        preparedStatement.setLong(1, developer.getId());
        preparedStatement.setString(1, customer.getCustomer_name());
        preparedStatement.setString(2, customer.getAdress());

        preparedStatement.executeUpdate();
    }

    @Override
    public void updateInTable(Customer customer) throws SQLException {
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.customers)
                .get(TypeCRUD.UPDATE);

//        preparedStatement.setLong(1, developer.getId());
        preparedStatement.setString(1, customer.getCustomer_name());
        preparedStatement.setString(2, customer.getAdress());
        preparedStatement.setLong(3, customer.getId());

        preparedStatement.executeUpdate();
    }

    @Override
    public void eraseTable() throws SQLException {
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.customers)
                .get(TypeCRUD.ERASE);

        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteCortege(long id) throws SQLException {
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.customers)
                .get(TypeCRUD.DELETE);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public ArrayList<Customer> getAllDataTable() throws SQLException {
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        PreparedStatement ps = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.customers)
                .get(TypeCRUD.READ);

        String[] param = Customer.getParam();

        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Customer customer = new Customer();
            customer.setId(rs.getLong(param[0]));
            customer.setCustomer_name(rs.getString(param[1]));
            customer.setAdress(rs.getString(param[2]));

            customerArrayList.add(customer);
        }
        return customerArrayList;    }
}
