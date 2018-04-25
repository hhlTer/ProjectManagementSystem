package homeworkHibernate.model.results;

import homeworkHibernate.model.Developer;
import homeworkHibernate.model.Project;

import java.util.Set;

public class DevelopersOfProject {
    private Project project;
    private Set<Developer> developers;


    public DevelopersOfProject(Set<Developer> developers) {
//        this.project = project;
        this.developers = developers;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }
    public Project getProject() {
        return project;
    }
    public Set<Developer> getDevelopers() {
        return developers;
    }
}
