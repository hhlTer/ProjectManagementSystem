package view.DialogImplementation;

import controler.commands.CustomerSQLMaker;
import controler.commands.SQLMaker;
import model.Customer;
import view.DialogService;
import view.MainJDBC;
import view.Table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class CustomerDialog implements CaseDialog {

    private SQLMaker<Customer> customerSQLMaker = new CustomerSQLMaker(MainJDBC.storage);
    private Customer customer;
    private ArrayList<Customer> customers;

    @Override
    public void createDialog() {
        customer = new Customer();
        fillCustomer(customer);
        try {
            customerSQLMaker.insertIntoTable(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readDialog() {
        while (true) {
            long id = DialogService.getLongId();
            try {
                customer = customerSQLMaker.selectFromTableById(id);
                Table.printAsTable(Customer.getParam(), customer.getAll());
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Customer not found in table");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void listDialog() {
        try {
            customers = customerSQLMaker.getAllDataTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> tempArray = new ArrayList<>(customers.size());
        for (Customer c:
                customers) {
            tempArray.add(c.getAll());
        }
        Table.printAsTable(Customer.getParam(), tempArray);
    }

    @Override
    public void updateDialog() {
        readDialog();
        fillCustomer(customer);
        try {
            customerSQLMaker.updateInTable(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDialog() {
        readDialog();
        System.out.println("Delete customer?\n[y]es/[n]o?");
        char c = DialogService.getAnswer("yn");
        if (c == 'y') {
            try {
                customerSQLMaker.deleteCortege(customer.getId());
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
                customerSQLMaker.eraseTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void fillCustomer(Customer c){
        String[] param = DialogService.getData(
                Arrays.copyOfRange(Customer.getParam(), 1, Customer.getParam().length)
        );

        c.setCustomer_name(param[0]);
        c.setAdress(param[1]);
    }
}
