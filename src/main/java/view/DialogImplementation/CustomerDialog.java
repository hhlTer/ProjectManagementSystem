package view.DialogImplementation;

import model.Customer;
import view.DialogService;

import java.util.Arrays;

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
        c.setAddress(param[1]);
    }
}
