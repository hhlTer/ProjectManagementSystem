package model;

import java.math.BigDecimal;

public class Projects {
    private long id;
    private String project_name;
    private String description;
    private BigDecimal cost;

    @Override
    public String toString() {
        return String.format("Company :%s\ndescription: %s\ncost: %f", project_name, description, cost.doubleValue());
    }

    public String[] getParam(){
        return new String[]{
                "company_name",
                "adress"
        };
    }
}
