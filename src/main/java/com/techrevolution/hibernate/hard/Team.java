package com.techrevolution.hibernate.hard;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "team")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(20)")
    private String name;

    @Column(name = "description", columnDefinition = "varchar(50)")
    private String description;

    @OneToMany(mappedBy = "team")
    private List<PlayerTeamRegistration> list;

    private String status;

    public Team(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
