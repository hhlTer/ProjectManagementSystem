package view.DialogImplementation;

import controler.commands.CustomerSQLMaker;
import controler.commands.SQLMaker;
import model.Customer;
import model.Customer;
import view.DialogService;
import view.MainJDBC;
import view.Table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class CustomerDialog extends GeneralDialog implements CaseDialog {

    @Override
    public void createDialog() {
        createDialog(new Customer());
    }

    @Override
    public void readDialog() {
        readDialog(Customer.class);
    }

    @Override
    public void listDialog() { super.listDialog(Customer.class);}

    @Override
    public void updateDialog() {
        updateDialog(Customer.class);
    }

    @Override
    public void deleteDialog() {
        deleteDialog(Customer.class);
    }

    @Override
    public void eraseDialog() {
        eraseDialog(Customer.class);
    }

    static void fillCustomer(Customer c){
        String[] param = DialogService.getData(
                Arrays.copyOfRange(Customer.getParam(), 1, Customer.getParam().length)
        );

        c.setCustomer_name(param[0]);
        c.setAdress(param[1]);
    }
}
