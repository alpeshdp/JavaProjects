package com.tw.hibernate.basics.one2many.bidir;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Hashtable;
import java.util.Map;

import org.junit.Test;

import com.tw.hibernate.basics.other.Color;
import com.tw.hibernate.util.HibernateUtil;

public class TeamTest {

    @Test
    public void shouldPersistHashValues() {

        org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Team puneWarriors = new Team();

        Player p1 = new Player();
        p1.setJerseyNumber(11);
        p1.setName("Player One");
        p1.setTeam(puneWarriors);

        Player p2 = new Player();
        p2.setJerseyNumber(22);
        p2.setName("Player Twooooo");
        p2.setTeam(puneWarriors);

        Player p3 = new Player();
        p3.setJerseyNumber(33);
        p3.setName("Player Threee");
        p3.setTeam(puneWarriors);

        Map<Integer, Player> players = new Hashtable<Integer, Player>();
        players.put(11, p1);
        players.put(22, p2);
        players.put(33, p3);

        puneWarriors.setColor(Color.Blue);
        puneWarriors.setName("Pune warriors");
        puneWarriors.setPlayers(players);


        session.save(puneWarriors);
        session.getTransaction().commit();

        assertThat(puneWarriors.getId(), notNullValue());
    }

    @Test
    public void shouldLoadTeam() {
        org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Team teamIndia = (Team) session.load(Team.class, 1);

        assertThat(teamIndia.getName(), is("Pune warriors"));
        assertThat(teamIndia.getPlayers().size(), is(3));

        session.getTransaction().commit();
    }
}
