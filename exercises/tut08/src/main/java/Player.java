import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    String name;
    String team;
    int goals;
    int assists;

    public Player(String name, String team, int goals, int assists) {
        this.name = name;
        this.team = team;
        this.goals = goals;
        this.assists = assists;
    }

    @Override
    public String toString() {
        return this.name + " - Goals: " + this.goals + " - Assists: " + this.assists + " - Team: " + this.team;
    }

    static List<Player> bestplayers = new ArrayList<>(Arrays.asList(
            new Player("Ivi López", "Rakow Czestochowa", 20, 7),
            new Player("Mikael Ishak", "Lech Poznan", 18, 6),
            new Player("Karol Angielski", "Radomiak Radom", 18, 1),
            new Player("João Amarai", "Lech Poznan", 14, 8),
            new Player("Lukasz Zwolinski", "Lechia Gdansk", 14, 0),
            new Player("Lukasz Sekulski", "Wisla Plock", 13, 3),
            new Player("Patryk Szysz", "Zaglebie Lubin", 11, 5),
            new Player("Luka Zahovic", "Pogon Szczecin", 11, 5),
            new Player("Muris Mesanovic", "Bruk-Bet Termalica Nieciecza", 11, 2),
            new Player("Erik Expósito", "Slask Wroclaw", 11, 2),
            new Player("Bartosz Spiaczka", "Gornik Leczna", 11, 0),
            new Player("Flávio Paixão", "Lechia Gdansk", 10, 7),
            new Player("Jakub Kaminski", "Lech Poznan", 9, 8),
            new Player("Vladislavs Gutkovskis", "Rakow Czestochowa", 9, 6),
            new Player("Kamil Grosicki", "Pogon Szczecin", 9, 6),
            new Player("Lukas Podolski", "Górnik Zabrze", 9, 4),
            new Player("Krzysztof Kubica", "Górnik Zabrze", 9, 3),
            new Player("Piotr Wlazlo", "Bruk-Bet Termalica Nieciecza", 9, 2),
            new Player("Adam Zrelak", "Warta Poznan", 9, 2),
            new Player("Jesús Imaz", "Jagiellonia Bialystok", 9, 2),
            new Player("Tomas Pekhart", "Legia Warszawa", 9, 1),
            new Player("Bartosz Nowak", "Górnik Zabrze", 8, 4),
            new Player("Jesús Jiménez", "Górnik Zabrze", 8, 4),
            new Player("Grzegorz Tomasiewicz", "Stal Mielec", 8, 2),
            new Player("Pelle van Amersfoort", "Cracovia", 8, 1)
    ));
}
