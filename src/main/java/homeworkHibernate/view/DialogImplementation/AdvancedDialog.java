package homeworkHibernate.view.DialogImplementation;

import homeworkHibernate.controler.main.SessionGenerate;
import homeworkHibernate.controler.commands.AdvanceHQLMaker;
import homeworkHibernate.controler.commands.AdvancedHQL;
import homeworkHibernate.controler.commands.SQLMaker;
import homeworkHibernate.model.tables.Developer;
import homeworkHibernate.model.tables.GenerallyTable;
import homeworkHibernate.model.tables.Project;
import homeworkHibernate.model.results.ProjectCost;
import homeworkHibernate.view.dialogServise.DialogService;
import homeworkHibernate.view.dialogServise.Table;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;

public class AdvancedDialog {

    private SessionFactory sessionFactory = SessionGenerate.getInstance().getSessionFactory();
//        private AdvancedHQL advancedSQL = new AdvanceHQLMaker();
        private AdvancedHQL advancedSQL = new AdvanceHQLMaker(sessionFactory.openSession());
        private CaseDialog dialog = new ProjectDialog();
        private Project project;

    public void getData(){
        boolean flag = true;
        do {
            System.out.println("Choose action:");
            System.out.println(" 1 - salary all developers of project");
            System.out.println(" 2 - list of all developers of project");
            System.out.println(" 3 - list of all developers as skill");
            System.out.println(" 4 - list of all developers as grade");
            System.out.println(" 5 - list count of all developers in project");
            System.out.println(" 0 - Exit");

            String[] column;
            ArrayList<String[]> param = new ArrayList<>();
            Set<Developer> developers;
            char ans = DialogService.getAnswer("123450");
            switch (ans){
                case '1':
                    caseProject();
                    ProjectCost projectCost = advancedSQL.getDevelopersCost(project);
                    Table.printAsTable("project name", "cost",
                            projectCost.getProjectName(), String.valueOf(projectCost.getCost()));
                    break;
                case '2':
                    caseProject();
                    Project developersOfProject = advancedSQL.fillDeveloperSet(project);
                    column = new String[]{"Project" , "Developer"};

//                    ArrayList<String[]> param = developersOfProject.getDevelopers().stream()
//                        .map(s -> new String[]{developersOfProject.getProject_name(), s.getName()})
//                            .collect(Collectors.toCollection(ArrayList::new));

                    param = new ArrayList<>();
                    for (Developer d:
                         developersOfProject.getDevelopers()) {
                        param.add(new String[]{project.getProject_name(), d.getName()});
                    }
                    Table.printAsTable(column, param);
                    break;
                case '3':
                    String skill = caseSkill();
                    developers = advancedSQL.showDevelopersSkill(skill);
                    column = new String[]{"Skill", "Developer"};
                    for (Developer d:
                            developers) {
                        param.add(new String[]{skill, d.getName()});
                    }
                    Table.printAsTable(column, param);
                    break;
                case '4':
                    String grade = caseGrade();
                    developers = advancedSQL.showDevelopersGrade(grade);
                    column = new String[]{"Skill", "Developer"};
                    for (Developer d:
                            developers) {
                        param.add(new String[]{grade, d.getName()});
                    }
                    Table.printAsTable(column, param);
                    break;
                case '5':
                    caseProject();
                    long result = advancedSQL.showCountOfDeveloperByProject(project);
                    Table.printAsTable("Count of developer: ", String.valueOf(result));
                    break;
                case '0':
                    flag = false;
                    break;
            }
        }while (flag);
    }

    private void caseProject(){
        try {
//            ArrayList<Project> projects = projectSQLMaker.getAllDataTable();
//            ArrayList<String[]> strings = new ArrayList<>();
//            for (Project p:
//                 projects) {
//                strings.add(p.getAll());
//            }

            ArrayList<GenerallyTable> gts = GeneralDialog.hiberSQLMaker.getAllDataTable(Project.class);
            Table.printAsTable(gts);
            for (;;) {
                    System.out.println("Choose a project: ");
                    long id = DialogService.getLongId();
                    project = (Project) GeneralDialog.hiberSQLMaker.getFromTableById(Project.class, id);
                    break;
                 }
            } catch (NoSuchElementException e) {
                System.out.println("Project not found in table");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    private String caseSkill(){
        System.out.println("Chose available skill");
        System.out.println("[1] - Java\n[2] - C#\n[3] - C++\n[4] - JavaScript");
        char ans = DialogService.getAnswer("1234");
        return ans == '1' ? "java" :
               ans == '2' ? "c#" :
               ans == '3' ? "c++" :
                            "js";
    }
    private String caseGrade(){
        System.out.println("Chose developers grade");
        System.out.println("[1] - junior\n[2] - middle\n[3] - senior\n");
        char ans = DialogService.getAnswer("1234");
        return ans == '1' ? "junior" :
               ans == '2' ? "middle" :
                            "senior";
    }
}
