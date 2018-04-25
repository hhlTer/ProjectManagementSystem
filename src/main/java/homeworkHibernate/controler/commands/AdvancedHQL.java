package homeworkHibernate.controler.commands;

import homeworkHibernate.model.Developer;
import homeworkHibernate.model.Project;
import homeworkHibernate.model.results.ProjectCost;

import java.util.Set;

public interface AdvancedHQL {
    ProjectCost getDevelopersCost(Project project);
    Project fillDeveloperSet(Project project);
    Set<Developer> showDevelopersSkill(String skill);
    Set<Developer> showDevelopersGrade(String grade);
    long showCountOfDeveloperByProject(Project project);
}
