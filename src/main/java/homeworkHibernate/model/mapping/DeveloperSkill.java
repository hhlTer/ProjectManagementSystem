package homeworkHibernate.model.mapping;

import homeworkHibernate.model.Developer;
import homeworkHibernate.model.Skill;

import javax.persistence.*;

@Entity
@Table(name = "developers_skill_mtm")
public class DeveloperSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_developer")
    private Developer developer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_skill")
    private Skill skill;

    public void setId(int id) {
        this.id = id;
    }
    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Developer getDeveloper() {
        return developer;
    }
    public int getId() {
        return id;
    }
    public Skill getSkill() {
        return skill;
    }
}
