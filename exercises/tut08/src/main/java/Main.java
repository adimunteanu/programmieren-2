import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static List<Player> bestPlayers = Player.bestplayers;

    public static void main(String[] args) {
        System.out.println(uniqueTeams(bestPlayers));
        System.out.println(topThreeAssists(bestPlayers));
        System.out.println(mostCommonFirstName(bestPlayers));
        System.out.println(teamsWithBestScoringPlayers(bestPlayers));
        System.out.println(bestScoringTeam(bestPlayers));
    }


    // 8.1.1

    public static long uniqueTeams(List<Player> players) {
        return players.stream()
                .map(player -> player.team)
                .distinct().count();
    }

    // 8.1.2
    public static List<Player> topThreeAssists(List<Player> players) {
        return players.stream()
                .sorted((p1, p2) -> p1.assists > p2.assists ? -1 : 1)
                .limit(3).collect(Collectors.toList());
    }

    //
//    // 8.1.3
    public static List<String> mostCommonFirstName(List<Player> players) {
        return players.stream()
                .map(player -> player.name.split(" ")[0])
                .filter(name -> players.stream()
                        .map(player -> player.name.split(" ")[0])
                        .filter(n -> Objects.equals(n, name))
                        .count() > 1
                )
                .distinct()
                .collect(Collectors.toList());
    }

    //
//    // 8.1.4
    public static List<Map.Entry<String, Long>> teamsWithBestScoringPlayers(List<Player> players) {
        return players.stream()
                .collect(Collectors.groupingBy(player -> player.team, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() >= 2)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    //
//  // 8.1.5
    public static Map.Entry<String, Integer> bestScoringTeam(List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(p -> p.team, p -> p.goals, (x, y) -> x + y))
                .entrySet()
                .stream()
                .reduce((acc, next) -> acc.getValue() > next.getValue() ? acc : next).orElse(null);
    }
}
