package homeworkHibernate.controler.commands;

import homeworkHibernate.model.results.ProjectCost;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class Queries {
    public ProjectCost projectCost(int projectId, Session session){
        String query = "select new homeworkHibernate.model.mapping.ProjectCost(pd.project.project_name, sum (pd.developer.salary)) " +
                "from ProjectDeveloper pd " +
                "where pd.project.id =: projectId";
        Query hibernateQuery = session.createQuery(query);
        hibernateQuery.setParameter("projectId", (long)projectId);
        return (ProjectCost) hibernateQuery.getSingleResult();
    }
}
