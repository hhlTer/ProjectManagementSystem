package controler.commands;

import controler.main.JDBCStorage;
import model.Project;

public interface AdvancedSQL{
    void showDevelopersCost(Project project);
    void showDevelopersJobs(Project project);
}
