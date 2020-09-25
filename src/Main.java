import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Team playerTeam;
    private static Team cpuTeam;
    private static boolean home;
    private static int strikes = 0;
    private static int balls = 0;
    private static int outs = 0;
    private static int batter = 1;
    private static int cpuScore = 0;

    private static boolean[] baseRunners = new boolean[3];


    public static void main(String[] args) throws InterruptedException, IOException {
//        teamSelect();
        System.out.println(selectStartingPitcher("cubs", "Rotation", "Bullpen"));
//        startGame(playerTeam, cpuTeam, home);
    }

    private static ArrayList<String> selectStartingPitcher(String team, String begin, String end) throws IOException {
        String path = "teams\\" + team + ".csv";    //String "team" should be the name of each file
        ArrayList<String> startingPitchers = new ArrayList<>();
        String temp;
        int i = 0; // for iterating through both do-while loops, which iterates through entire file, line by line
        int counter = 0; // for keeping track of iteration within inner do-while loop
        int start = i; // for keeping track of which line in the file starts the inner do-while loop
        try { //FileNotFoundException
            do { // Outside do-while loop ensure the entire file is scanned
                temp = Files.readAllLines(Paths.get(path)).get(i); // each line is set to String temp and then checked for conditions
                if (temp.startsWith(begin)) { // temp is checked if it starts with String begin
                    start = i; // if so, then we keep track of which line matches by setting start to equal i
                    do {
                        i++; // iterate i first because we want to capture the next line
                        temp = Files.readAllLines(Paths.get(path)).get(i); // scan each subsequent line
                        counter++; // add to counter to keep track of how many lines are within range
                    } while (!temp.startsWith(end)); // keep scanning until the String end is reached
                } else
                    i++; // go to the next line
            } while (!temp.startsWith("exit")); // reads every line in the file until it encounters the String "exit"
            for (int j = start; j < counter; j++) { // ensures only matched range is assigned to ArrayList
                startingPitchers.add(j, Files.readAllLines(Paths.get(path)).get(j));
                //TODO cleanup array (use , to remove all other chars?)
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return startingPitchers;
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
            cpuTeam = new Team(2);
        } else {
            System.out.println("You chose the Cubs!");
            playerTeam = new Team(2);
            cpuTeam = new Team(1);
        }
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

    public static void startGame(Team playerTeam, Team cpuTeam, boolean home) throws InterruptedException {
        int playerScore = 0;
        int startingPitcher;
        int selectedPitch;

        Player[] lineup = playerTeam.getLineup();
        Pitcher[] bullpen = playerTeam.getPen();
        Pitcher[] starters = playerTeam.getStarters();

        if (home) {
            startingPitcher = getStartingPitcher(starters);

            do {
                displayPitcherName(startingPitcher);
                displayBatterName(batter);

                do {
                    displayScore(playerScore, cpuScore);
                    displayOuts(outs);
                    displayBaserunners(baseRunners);
                    displayCount(balls, strikes);
                    selectedPitch = getPitch();
                    throwSelectedPitch(selectedPitch, startingPitcher, starters, strikes, balls);
                } while (strikes < 3 && balls < 4);

                if (strikes == 3)
                    strikeout();
                else
                    walk(baseRunners, cpuScore);

                strikes = 0;
                balls = 0;
            } while (outs < 3);
            System.out.println("Inning over");

        } else {
            do {
                System.out.println("At bat: " + playerTeam.getPlayerName(batter));
                batter++;
                outs++;
            } while (outs < 3);
        }

    }

    public static void displayPitcherName(int pitcher) {
        System.out.println("Now pitching: " + playerTeam.getStarterNames(pitcher));
        System.out.println();
    }

    public static void displayBatterName(int batter) {
        System.out.println("Now Batting: " + cpuTeam.getPlayerName(batter));
        System.out.println();
    }

    public static void displayScore(int playerScore, int cpuScore) {
        System.out.println("---Score---");
        System.out.println("Home: " + playerScore);
        System.out.println("Away: " + cpuScore);
        System.out.println("-----------");
        System.out.println();
    }

    public static void displayOuts(int outs) {
        System.out.println("Outs: " + outs);
        System.out.println();
    }

    public static void displayBaserunners(boolean[] baseRunners) {
        if (!baseRunners[0] && !baseRunners[1] && !baseRunners[2])
            System.out.println("Bases are empty");
        if (baseRunners[0] && !baseRunners[1] && !baseRunners[2])
            System.out.println("Runner on first");
        if (!baseRunners[0] && baseRunners[1] && !baseRunners[2])
            System.out.println("Runner on second");
        if (!baseRunners[0] && !baseRunners[1] && baseRunners[2])
            System.out.println("Runner on third");
        if (baseRunners[0] && baseRunners[1] && !baseRunners[2])
            System.out.println("Runners on first & second");
        if (baseRunners[0] && !baseRunners[1] && baseRunners[2])
            System.out.println("Runners on first & third");
        if (baseRunners[0] && baseRunners[1] && baseRunners[2])
            System.out.println("Bases loaded!");
    }

    public static void displayCount(int balls, int strikes) {
        System.out.println("The count is: " + balls + " & " + strikes);
        System.out.println();
    }

    public static int getPitch() {
        System.out.println("Select a pitch:");
        System.out.println("Fastball (1)  |  Curveball (2)  |  Slider (3)  |  Changeup (4)");
        System.out.println();
        Scanner pitchSelect = new Scanner(System.in);
        return pitchSelect.nextInt();
    }

    public static void addStrike() {
        strikes++;
    }

    public static void addBall() {
        balls++;
    }

    public static void throwSelectedPitch(int selectedPitch, int startingPitcher, Pitcher[] starters, int strikes, int balls) {
        if (selectedPitch == 1) {
            System.out.println(playerTeam.getStarterNames(startingPitcher) + " throws a fastball");
            Pitch pitch = new Pitch(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch), selectedPitch);
            if (pitch.throwFastball(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch))) {
                System.out.println("It's a strike!");
                addStrike();
            } else {
                System.out.println("It's a ball!");
                addBall();
            }
        } else if (selectedPitch == 2) {
            System.out.println(playerTeam.getStarterNames(startingPitcher) + " throws a curveball");
            Pitch pitch = new Pitch(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch), selectedPitch);
            if (pitch.throwCurveball(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch))) {
                System.out.println("It's a strike!");
                addStrike();
            } else {
                System.out.println("It's a ball!");
                addBall();
            }
        } else if (selectedPitch == 3) {
            System.out.println(playerTeam.getStarterNames(startingPitcher) + " throws a slider");
            Pitch pitch = new Pitch(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch), selectedPitch);
            if (pitch.throwSlider(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch))) {
                System.out.println("It's a strike!");
                addStrike();
            } else {
                System.out.println("It's a ball!");
                addBall();
            }
        } else if (selectedPitch == 4) {
            System.out.println(playerTeam.getStarterNames(startingPitcher) + " throws a changeup");
            Pitch pitch = new Pitch(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch), selectedPitch);
            if (pitch.throwChangeup(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch))) {
                System.out.println("It's a strike!");
                addStrike();
            } else {
                System.out.println("It's a ball!");
                addBall();
            }
        } else {
            System.out.println("You must select a valid pitch");
        }
    }

    public static void strikeout() {
        outs++;
        batter++;
        if (batter == 10)
            batter = 1;
        System.out.println();
        System.out.println("Batter is out!");
        System.out.println();
    }

    public static void walk(boolean[] baseRunners, int cpuScore) {
        batter++;
        if (batter == 10)
            batter = 1;
        System.out.println("Batter takes base on balls");
        if (!baseRunners[0])
            baseRunners[0] = true;
        else if (baseRunners[0] && !baseRunners[1])
            baseRunners[1] = true;
        else if (baseRunners[0] && baseRunners[1] && !baseRunners[2])
            baseRunners[2] = true;
        else if (baseRunners[0] && baseRunners[1] && baseRunners[2])
            cpuScore++;
    }

    public static int getStartingPitcher(Pitcher[] starters) {
        Scanner startingPitcherReader = new Scanner(System.in);
        int selectedPitcher;
        int startingPitcher = -1;
        System.out.println("Select your starting pitcher");
        System.out.println();
        for (int i = 0; i < starters.length; i++) {
            System.out.println(playerTeam.getStarterNames(i) + " - " + (i + 1));
        }
        System.out.print("[Choose 1 - 5] ");
        selectedPitcher = startingPitcherReader.nextInt();
        if (selectedPitcher == 1)
            startingPitcher = 0;
        if (selectedPitcher == 2)
            startingPitcher = 1;
        if (selectedPitcher == 3)
            startingPitcher = 2;
        if (selectedPitcher == 4)
            startingPitcher = 3;
        if (selectedPitcher == 5)
            startingPitcher = 4;

        return startingPitcher;
    }

    public static void fastballAnimation() throws InterruptedException {
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
