package homeworkHibernate.controler.commands;

import homeworkHibernate.model.Project;
import homeworkHibernate.model.mapping.results.DevelopersOfProject;
import homeworkHibernate.model.mapping.results.ProjectCost;

public interface AdvancedSQL{
    ProjectCost getDevelopersCost(Project project);
    Project fillDeveloperSet(Project project);
    void showDevelopersSkill(String skill, String colName);
    void showDevelopersGrade(String skill, String colName);
    void showCountOfDeveloperByProject(Project project);
}
