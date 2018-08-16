package homeworkHibernate.model.results;

import java.math.BigDecimal;

public class ProjectCost {
    private BigDecimal cost;
    private String projectName;

    public ProjectCost(String projectNameInit, BigDecimal costInit) {
        this.cost = costInit;
        this.projectName = projectNameInit;
    }

    //test comit
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getCost() {
        return cost;
    }
    public String getProjectName() {
        return projectName;
    }
}
