package homeworkHibernate.controler.commands;

import homeworkHibernate.controler.main.JDBCStorage;
import homeworkHibernate.enumerated.TypeCRUD;
import homeworkHibernate.enumerated.TypeTable;
import homeworkHibernate.model.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanySQLMaker extends MainMaker implements SQLMaker<Company> {

    public CompanySQLMaker(JDBCStorage initJdbcStorage) {
        super(initJdbcStorage);
    }

    @Override
    public Company selectFromTableById(long id) throws SQLException {
        Company company = new Company();
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.companies)
                .get(TypeCRUD.READ_BY_ID);

        preparedStatement.setLong(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (!rs.first()){
            System.out.println("Wrong id");
            return null;
        } else {
            company.setId(id);
            company.setCompany_name(rs.getString(Company.getParam()[1]));
            company.setAdress(rs.getString(Company.getParam()[2]));
        }
        return company;
    }

    @Override
    public void insertIntoTable(Company company) throws SQLException {
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.companies)
                .get(TypeCRUD.CREATE);

        preparedStatement.setString(1, company.getCompany_name());
        preparedStatement.setString(2, company.getAdress());

        preparedStatement.executeUpdate();
    }

    @Override
    public void updateInTable(Company company) throws SQLException {
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.companies)
                .get(TypeCRUD.UPDATE);

        preparedStatement.setString(1, company.getCompany_name());
        preparedStatement.setString(2, company.getAdress());
        preparedStatement.setLong(3, company.getId());

        preparedStatement.executeUpdate();

    }

    @Override
    public void eraseTable() throws SQLException {
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.companies)
                .get(TypeCRUD.ERASE);

        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteCortege(long id) throws SQLException {
        PreparedStatement preparedStatement = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.companies)
                .get(TypeCRUD.DELETE);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public ArrayList<Company> getAllDataTable() throws SQLException {
        ArrayList<Company> companies = new ArrayList<>();
        PreparedStatement ps = jdbcStorage.getPrepareStatementsMap()
                .get(TypeTable.companies)
                .get(TypeCRUD.READ);

        String[] param = Company.getParam();

        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Company company = new Company();
            company.setId(rs.getLong(param[0]));
            company.setCompany_name(rs.getString(param[1]));
            company.setAdress(rs.getString(param[2]));

            companies.add(company);
        }
        return companies;       }
}
