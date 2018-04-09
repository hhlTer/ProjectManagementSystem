package model;

import java.math.BigDecimal;

public class Developer {
    private long id;
    private String name;
    private int age;
    private boolean sex;
    private BigDecimal salary;

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setSex(boolean sex) {
        this.sex = sex;
    }
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public boolean isSex() {
        return sex;
    }
    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format(
                "Developer :%s\nAge %d\nsex: %s\nsalary: %d", name, age
                , sex? "male" : "female", salary.longValue());
    }

    public String[] getAll(){
        return new String[]{
            String.valueOf(id),
            name,
            String.valueOf(age),
            String.valueOf(sex),
            String.valueOf(salary)
        };
    }

    public static String[] getParam() {
        return new String[]{
                "id",
                "first_name",
                "age",
                "sex",
                "salary"
        };
    }

    public static class Skill {
        private long id;
        private String skill;
        private String grade;

        @Override
        public String toString() {
            return String.format("id :%d\nSkill: %s\nGrade: %s\n", id, skill, grade);
        }

        public String[] getParam() {
            return new String[]{
                    "id",
                    "skill",
                    "grade",
            };
        }
    }
}