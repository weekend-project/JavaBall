import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Team playerTeam;
    private static boolean home;

    public static void main(String[] args) throws InterruptedException {
        teamSelect();
        startGame(playerTeam, home);
    }

    public static void teamSelect() {
        System.out.println("Angels - 1");
        System.out.println("Cubs - 2");
        System.out.print("Choose your team: ");
        Scanner reader = new Scanner(System.in);
        int team = reader.nextInt();
        if (team == 1) {
            System.out.println("You chose the Angels!");
            playerTeam = new Team(1);
        } else
            System.out.println("You chose the Cubs!");
        System.out.println("Home - 1");
        System.out.println("Away - 2");
        System.out.print("Do you want to play home or away? ");
        int homeOrAway = reader.nextInt();
        if (homeOrAway == 1) {
            home = true;
            System.out.println("You will play at home and you will pitch first");
        } else {
            home = false;
            System.out.println("You will play away and bat first");
        }
    }

    public static void startGame(Team playerTeam, boolean home) throws InterruptedException {
        int outs = 0;
        int batter = 1;
        final int PITCHER = 0;
        int strikes = 0;
        int balls = 0;
        Player[] lineup = playerTeam.getLineup();
        Pitcher[] bullpen = playerTeam.getPen();
        Scanner pitchSelect = new Scanner(System.in);

        if (home) {
            do {
                System.out.println("Pitching: " + playerTeam.getPlayerName(PITCHER));
                System.out.println();
                Thread.sleep(1000);
                System.out.println("Select a pitch:");
                System.out.println("Fastball (1)  |  Curveball (2)  |  Slider (3)  |  Changeup (4)");
                int pitch = pitchSelect.nextInt();
                if (pitch == 1) {
                    System.out.println(playerTeam.getPlayerName(PITCHER) + " throws a fastball");
                    Thread.sleep(500);
                    fastball();
                    Thread.sleep(500);
                    System.out.println("It's a strike!");
                } else if (pitch == 2) {

                } else if (pitch == 3) {

                } else if (pitch == 4) {

                } else {
                    System.out.println("You must select a valid pitch");
                }
                outs++;
            } while (outs < 3);
        } else {
            do {
                System.out.println("At bat: " + playerTeam.getPlayerName(batter));
                batter++;
                outs++;
            } while (outs < 3);
        }





    }

    public static void fastball() throws InterruptedException {
        System.out.print("o");
        Thread.sleep(100);
        System.out.print(" o");
        Thread.sleep(100);
        System.out.print("  o");
        Thread.sleep(100);
        System.out.print("   o");
        Thread.sleep(100);
        System.out.print("    o");
        Thread.sleep(100);
        System.out.print("     o");
        Thread.sleep(100);
        System.out.print("      o");
        Thread.sleep(100);
        System.out.print("       o");
        Thread.sleep(100);
        System.out.print("        o");
        System.out.println();


    }
}
