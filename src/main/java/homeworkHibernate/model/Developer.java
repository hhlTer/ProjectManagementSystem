package homeworkHibernate.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Table(name = "developers")
@Entity
public class Developer implements GenerallyTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private boolean sex;

    @Column(name = "salary")
    private BigDecimal salary;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "developer")
//    private Set<Project> projects;

//    public void setProjects(Set<Project> projects) {
//        this.projects = projects;
//    }
    public void setId(long id) {
        this.id = (int)id;
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

//    public Set<Project> getProjects() {
//        return projects;
//    }
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
    public String[] getPrm() {
        return new String[]{
                "id",
                "first_name",
                "age",
                "sex",
                "salary"
        };
    }

    @Override
    public String getTableName() {
        return "developers";
    }

    public final static class Skill {
        private long id;
        private String skill;
        private String grade;

        public long getId() {
            return id;
        }
        public String getSkill() {
            return skill;
        }
        public String getGrade() {
            return grade;
        }

        @Override
        public String toString() {
            return String.format("id :%d\nTypeSkill: %s\nGrade: %s\n", id, skill, grade);
        }

        public static String[] getParam() {
            return new String[]{
                    "id",
                    "skill",
                    "grade",
            };
        }

        public String[] getAll(){
            return new String[]{
                String.valueOf(id),
                skill,
                grade
            };
        }

    }
}