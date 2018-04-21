package view.DialogImplementation;

import controler.commands.DoItHibernate;
import controler.commands.HiberInterface;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;


public class GeneralDialog {
    private static HiberInterface<GenerallyTable> hiberSQLMaker = new DoItHibernate<>();

    private Project project;
    private Developer developer;
    private Company company;
    private Customer customer;

    private ArrayList<Project> projects;
    private ArrayList<Developer> developers;
    private ArrayList<Customer> customers;
    private ArrayList<Company> companies;

    private GenerallyTable gt;

    public void createDialog(GenerallyTable gt) {
            fillTable(gt);
            try {
                hiberSQLMaker.insertIntoTable(gt);
                System.out.println("Done!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void readDialog() {

    }

    public void listDialog() {

    }

    public void updateDialog() {

    }

    public void deleteDialog() {

    }

    public void eraseDialog() {

    }

    private void fillTable(GenerallyTable gt){
        if (gt instanceof Project){
            ProjectDialog.fillProject((Project) gt);
        }
        if (gt instanceof Developer){
            DeveloperDialog.fillDeveloper((Developer) gt);
        }
    }
}
