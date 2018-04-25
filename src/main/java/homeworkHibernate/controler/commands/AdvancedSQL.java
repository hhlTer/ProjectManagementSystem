package homeworkHibernate.controler.commands;

import homeworkHibernate.model.Developer;
import homeworkHibernate.model.Project;
import homeworkHibernate.model.mapping.results.DevelopersOfProject;
import homeworkHibernate.model.mapping.results.ProjectCost;

import java.util.Set;

public interface AdvancedSQL{
    ProjectCost getDevelopersCost(Project project);
    Project fillDeveloperSet(Project project);
    Set<Developer> showDevelopersSkill(String skill);
    void showDevelopersGrade(String skill, String colName);
    void showCountOfDeveloperByProject(Project project);
}
