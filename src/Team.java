public class Team {

    // should build an array of player objects with team name

//    Pitchers
//    Matt Andriese     35	    R/R
//    Jacob Barnes      40	    R/R
//    Jaime Barria      51	    R/R
//    Cam Bedrosian     32	    R/R
//    Dylan Bundy       37	    S/R
//    Ty Buttrey        31      L/R
//    Griffin Canning   47      R/R
//    Andrew Heaney     28	    L/L
//    Mike Mayers       60	    R/R
//    Hoby Milner       61	    L/L
//    Shohei Ohtani     17	    L/R
//    Felix Peña        64      R/R
//    Noé Ramirez       24	    R/R
//    Hansel Robles     57	    R/R
//    Julio Teheran     49	    R/R

//    Catchers
//    Anthony Bemboom   12  	L/R
//    Max Stassi        33      R/R

//    Infielders
//    David Fletcher    22	    R/R
//    Albert Pujols     5	    R/R
//    Anthony Rendon    6	    R/R
//    Luis Rengifo      4	    S/R
//    Andrelton Simmons 2   	R/R
//    Matt Thaiss       23	    L/R
//    Jared Walsh       25	    L/L

//    Outfielders
//    Jo Adell          59      R/R
//    Mike Trout        27      R/R
//    Justin Upton      10	    R/R
//    Taylor Ward       3       R/R

    //pitchers
    Pitcher mAndriese = new Pitcher(true,87.1,89.4,78.1,79.2,65.1,85.8,
            56.4,32.1,18.4,'r','r',"Matt Andriese",35);
    Pitcher jBarnes = new Pitcher(true,87.1,89.4,78.1,79.2,87.2,89.3,
            91.5,98.8,95.6,'r','r',"Jacob Barnes",27);
    Pitcher jBarria = new Pitcher(true,87.1,89.4,78.1,79.2,87.2,89.3,
            91.5,98.8,95.6,'r','r',"Jaime Barria",27);
    Pitcher cBedrosian = new Pitcher(true,87.1,89.4,78.1,79.2,87.2,89.3,
            91.5,98.8,95.6,'r','r',"Cam Bedrosian",27);
    Pitcher dBundy = new Pitcher(true,87.1,89.4,78.1,79.2,87.2,89.3,
            91.5,98.8,95.6,'r','r',"Dylan Bundy",27);
    Pitcher tButtery = new Pitcher(true,87.1,89.4,78.1,79.2,87.2,89.3,
            91.5,98.8,95.6,'r','r',"Ty Buttrey",27);
    Pitcher gCanning = new Pitcher(true,87.1,89.4,78.1,79.2,87.2,89.3,
            91.5,98.8,95.6,'r','r',"Griffin Canning",27);
    Pitcher aHeaney = new Pitcher(true,89.1,91.5,92.8,86.9,78.3,89.3,
            78.5,32.8,11.5,'L','L',"Andrew Heaney",28);
    Pitcher mMayers = new Pitcher(true,87.1,89.4,78.1,79.2,87.2,89.3,
            91.5,98.8,95.6,'r','r',"Mike Mayers",27);
    Pitcher hMilner = new Pitcher(true,87.1,89.4,78.1,79.2,87.2,89.3,
            91.5,98.8,95.6,'r','r',"Hoby Milner",27);
    Pitcher fPena = new Pitcher(true,87.1,89.4,78.1,79.2,87.2,89.3,
            91.5,98.8,95.6,'r','r',"Felix Peña",27);
    Pitcher nRamirez = new Pitcher(true,87.1,89.4,78.1,79.2,87.2,89.3,
            91.5,98.8,95.6,'r','r',"Noé Ramirez",27);
    Pitcher hRobles = new Pitcher(true,87.1,89.4,78.1,79.2,87.2,89.3,
            91.5,98.8,95.6,'r','r',"Hansel Robles",27);
    Pitcher jTeheran = new Pitcher(true,87.1,89.4,78.1,79.2,87.2,89.3,
            91.5,98.8,95.6,'r','r',"Julio Teheran",27);

    //catchers
    Player aBemboom = new Player(false,87.2,89.3,91.5,98.8,95.6,'r','r',"Anthony Bemboom",27);
    Player mStassi = new Player(false,87.2,89.3,91.5,98.8,95.6,'r','r',"Max Stassi",27);

    //infielders
    Player dFletcher = new Player(false,87.2,89.3,91.5,98.8,95.6,'r','r',"David Fletcher",27);
    Player aRendon = new Player(false,87.2,89.3,91.5,98.8,95.6,'r','r',"Anthony Rendon",27);
    Player lRengifo = new Player(false,87.2,89.3,91.5,98.8,95.6,'r','r',"Luis Rengifo",27);
    Player aSimmons = new Player(false,87.2,89.3,91.5,98.8,95.6,'r','r',"Andrelton Simmons",27);
    Player mThaiss = new Player(false,87.2,89.3,91.5,98.8,95.6,'r','r',"Matt Thaiss",27);
    Player jWalsh = new Player(false,87.2,89.3,91.5,98.8,95.6,'r','r',"Jared Walsh",27);

    //outfielders
    Player jAdell = new Player(false,87.2,89.3,91.5,98.8,95.6,'r','r',"Jo Adell",27);
    Player mTrout = new Player(false,87.2,89.3,91.5,98.8,95.6,'r','r',"Mike Trout",27);
    Player jUpton = new Player(false,87.2,89.3,91.5,98.8,95.6,'r','r',"Justin Upton",27);
    Player tWard = new Player(false,87.2,89.3,91.5,98.8,95.6,'r','r',"Taylor Ward",27);

    //designated hitters
    Player sOhtani = new Player(false,87.2,89.3,91.5,98.8,95.6,'r','r',"Shohei Ohtani",27);
    Player aPujols = new Player(false,87.2,89.3,91.5,98.8,95.6,'r','r',"Albert Pujols",27);

    private Player[] lineup;
    private Pitcher[] starters;
    private Pitcher[] pen;

    private Player[] startingLineupAngels = {aHeaney,dFletcher,jWalsh,mTrout,aRendon,sOhtani,jAdell,aSimmons,jUpton,mStassi};
    private Pitcher[] startingPitchersAngels = {aHeaney,dBundy,gCanning,jBarria,jTeheran};
    private Pitcher[] bullpenAngels = {tButtery,nRamirez,mMayers,hRobles,jBarnes,mAndriese,cBedrosian,fPena,hMilner};

    public Team(int teamNumber) {
            lineup = startingLineupAngels;
            starters = startingPitchersAngels;
            pen = bullpenAngels;
    }

    public Player[] getLineup() {
            return lineup;
    }

    public Pitcher[] getStarters() {
            return startingPitchersAngels;
    }

    public Pitcher[] getPen() {
            return bullpenAngels;
    }

    public String getPlayerName(int index) {
        return lineup[index].getName();
    }
}

