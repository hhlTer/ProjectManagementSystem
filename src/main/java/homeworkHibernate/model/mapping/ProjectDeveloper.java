package homeworkHibernate.model.mapping;

import homeworkHibernate.model.Developer;
import homeworkHibernate.model.Project;

import javax.persistence.*;

@Entity
@Table(name = "developer_project_mtm")
public class ProjectDeveloper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_developer")
    private Developer developer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project")
    private Project project;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Developer getDeveloper() {
        return developer;
    }
    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
}
