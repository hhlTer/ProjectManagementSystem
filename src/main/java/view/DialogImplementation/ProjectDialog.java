package view.DialogImplementation;

import controler.commands.DoItHibernate;
import controler.commands.HiberInterface;
import controler.commands.ProjectSQLMaker;
import controler.commands.SQLMaker;
import model.GenerallyTable;
import model.Project;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import view.DialogService;
import view.MainJDBC;
import view.Table;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
