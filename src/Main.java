import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Team playerTeam;

    public static void main(String[] args) {
        teamSelect();
        startGame(playerTeam);
    }

    public static void teamSelect() {
        System.out.println("Angels - 1");
        System.out.println("Cubs - 2");
        System.out.print("Choose your team: ");
        Scanner reader = new Scanner(System.in);
        int team = reader.nextInt();
        if (team == 1) {
            System.out.println("You chose the Angels!");

        } else
            System.out.println("You chose the Cubs!");
        System.out.println("Home - 1");
        System.out.println("Away - 2");
        System.out.print("Do you want to play home or away? ");
        int homeOrAway = reader.nextInt();
        if (homeOrAway == 1)
            System.out.println("You will play at home and you will pitch first");
        else
            System.out.println("You will play away and bat first");
    }

    public static void startGame(Team playerTeam) {

    }
}
