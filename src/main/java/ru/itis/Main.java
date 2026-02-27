package ru.itis;

import ru.itis.models.Player;
import ru.itis.models.Team;
import ru.itis.models.Arena;
import ru.itis.models.Game;
import ru.itis.service.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Arena> arenas = new ArrayList<>();
        List<Team> teams = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        List<Game> games = new ArrayList<>();
        try {
            arenas = DataFillerUtil.getArenas(new File("src/main/resources/arena.csv"));
            teams = DataFillerUtil.getTeams(new File("src/main/resources/team.csv"));
            players = DataFillerUtil.getPlayers(new File("src/main/resources/player.csv"));
            games = DataFillerUtil.getGames(new File("src/main/resources/game.csv"));
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка чтения данных. Правильно ли размещён файл?");
        }
        System.out.println(arenas);
        System.out.println(teams);
        System.out.println(players);
        System.out.println(games);

        System.out.println("=-=-=-=-=-=-=-=-=-");
        System.out.println(solve1(arenas));
        System.out.println(solve2(players));
        System.out.println(solve3(players));
        System.out.println(solve4(players));
        System.out.println(solve5(teams));
        System.out.println(solve6(players));
        System.out.println(solve7(teams));
        System.out.println(solve8(arenas));
        System.out.println(solve9(arenas, teams));
        System.out.println(solve10(arenas, teams));
        System.out.println(solve11(players));
    }

    public static class StringInformation {
        private String name;
        private String objectType;

        public StringInformation(String name, String objectType) {
            this.name = name;
            this.objectType = objectType;
        }

        public String getName() { return name; }
        public String getObjectType() { return objectType; }
        public void setName(String name) { this.name = name; }
        public void setObjectType(String objectType) { this.objectType = objectType; }


        @Override
        public String toString() {
            return name + " ," + objectType;
        }
    }
    public static List<Arena> solve1(List<Arena> arenas) {
        return arenas.stream()
                .filter(arena -> arena.getCapacity() > 9000)
                .toList();
    }

    public static List<Player> solve2(List<Player> players) {
        return players.stream()
                .filter(player -> player.getPosition().equals("защитник") || player.getPosition().equals("форвард"))
                .toList();
    }

    public static List<Player> solve3(List<Player> players) {
        return players.stream()
                .filter(player -> (player.getPosition().equals("защитник") || player.getPosition().equals("форвард")) && (player.getSalary() >= 240000 && player.getSalary() <= 260000))
                .toList();
    }

    public static List<Player> solve4(List<Player> players) {
        return players.stream()
                .filter(player -> player.getHeight() > 215 || player.getWeight() > 120)
                .toList();
    }

    public static List<Team> solve5(List<Team> teams) {
        return teams.stream()
                .filter(team -> (team.getCity().equals("Барселона") || team.getCity().equals("Москва")) && team.getCoachName().equals("Димитрис Итудис"))
                .toList();
    }

    public static List<Player> solve6(List<Player> players) {
        return players.stream()
                .filter(player -> (player.getHeight() >= 188 && player.getHeight() <= 200) && (player.getSalary() >= 100000 && player.getSalary() <= 150000))
                .sorted((p1, p2) -> p2.getName().compareTo(p1.getName()))
                .toList();
    }

    public static List<String> solve7(List<Team> teams) {
        return teams.stream()
                .map(team -> String.format("город: %s; команда: %s; тренер: %s",
                        team.getCity(), team.getName(), team.getCoachName()))
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<String> solve8(List<Arena> arenas) {
        List<Integer> id = List.of(10, 30, 50);
        return arenas.stream()
                .filter(arena -> id.contains(arena.getId()))
                .map(arena -> String.format("Название: %s; Вместительность: %s",
                        arena.getName(), arena.getCapacity()))
                .toList();
    }
    public static List<String> solve9(List<Arena> arenas, List<Team> teams) {
        List<String> names = new ArrayList<>();
        for(Arena arena : arenas) {
            names.add(arena.getName());
        }
        for(Team team : teams) {
            names.add(team.getName());
        }
        names.sort((n1, n2) -> n2.compareTo(n1));
        return names;
    }

    public static List<StringInformation> solve10(List<Arena> arenas, List<Team> teams) {
        List<StringInformation> res = new ArrayList<>();
        for(Arena arena : arenas) {
            res.add(new StringInformation(arena.getName(), "arena"));
        }
        for(Team team : teams) {
            res.add(new StringInformation(team.getName(), "team"));
        }
        return res;
    }

    public static List<Player> solve11(List<Player> players) {
        return players.stream().sorted((p1, p2) -> {
                    if(p1.getSalary() == 475000 && p2.getSalary() != 475000) return -1;
                    if(p1.getSalary() != 475000 && p2.getSalary() == 475000) return 1;
                    return Integer.compare(p1.getSalary(), p2.getSalary());
                })
                .limit(5)
                .toList();
    }
}