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
        List<Developer> developers = session.createQuery("select d from Developer d " +
                "left join ProjectDeveloper pd on pd.developer.id = d.id " +
                "where pd.project.project_name = 'MainFM'", Developer.class)
//                .setParameter("pId", project.getId())
                .getResultList();

        Set<Developer> developersSet = developers.stream().collect(Collectors.toCollection(HashSet::new));
        project.setDevelopers(developersSet);
        return project;
    }

    @Override
    public Set<Developer> showDevelopersSkill(String skill) {
        List<Object> skills = session.createQuery("select s.skills from Skill s", Object.class)
                .getResultList();
        List<Developer> developers = session.createQuery("select d from Developer d " +
                "left join DeveloperSkill ds on ds.developer.id = d.id " +
//                "left join Skill s on s.id = ds.skill.id " +
                "where ds.skill.skills like :sk", Developer.class)
                .setParameter("sk", skill)
                .getResultList();
        Set<Developer> developersSet = developers.stream().collect(Collectors.toCollection(HashSet::new));
        return developersSet;
//        return null;
    }

    @Override
    public void showDevelopersGrade(String grade, String skillColName) {
        showDevelopersSkill("");
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
