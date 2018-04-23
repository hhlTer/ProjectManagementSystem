package homeworkHibernate.controler.commands;

import homeworkHibernate.model.Project;
import homeworkHibernate.model.mapping.ProjectCost;

public interface AdvancedSQL{
    ProjectCost getDevelopersCost(Project project);
    void showDevelopersJobs(Project project);
    void showDevelopersSkill(String skill, String colName);
    void showDevelopersGrade(String skill, String colName);
    void showCountOfDeveloperByProject(Project project);
}
