import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//FIXME there is no designated hitter row in the Dodgers CSV
//TODO make all print statements look uniform, or start a Swing version

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
    private static String[] positions = {"Rotation", "Catcher", "First Base", "Second Base", "Third Base", "Shortstop",
            "Left Field", "Center Field", "Right Field", "Designated Hitter"};
    public static final String[] TEAMS = new String[30];

    private static int[] playerSelectedPositions = new int[10];
    private static String[] lineup = {"EMPTY", "EMPTY", "EMPTY", "EMPTY", "EMPTY", "EMPTY", "EMPTY", "EMPTY", "EMPTY",};
    private static Scanner reader = new Scanner(System.in);
    private static ArrayList<String> rotation;
    private static ArrayList<String> catcher;
    private static ArrayList<String> firstBase;
    private static ArrayList<String> secondBase;
    private static ArrayList<String> thirdBase;
    private static ArrayList<String> shortstop;
    private static ArrayList<String> leftField;
    private static ArrayList<String> centerField;
    private static ArrayList<String> rightField;
    private static ArrayList<String> dh;
    private static ArrayList<String> bullpen;
    private static List<ArrayList<String>> playerPos = new ArrayList<ArrayList<String>>();


    public static void main(String[] args) throws IOException {
        welcomeSplash();
        buildTEAMSarray();
        teamSelect();
        buildBullpenArray();
        homeTeamSelect();
        positionSelect();
        lineupSelect();
//        startGame(playerTeam, cpuTeam, home);
    }

    public static void buildBullpenArray() throws IOException {
        bullpen = new ArrayList<>(buildPlayerArray(parseTeamCSVtoArrayList(playerTeam.getTeamName(), "Bullpen", "exit")));
    }

    public static void welcomeSplash() {
        System.out.println("Welcome to JavaBall");
        System.out.println();
    }

    public static void buildTEAMSarray() throws IOException {
        File file = new File("C:\\Dev\\JavaBall_2\\Teams\\teams.csv");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int i = 0;
        try {
            String st;
            while ((st = br.readLine()) != null) {
                TEAMS[i] = st;
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

    }

    public static void lineupSelect() {
        ArrayList<String> tempLineup = new ArrayList<>();
        Scanner reader = new Scanner(System.in);
        int spot;
        int player;
        for (int i = 0, j = 1; i < lineup.length; i++, j++) {
            tempLineup.add(playerPos.get(j).get(playerSelectedPositions[i] - 1)); //FIXME can this just use i instead of j?
        }
        System.out.println();
        while (!tempLineup.isEmpty()) {
            System.out.println("-----------------------------");
            System.out.println("Players remaining:");
            for (String s : tempLineup)
                System.out.println(s);
            System.out.println("-----------------------------");
            System.out.println();
            System.out.println("Current lineup:");
            for (int i = 0; i < lineup.length; i++)
                System.out.println((i + 1) + " | " + lineup[i]);
            System.out.println();
            System.out.println("Select which spot you want to fill");
            spot = reader.nextInt();
            System.out.println();
            System.out.println("Which player would you like to add to spot #" + spot + "?");
            for (int i = 0; i < tempLineup.size(); i++)
                System.out.println("[" + (i + 1) + "] - " + tempLineup.get(i));
            player = reader.nextInt();
            lineup[spot - 1] = tempLineup.get(player - 1);
            tempLineup.remove(player - 1);

        }


//        for (int i = 1; i < lineup.length-1; i++) {
//            System.out.println("#" + i + " spot options include");
//        }
    }

    public static void positionSelect() throws IOException {
        for (int start = 0, end = 1; start < positions.length; start++, end++) {
            if (start == 0 && end == 1) {
                playerSelect(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end]));
                rotation = new ArrayList<>(buildPlayerArray(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end])));
            } else if (start == 1 && end == 2) {
                playerSelect(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end]));
                catcher = new ArrayList<>(buildPlayerArray(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end])));
            } else if (start == 2 && end == 3) {
                playerSelect(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end]));
                firstBase = new ArrayList<>(buildPlayerArray(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end])));
            } else if (start == 3 && end == 4) {
                playerSelect(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end]));
                secondBase = new ArrayList<>(buildPlayerArray(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end])));
            } else if (start == 4 && end == 5) {
                playerSelect(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end]));
                thirdBase = new ArrayList<>(buildPlayerArray(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end])));
            } else if (start == 5 && end == 6) {
                playerSelect(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end]));
                shortstop = new ArrayList<>(buildPlayerArray(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end])));
            } else if (start == 6 && end == 7) {
                playerSelect(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end]));
                leftField = new ArrayList<>(buildPlayerArray(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end])));
            } else if (start == 7 && end == 8) {
                playerSelect(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end]));
                centerField = new ArrayList<>(buildPlayerArray(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end])));
            } else if (start == 8 && end == 9) {
                playerSelect(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end]));
                rightField = new ArrayList<>(buildPlayerArray(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], positions[end])));
            } else if (start == 9 && end == 10) {
                playerSelect(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], "Bullpen"));
                dh = new ArrayList<>(buildPlayerArray(parseTeamCSVtoArrayList(playerTeam.getTeamName(), positions[start], "Bullpen")));
            }
