package controler.commands;

import enumerated.TypeSkill;
import model.Project;

public interface AdvancedSQL{
    void showDevelopersCost(Project project);
    void showDevelopersJobs(Project project);
    void showDevelopersSkill(String skill);
}
