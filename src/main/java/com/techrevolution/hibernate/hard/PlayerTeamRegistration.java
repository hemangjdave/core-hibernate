package com.techrevolution.hibernate.hard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "player_team_registration")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class PlayerTeamRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status", columnDefinition = "varchar(20)")
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private Player player;

    @Override
    public String toString() {
        return "PlayerTeamRegistration{" +
                "status='" + status + '\'' +
                ", team=" + team +
                ", player=" + player +
                '}';
    }
}