//            if (end != 10) {
//                playerSelect(getPlayerArray(playerTeam.getTeamName(), positions[start], positions[end]));
//            } else {
//                playerSelect(getPlayerArray(playerTeam.getTeamName(), positions[start], "Bullpen"));
//            }
            System.out.println();
            System.out.print("Choose your player: ");
            playerSelectedPositions[start] = reader.nextInt();
            System.out.println();
        }
        playerPos.add(rotation);
        playerPos.add(catcher);
        playerPos.add(firstBase);
        playerPos.add(secondBase);
        playerPos.add(thirdBase);
        playerPos.add(shortstop);
        playerPos.add(leftField);
        playerPos.add(centerField);
        playerPos.add(rightField);
        playerPos.add(dh);
        System.out.println("Proceed with the following players at these positions?");
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < positions.length; i++) {
            if (i == 0) {
                System.out.println("Starting Pitcher: " + playerPos.get(i).get(playerSelectedPositions[i] - 1));
            }
            else {
                System.out.println(positions[i] + ": " + playerPos.get(i).get(playerSelectedPositions[i] - 1));
            }
        }
        System.out.println("------------------------------------------------------");
        System.out.println();
        System.out.println("(1) - Proceed");
        System.out.println("(2) - Start over");
        int proceed = reader.nextInt();
        if (proceed == 2)
            positionSelect();
    }

    public static ArrayList<String> buildPlayerArray(ArrayList<String> players) {
        ArrayList<String> pos = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            String str = players.get(i);
            String[] arr = str.split(",");
            if (i != 0)
                pos.add(arr[0]);
        }
        return pos;
    }

