package homeworkHibernate.controler.commands;

import homeworkHibernate.model.tables.Developer;
import homeworkHibernate.model.tables.Project;
import homeworkHibernate.model.results.ProjectCost;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.*;
import java.util.stream.Collectors;

public class AdvanceHQLMaker extends MainMaker implements AdvancedHQL {

    public AdvanceHQLMaker(Session session) {
        super(session);
    }

    @Override
    public ProjectCost getDevelopersCost(Project project) {
        String query = "select new homeworkHibernate.model.results.ProjectCost" +
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
                .getResultList();

        Set<Developer> developersSet = developers.stream().collect(Collectors.toCollection(HashSet::new));
        project.setDevelopers(developersSet);
        return project;
    }

    @Override
    public Set<Developer> showDevelopersSkill(String skill) {
        List<Developer> developers = session.createQuery("select d from Developer d " +
                "left join DeveloperSkill ds on ds.developer.id = d.id " +
                "where ds.skill.skills like :sk", Developer.class)
                .setParameter("sk", skill)
                .getResultList();
        Set<Developer> developersSet = developers.stream().collect(Collectors.toCollection(HashSet::new));
        return developersSet;
    }

    @Override
    public Set<Developer> showDevelopersGrade(String grade) {
        List<Developer> developers = session.createQuery("select d from Developer d " +
                "left join DeveloperSkill ds on ds.developer.id = d.id " +
                "where ds.skill.grade like :sg", Developer.class)
                .setParameter("sg", grade)
                .getResultList();
        Set<Developer> developersSet = developers.stream().collect(Collectors.toCollection(HashSet::new));
        return developersSet;
    }

    @Override
    public long showCountOfDeveloperByProject(Project project) {
        long countDeveloper = session.createQuery("select count (pd.developer) from ProjectDeveloper pd " +
                "where pd.project.id = :pId ", Long.class)
                .setParameter("pId", project.getId())
                .getSingleResult();
        return countDeveloper;
    }
}
