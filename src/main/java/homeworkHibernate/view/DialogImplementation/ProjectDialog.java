package homeworkHibernate.view.DialogImplementation;

import homeworkHibernate.model.Project;
import homeworkHibernate.view.dialogServise.DialogService;

import java.math.BigDecimal;
import java.util.Arrays;

public class ProjectDialog extends GeneralDialog implements CaseDialog{

    @Override
    public void createDialog() {
        createDialog(new Project());
    }

    @Override
    public void readDialog() {
        readDialog(Project.class);
    }

    @Override
    public void listDialog() { super.listDialog(Project.class);}

    @Override
    public void updateDialog() {
        updateDialog(Project.class);
    }

    @Override
    public void deleteDialog() {
        deleteDialog(Project.class);
    }

    @Override
    public void eraseDialog() {
        eraseDialog(Project.class);
    }

    static void fillProject(Project p){
        String[] param = DialogService.getData(
                Arrays.copyOfRange(Project.getParam(), 1, Project.getParam().length)
        );

        BigDecimal cost = BigDecimal.valueOf(Long.parseLong(param[2]));
        p.setProject_name(param[0]);
        p.setDescription(param[1]);
        p.setCost(cost);
    }
}
