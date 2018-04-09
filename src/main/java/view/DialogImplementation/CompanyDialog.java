package view.DialogImplementation;

import controler.commands.CompanySQLMaker;
import controler.commands.SQLMaker;
import model.Company;
import view.DialogService;
import view.MainJDBC;
import view.Table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class CompanyDialog implements CaseDialog {
    
    private SQLMaker<Company> companySQLMaker = new CompanySQLMaker(MainJDBC.storage);
    private Company company;
    private ArrayList<Company> companies;
    
    @Override
    public void createDialog() {
        company = new Company();
        fillCompany(company);
        try {
            companySQLMaker.insertIntoTable(company);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readDialog() {
        while (true) {
            long id = DialogService.getLongId();
            try {
                company = companySQLMaker.selectFromTableById(id);
                Table.printAsTable(Company.getParam(), company.getAll());
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Company not found in table");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void listDialog() {
        try {
            companies = companySQLMaker.getAllDataTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> tempArray = new ArrayList<>(companies.size());
        for (Company c:
                companies) {
            tempArray.add(c.getAll());
        }
        Table.printAsTable(Company.getParam(), tempArray);
    }

    @Override
    public void updateDialog() {
        readDialog();
        fillCompany(company);
        try {
            companySQLMaker.updateInTable(company);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDialog() {
        readDialog();
        System.out.println("Delete company?\n[y]es/[n]o?");
        char c = DialogService.getAnswer("yn");
        if (c == 'y') {
            try {
                companySQLMaker.deleteCortege(company.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void eraseDialog() {
        System.out.println("Erase table?\n[Y]es/[N]o?");
        char answer = DialogService.getAnswer("yn");
        if (answer == 'y'){
            try {
                companySQLMaker.eraseTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillCompany(Company c){
        String[] param = DialogService.getData(
                Arrays.copyOfRange(Company.getParam(), 1, Company.getParam().length)
        );

        c.setCompany_name(param[0]);
        c.setAdress(param[1]);
    }
}
