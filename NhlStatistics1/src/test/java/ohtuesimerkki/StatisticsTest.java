package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class StatisticsTest {
    Reader readerStub = () -> {
        ArrayList<Player> players = new ArrayList<>();

        players.add(new Player("Semenko", "EDM", 4, 12));
        players.add(new Player("Lemieux", "PIT", 45, 54));
        players.add(new Player("Kurri", "EDM", 37, 53));
        players.add(new Player("Yzerman", "DET", 42, 56));
        players.add(new Player("Gretzky", "EDM", 35, 89));

        return players;
    };

    Statistics stats = new Statistics(readerStub);

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }


    @Test
    public void searchWorksWithLegitName() {
        assertNotNull(stats.search("Kurri"));
    }

    @Test
    public void searchDoesReturnNullWhenSearchingNonexistingPlayers() {
        assertNull(stats.search("Selänne"));
    }

    @Test
    public void teamReturnsCorrectValues() {
        ArrayList<Player> expected = new ArrayList<>();
        expected.add(new Player("Semenko", "EDM", 4, 12));
        expected.add(new Player("Kurri", "EDM", 37, 53));
        expected.add(new Player("Gretzky", "EDM", 35, 89));

        assertEquals(expected, stats.team("EDM"));
    }

    @Test
    public void topScorersReturnsCorrectPlayers() {
        ArrayList<Player> expected = new ArrayList<>();
        expected.add(new Player("Semenko", "EDM", 4, 12));
        expected.add(new Player("Lemieux", "PIT", 45, 54));
        expected.add(new Player("Kurri", "EDM", 37, 53));
        expected.add(new Player("Yzerman", "DET", 42, 56));
        expected.add(new Player("Gretzky", "EDM", 35, 89));

        Collections.sort(expected);
        for (int i = 0; i < 2; i++) expected.remove(expected.size() - 1);

        assertEquals(expected, stats.topScorers(3));
    }
}
