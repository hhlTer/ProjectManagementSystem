package homeworkHibernate.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "projects")
@Entity
public class Project implements GenerallyTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "project_name")
    private String project_name;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private BigDecimal cost;

    public void setId(long id) {
        this.id = id;
    }
    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public long getId() {
        return id;
    }
    public String getProject_name() {
        return project_name;
    }
    public String getDescription() {
        return description;
    }
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("Project :%s\ndescription: %s\ncost: %f", project_name, description, cost.doubleValue());
    }

    public static String[] getParam(){
        return new String[]{
                "id",
                "project_name",
                "description",
                "cost"
        };
    }

    public String[] getAll(){
        return new String[]{
            "" + id,
            project_name,
            description,
            String.valueOf(cost)
        };
    }

    @Override
    public String[] getPrm() {
        return new String[]{
                "id",
                "project_name",
                "description",
                "cost"
        };
    }

    @Override
    public String getTableName() {
        return "projects";
    }
}