//            for (int j = 0; j < players.size(); j++) {
//        String str = players.get(j);
//        String[] arr = str.split(",");
//        if (j == 0)
//            System.out.println("--" + arr[0] + "--");
//        else
//            System.out.println("(" + j + ") - " + arr[0]);
//    }
//}

    private static ArrayList<String> parseTeamCSVtoArrayList(String team, String begin, String end) throws IOException {
        String path = "C:\\Dev\\JavaBall_2\\Teams\\" + team + ".csv";    //String "team" should be the name of each file
        ArrayList<String> playerList = new ArrayList<>();
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
                    break;
                } else
                    i++; // go to the next line
            } while (!temp.startsWith("exit")); // reads every line in the file until it encounters the String "exit"
            for (int j = 0; j < counter; j++, start--)  // ensures only matched range is assigned to ArrayList
                playerList.add(0, Files.readAllLines(Paths.get(path)).get((start + counter) - 1));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return playerList;
    }

    public static void playerSelect(ArrayList<String> players) throws IOException {
        for (int j = 0; j < players.size(); j++) {
            String str = players.get(j);
            String[] arr = str.split(",");
            if (j == 0)
                System.out.println("--" + arr[0] + "--");
            else {
                System.out.println("(" + j + ") - " + arr[0]);
                System.out.println("testing getPlayerStats:"+parseTeamCSVtoArrayList(playerTeam.getTeamName(),arr[0],arr[0] ));
            }
        }
    }

    public static String getPlayerStats(String team, String playerName, int index) throws IOException {
        String path = "C:\\Dev\\JavaBall_2\\Teams\\" + team + ".csv";    //String "team" should be the name of each file
        ArrayList<String> statsArray = new ArrayList<>();
        Scanner reader = new Scanner(new File(path));
        while (reader.hasNextLine()) {
            if (reader.next().equals(playerName)) {
                reader.useDelimiter(",");
                while (reader.hasNext()){
                    statsArray.add(reader.next());
                }
            }
        }
//        BufferedReader br = null;
//        String line = "";
//        String splitChar = ",";
//        String stat = "";
//        try {
//            br = new BufferedReader(new FileReader(path));
//            while ((line = br.readLine()) != null && line.startsWith(playerName)) {
//                String[] stats = line.split(splitChar);
//                stat = stats[index];
//                break;
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
////        ArrayList<String> playerList;
////        String line;
////        int i = 0; // for iterating through both do-while loops, which iterates through entire file, line by line
////        int counter = 0; // for keeping track of iteration within inner do-while loop
////        int start = i; // for keeping track of which line in the file starts the inner do-while loop
////        line = Files.readAllLines(Paths.get(path)).get(i); // each line is set to String temp and then checked for conditions
////        if (line.startsWith(playerName))  // temp is checked if it starts with String begin
////            String[] players = line.split(",");
////        return players;
//        }
        return "stats";
    }

    public static void teamSelect() {
        for (int i = 0; i < TEAMS.length; i++) {
            System.out.println("[" + (i + 1) + "] - " + TEAMS[i]);
        }
        System.out.println();
        System.out.print("Choose your team now: ");
        Scanner reader = new Scanner(System.in);
        int playerTeamSelection = reader.nextInt();
        playerTeam = new Team(playerTeamSelection - 1);
        System.out.println();
        System.out.print("Choose CPU team: ");
        int cpuTeamSelection = reader.nextInt();
        cpuTeam = new Team(cpuTeamSelection - 1, true);
        System.out.println();
        System.out.println("You are playing as the " + playerTeam.getTeamName());
        System.out.println();
        System.out.println("You are playing against the " + cpuTeam.getCpuTeamName());
        System.out.println();
        System.out.println("Proceed?");
        System.out.println("(1) - Yes");
        System.out.println("(2) - No");
        int proceed = reader.nextInt();
        if (proceed == 2)
            teamSelect();
        System.out.println();
    }

    public static void homeTeamSelect() {
        System.out.println("Do you want to play home or away? ");
        System.out.println("(1) - Home");
        System.out.println("(2) - Away");
        int homeOrAway = reader.nextInt();
        if (homeOrAway == 1) {
            home = true;
            System.out.println("You will play at home and you will pitch first");
        } else {
            home = false;
            System.out.println("You will play away and bat first");
        }
        System.out.println();
    }


