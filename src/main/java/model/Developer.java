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
        return String.format("Developer :%s\nAge %d\n", name, age);
    }
}

class Skill{
    private long id;
    private String skill;
    private String grade;
}

