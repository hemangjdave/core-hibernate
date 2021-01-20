package com.techrevolution.hibernate.hard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class PlayerDetails {
    private Player player;
    private List<Team> teamList;

    public PlayerDetails(Player player, List<Team> teamList) {
        this.player = player;
        this.teamList = teamList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDetails that = (PlayerDetails) o;
        return Objects.equals(player, that.player) && Objects.equals(teamList, that.teamList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, teamList);
    }
}
