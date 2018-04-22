package homeworkHibernate.view.DialogImplementation;

import homeworkHibernate.model.Company;
import homeworkHibernate.view.DialogService;

import java.util.Arrays;

public class CompanyDialog extends GeneralDialog implements CaseDialog {
    @Override
    public void createDialog() {
        createDialog(new Company());
    }

    @Override
    public void readDialog() {
        readDialog(Company.class);
    }

    @Override
    public void listDialog() { super.listDialog(Company.class);}

    @Override
    public void updateDialog() {
        updateDialog(Company.class);
    }

    @Override
    public void deleteDialog() {
        deleteDialog(Company.class);
    }

    @Override
    public void eraseDialog() {
        eraseDialog(Company.class);
    }

    private void fillCompany(Company c){
        String[] param = DialogService.getData(
                Arrays.copyOfRange(Company.getParam(), 1, Company.getParam().length)
        );

        c.setCompany_name(param[0]);
        c.setAdress(param[1]);
    }
}
