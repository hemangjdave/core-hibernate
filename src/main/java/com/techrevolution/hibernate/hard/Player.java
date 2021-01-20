package com.techrevolution.hibernate.hard;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(20)")
    private String name;

    @Column(name = "winning_count", columnDefinition = "integer")
    private int winning_count;

    @OneToMany(mappedBy = "player")
    private List<PlayerTeamRegistration> list;

    public Player(String name, int winning_count) {
        this.name = name;
        this.winning_count = winning_count;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", winning_count=" + winning_count +
                '}';
    }
}
