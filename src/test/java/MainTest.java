import org.junit.jupiter.api.Test;
import ru.itis.Main;
import ru.itis.models.Arena;
import ru.itis.models.Player;
import ru.itis.models.Team;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class MainTest {

    @Test
    void Test_solve1() {
        List<Arena> arenas = new ArrayList<>();

        Arena arena = new Arena();
        arena.setCapacity(10000);
        arenas.add(arena);

        List<Arena> result = Main.solve1(arenas);
        assertEquals(1, result.size());

    }

    @Test
    void Test_solve2() {
        List<Player> players = new ArrayList<>();

        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        player1.setPosition("защитник");
        player2.setPosition("форвард");
        player3.setPosition("центровой");
        players.add(player1);
        players.add(player2);
        players.add(player3);

        List<Player> result = Main.solve2(players);
        assertEquals(2, result.size());
    }

    @Test
    void Test_solve3() {
        List<Player> players = new ArrayList<>();

        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        player1.setPosition("защитник");
        player2.setPosition("форвард");
        player3.setPosition("центровой");
        player1.setSalary(241000);
        player2.setSalary(270000);
        player3.setSalary(241000);
        players.add(player1);
        players.add(player2);
        players.add(player3);

        List<Player> result = Main.solve3(players);
        assertEquals(1, result.size());
    }

    @Test
    void Test_solve4() {
        List<Player> players = new ArrayList<>();

        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        player1.setHeight(216);
        player1.setWeight(121);
        player2.setHeight(216);
        player2.setWeight(119);
        player3.setHeight(214);
        player3.setWeight(121);
        players.add(player1);
        players.add(player2);
        players.add(player3);

        List<Player> result = Main.solve4(players);
        assertEquals(3, result.size());
    }

    @Test
    void Test_solve5() {
        List<Team> teams = new ArrayList<>();

        Team team1 = new Team();
        Team team2 = new Team();
        Team team3 = new Team();
        team1.setCity("Барселона");
        team1.setCoachName("Димитрис Итудис");
        team2.setCity("Москва");
        team2.setCoachName("Максим Кнайнов");
        team3.setCity("Казань");
        team3.setCoachName("Велимир Перасович");
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);

        List<Team> result = Main.solve5(teams);
        assertEquals(1, result.size());
    }

    @Test
    void Test_solve6() {
        List<Player> players = new ArrayList<>();

        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();

        player1.setSalary(120000);
        player1.setHeight(190);
        player1.setName("Максим");
        player2.setSalary(121000);
        player2.setHeight(191);
        player2.setName("Тимур");
        player3.setSalary(1000000);
        player3.setHeight(300);
        player3.setName("Анвар");
        players.add(player1);
        players.add(player2);
        players.add(player3);

        List<Player> result = Main.solve6(players);
        assertEquals(2, result.size());
        assertEquals("Тимур", result.get(0).getName());
        assertEquals("Максим", result.get(1).getName());
    }
    @Test
    void Test_solve7() {
        List<Team> teams = new ArrayList<>();

        Team team1 = new Team();
        team1.setCity("Москва");
        team1.setName("ЦСКА");
        team1.setCoachName("Димитрис Итудис");
        teams.add(team1);

        Team team2 = new Team();
        team2.setCity("Мадрид");
        team2.setName("Реал Мадрид");
        team2.setCoachName("Пабло Ласо");
        teams.add(team2);

        Team team3 = new Team();
        team3.setCity("Барселона");
        team3.setName("Барселона");
        team3.setCoachName("Шарунас Ясикявичюс");
        teams.add(team3);

        List<String> result = Main.solve7(teams);
        assertEquals(3, result.size());
        assertEquals("город: Барселона; команда: Барселона; тренер: Шарунас Ясикявичюс", result.get(0));
        assertEquals("город: Мадрид; команда: Реал Мадрид; тренер: Пабло Ласо", result.get(1));
        assertEquals("город: Москва; команда: ЦСКА; тренер: Димитрис Итудис", result.get(2));
    }

    @Test
    void Test_solve8() {
        List<Arena> arenas = new ArrayList<>();

        Arena arena1 = new Arena();
        arena1.setId(10);
        arena1.setName("Палау");
        arena1.setCapacity(8250);
        arenas.add(arena1);

        Arena arena2 = new Arena();
        arena2.setId(30);
        arena2.setName("Визинк-Центр - Паласио де Депортес");
        arena2.setCapacity(15500);
        arenas.add(arena2);

        Arena arena3 = new Arena();
        arena3.setId(60);
        arena3.setName("Барнабеу");
        arena3.setCapacity(100000);
        arenas.add(arena3);

        List<String> result = Main.solve8(arenas);

        assertEquals(2, result.size());
        assertEquals("Название: Палау; Вместительность: 8250", result.get(0));
        assertEquals("Название: Визинк-Центр - Паласио де Депортес; Вместительность: 15500", result.get(1));
    }

    @Test
    void Test_solve9() {
        List<Arena> arenas = new ArrayList<>();

        Arena arena1 = new Arena();
        arena1.setName("УСК ЦСКА им. А.Я. Гомельского");
        arenas.add(arena1);

        List<Team> teams = new ArrayList<>();

        Team team1 = new Team();
        team1.setName("ЦСКА");
        teams.add(team1);

        List<String> result = Main.solve9(arenas, teams);
        assertEquals(2, result.size());
        assertEquals("ЦСКА", result.get(0));
        assertEquals("УСК ЦСКА им. А.Я. Гомельского", result.get(1));
    }

    @Test
    void Test_solve10() {
        List<Arena> arenas = new ArrayList<>();

        Arena arena1 = new Arena();
        arena1.setName("Визинк-Центр - Паласио де Депортес");
        arenas.add(arena1);

        Arena arena2 = new Arena();
        arena2.setName("Палау Блауграна");
        arenas.add(arena2);

        List<Team> teams = new ArrayList<>();

        Team team1 = new Team();
        team1.setName("Барселона");
        teams.add(team1);

        List<Main.StringInformation> result = Main.solve10(arenas, teams);
        assertEquals(3, result.size());
        assertEquals("arena", result.get(0).getObjectType());
        assertEquals("Визинк-Центр - Паласио де Депортес", result.get(0).getName());
        assertEquals("arena", result.get(1).getObjectType());
        assertEquals("Палау Блауграна", result.get(1).getName());
        assertEquals("team", result.get(2).getObjectType());
        assertEquals("Барселона", result.get(2).getName());
    }

    @Test
    void Test_solve11() {
        List<Player> players = new ArrayList<>();

        Player player1 = new Player();
        player1.setSalary(475000);
        players.add(player1);

        Player player2 = new Player();
        player2.setSalary(75000);
        players.add(player2);

        Player player3 = new Player();
        player3.setSalary(100000);
        players.add(player3);

        List<Player> result = Main.solve11(players);
        assertEquals(3, result.size());
        assertEquals(475000, result.get(0).getSalary());
        assertEquals(75000, result.get(1).getSalary());
        assertEquals(100000, result.get(2).getSalary());
    }
}
