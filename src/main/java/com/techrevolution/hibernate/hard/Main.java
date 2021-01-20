package com.techrevolution.hibernate.hard;

import com.techrevolution.hibernate.configuration.SpringConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        SessionFactory sessionFactory = applicationContext.getBean("sessionFactory", SessionFactory.class);

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

//        Player player = new Player("Hemang", 47);
//
//        Team team = new Team("ShivDhara", "coding");
//
//        PlayerTeamRegistration registration = new PlayerTeamRegistration();
//        registration.setPlayer(player);
//        registration.setStatus("REGISTERED");
//        registration.setTeam(team);
//        try {
//            session.save(registration);
//            transaction.commit();
//            System.out.println("Successfully saved data....");
//        } catch (Exception e) {
//            transaction.rollback();
//        }finally {
//            session.close();
//            sessionFactory.close();
//        }

        try {
            Query query = session.createQuery("from PlayerTeamRegistration ptr where ptr.status='REGISTERED'");
            List<PlayerTeamRegistration> list = query.getResultList();

            Set<Integer> uniquePlayerId = list.stream()
                    .map(playerTeamRegistration -> playerTeamRegistration.getPlayer().getId())
                    .collect(Collectors.toSet());

            List<PlayerDetails> playerDetailsList = new ArrayList<>();

            //second login

            List<Integer> playerIds = new ArrayList<>();

            List<PlayerDetails> logic2 = list.stream()
                    .filter(ptr -> {
                        if (playerIds.contains(ptr.getPlayer().getId())) return false;
                        playerIds.add(ptr.getPlayer().getId());
                        return true;
                    })
                    .map(
                            playerTeamRegistration -> new PlayerDetails(
                                    playerTeamRegistration.getPlayer(), getTeamList(playerTeamRegistration.getPlayer().getId(), list
                            ))).collect(Collectors.toList());

            System.out.println("Second Logic is:--");
            logic2.forEach((playerDetails -> {
                System.out.println("Player name is:--" + playerDetails.getPlayer());
                System.out.println("Player associate with:---" + playerDetails.getTeamList());
            }));



            for (Integer integer : uniquePlayerId) {
                Player player = list.stream().filter(playerTeamRegistration -> playerTeamRegistration.getPlayer().getId() == integer).findFirst().get().getPlayer();
                PlayerDetails playerDetails = new PlayerDetails();
                playerDetails.setPlayer(player);
                playerDetails.setTeamList(getTeamList(integer, list));
                playerDetailsList.add(playerDetails);
            }

            playerDetailsList.forEach(playerDetails -> {
                System.out.println("Player is:--" + playerDetails.getPlayer());
                System.out.println("Player is under teams are:--" + playerDetails.getTeamList());
            });

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    private static List<Team> getTeamList(int player, List<PlayerTeamRegistration> list) {
        return list.stream().filter(playerTeamRegistration -> playerTeamRegistration.getPlayer().getId() == player)
                .map(PlayerTeamRegistration::getTeam).collect(Collectors.toList());
    }

}
