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
    private Project project;
    private ArrayList<Project> projects;

    private static HiberInterface<Project> hiberSQLMaker = new DoItHibernate<>();

    @Override
    public void createDialog() {
        createDialog(new Project());
    }

    @Override
    public void readDialog() {
        while (true) {
            long id = DialogService.getLongId();
            try {
                project = hiberSQLMaker.getFromTableById(Project.class, id);
                Table.printAsTable(Project.getParam(), project.getAll());
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Project not found in table");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void listDialog() {
        try {
            projects = hiberSQLMaker.getAllDataTable(Project.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Table.printAsTable(projects);
    }

    @Override
    public void updateDialog() {
        readDialog();
        fillProject(project);
        try {
            hiberSQLMaker.updateInTable(project);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDialog() {
        readDialog();
        System.out.println("Delete project?\n[y]es/[n]o?");
        char c = DialogService.getAnswer("yn");
        if (c == 'y') {
            try {
                hiberSQLMaker.deleteCortege(project);
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
                hiberSQLMaker.eraseTable("project");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