//    public static void startGame(Team playerTeam, Team cpuTeam, boolean home) throws InterruptedException {
//        int playerScore = 0;
//        int startingPitcher;
//        int selectedPitch;
//
//        Player[] lineup = playerTeam.getLineup();
//        Pitcher[] bullpen = playerTeam.getPen();
//        Pitcher[] starters = playerTeam.getStarters();
//
//        if (home) {
//            startingPitcher = getStartingPitcher(starters);
//
//            do {
//                displayPitcherName(startingPitcher);
//                displayBatterName(batter);
//
//                do {
//                    displayScore(playerScore, cpuScore);
//                    displayOuts(outs);
//                    displayBaserunners(baseRunners);
//                    displayCount(balls, strikes);
//                    selectedPitch = getPitch();
//                    throwSelectedPitch(selectedPitch, startingPitcher, starters, strikes, balls);
//                } while (strikes < 3 && balls < 4);
//
//                if (strikes == 3)
//                    strikeout();
//                else
//                    walk(baseRunners, cpuScore);
//
//                strikes = 0;
//                balls = 0;
//            } while (outs < 3);
//            System.out.println("Inning over");
//
//        } else {
//            do {
//                System.out.println("At bat: " + playerTeam.getPlayerName(batter));
//                batter++;
//                outs++;
//            } while (outs < 3);
//        }
//
//    }

//    public static void displayPitcherName(int pitcher) {
//        System.out.println("Now pitching: " + playerTeam.getStarterNames(pitcher));
//        System.out.println();
//    }

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

//    public static void throwSelectedPitch(int selectedPitch, int startingPitcher, Pitcher[] starters, int strikes, int balls) {
//        if (selectedPitch == 1) {
//            System.out.println(playerTeam.getStarterNames(startingPitcher) + " throws a fastball");
//            Pitch pitch = new Pitch(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch), selectedPitch);
//            if (pitch.throwFastball(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch))) {
//                System.out.println("It's a strike!");
//                addStrike();
//            } else {
//                System.out.println("It's a ball!");
//                addBall();
//            }
//        } else if (selectedPitch == 2) {
//            System.out.println(playerTeam.getStarterNames(startingPitcher) + " throws a curveball");
//            Pitch pitch = new Pitch(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch), selectedPitch);
//            if (pitch.throwCurveball(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch))) {
//                System.out.println("It's a strike!");
//                addStrike();
//            } else {
//                System.out.println("It's a ball!");
//                addBall();
//            }
//        } else if (selectedPitch == 3) {
//            System.out.println(playerTeam.getStarterNames(startingPitcher) + " throws a slider");
//            Pitch pitch = new Pitch(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch), selectedPitch);
//            if (pitch.throwSlider(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch))) {
//                System.out.println("It's a strike!");
//                addStrike();
//            } else {
//                System.out.println("It's a ball!");
//                addBall();
//            }
//        } else if (selectedPitch == 4) {
//            System.out.println(playerTeam.getStarterNames(startingPitcher) + " throws a changeup");
//            Pitch pitch = new Pitch(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch), selectedPitch);
//            if (pitch.throwChangeup(playerTeam.getSpecificPitchRating(starters[startingPitcher], selectedPitch))) {
//                System.out.println("It's a strike!");
//                addStrike();
//            } else {
//                System.out.println("It's a ball!");
//                addBall();
//            }
//        } else {
//            System.out.println("You must select a valid pitch");
//        }
//    }

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

//    public static int getStartingPitcher(Pitcher[] starters) {
//        Scanner startingPitcherReader = new Scanner(System.in);
//        int selectedPitcher;
//        int startingPitcher = -1;
//        System.out.println("Select your starting pitcher");
//        System.out.println();
//        for (int i = 0; i < starters.length; i++) {
//            System.out.println(playerTeam.getStarterNames(i) + " - " + (i + 1));
//        }
//        System.out.print("[Choose 1 - 5] ");
//        selectedPitcher = startingPitcherReader.nextInt();
//        if (selectedPitcher == 1)
//            startingPitcher = 0;
//        if (selectedPitcher == 2)
//            startingPitcher = 1;
//        if (selectedPitcher == 3)
//            startingPitcher = 2;
//        if (selectedPitcher == 4)
//            startingPitcher = 3;
//        if (selectedPitcher == 5)
//            startingPitcher = 4;
//
//        return startingPitcher;
//    }

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
