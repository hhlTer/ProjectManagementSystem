package homeworkHibernate.controler.commands;

import homeworkHibernate.controler.main.JDBCStorage;
import homeworkHibernate.model.Developer;
import homeworkHibernate.model.Project;
import homeworkHibernate.model.mapping.results.DevelopersOfProject;
import homeworkHibernate.model.mapping.results.ProjectCost;
import homeworkHibernate.view.Table;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class AdvanceHQLMaker extends MainMaker implements AdvancedSQL{

    public AdvanceHQLMaker(JDBCStorage initJdbcStorage) {
        super(initJdbcStorage);
    }
    public AdvanceHQLMaker(Session session) {
        super(session);
    }

    @Override
    public ProjectCost getDevelopersCost(Project project) {
        String query = "select new homeworkHibernate.model.mapping.results.ProjectCost" +
                "(pd.project.project_name, sum (pd.developer.salary)) " +
                "from ProjectDeveloper pd " +
                "where pd.project.id =: projectId";
        Query hibernateQuery = session.createQuery(query);
        hibernateQuery.setParameter("projectId", project.getId());
        return (ProjectCost) hibernateQuery.getSingleResult();
    }

    @Override
    public Project fillDeveloperSet(Project project){
        List<Developer> developers = session.createQuery("from Developer d " +
                "left join ProjectDeveloper pd on pd.developer.id = d.id " +
                "where pd.project.id = " + project.getId())
                .getResultList();

        Set<Developer> developersSet = developers.stream().collect(Collectors.toCollection(HashSet::new));
        project.setDevelopers(developersSet);
//        DevelopersOfProject developersOfProject = new DevelopersOfProject(developersSet);
//        developersOfProject.setProject(project);
        return project;
    }

    @Override
    public void showDevelopersSkill(String skill, String skillColName) {
        try {
            PreparedStatement ps = jdbcStorage.getListDeveloperAsSkill(skillColName);
            ps.setString(1, skill);
            ResultSet rs = ps.executeQuery();
            ArrayList<String[]> strings = new ArrayList<>();
            if (rs.first()){
                while (rs.next()){
                    strings.add(new String[]{
                            rs.getString(1),
                            rs.getString(2)
                    });
                }
            }
            String[] column = new String[]{
                    "Developer name",
                    "Skill"
            };
            Table.printAsTable(column, strings);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showDevelopersGrade(String grade, String skillColName) {
        showDevelopersSkill(grade, skillColName);
    }

    @Override
    public void showCountOfDeveloperByProject(Project project) {
        try {
            PreparedStatement ps = jdbcStorage.getCountDevelopersOfProject();
            ps.setLong(1, project.getId());
            ResultSet rs = ps.executeQuery();
            String[] data = new String[1];
            if (rs.first()){
                    data  = new String[]{
                            rs.getString(1),
                            String.valueOf(rs.getInt(2))
                    };
            }
            String[] column = new String[]{
                    "Project",
                    "Count of developers"
            };
            Table.printAsTable(column, data);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
