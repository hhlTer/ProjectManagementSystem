package homeworkHibernate.controler.commands;

import homeworkHibernate.model.Project;

public interface AdvancedSQL{
    void showDevelopersCost(Project project);
    void showDevelopersJobs(Project project);
    void showDevelopersSkill(String skill, String colName);
    void showDevelopersGrade(String skill, String colName);
    void showCountOfDeveloperByProject(Project project);
}
