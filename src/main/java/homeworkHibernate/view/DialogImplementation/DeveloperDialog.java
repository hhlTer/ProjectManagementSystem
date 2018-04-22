package homeworkHibernate.view.DialogImplementation;

import homeworkHibernate.model.Developer;
import homeworkHibernate.view.DialogService;

import java.math.BigDecimal;
import java.util.Arrays;

public class DeveloperDialog extends GeneralDialog implements CaseDialog {
    @Override
    public void createDialog() {
        createDialog(new Developer());
    }

    @Override
    public void readDialog() {
        readDialog(Developer.class);
    }

    @Override
    public void listDialog() { super.listDialog(Developer.class);}

    @Override
    public void updateDialog() {
        updateDialog(Developer.class);
    }

    @Override
    public void deleteDialog() {
        deleteDialog(Developer.class);
    }

    @Override
    public void eraseDialog() {
        eraseDialog(Developer.class);
    }

    static void fillDeveloper(Developer developer){
        String[] param = DialogService.getData(
                Arrays.copyOfRange(Developer.getParam(), 1, Developer.getParam().length)
        );

        int age = Integer.parseInt(param[1]);
        boolean sex = param[2].equals("male");
        BigDecimal salary = BigDecimal.valueOf(Long.parseLong(param[3]));

        developer.setName(param[0]);
        developer.setAge(age);
        developer.setSex(sex);
        developer.setSalary(salary);
    }

}
