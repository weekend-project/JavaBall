class Player {

    private boolean isPitcher;
    private double speed;
    private double throwing;
    private double fielding;
    private double hitPower;
    private double hitContact;
    private char handed;
    private char hits;
    private String name;
    private int number;
    private String pos;

    public Player(boolean isPitcher,
                  double speed,
                  double throwing,
                  double fielding,
                  double hitPower,
                  double hitContact,
                  char handed,
                  char hits,
                  String name,
                  int number) {

        this.isPitcher = isPitcher;
        this.speed = speed;
        this.throwing = throwing;
        this.fielding = fielding;
        this.hitPower = hitPower;
        this.hitContact = hitContact;
        this.handed = handed;
        this.hits = hits;
        this.name = name;
        this.number = number;

    }

    public double getSpeed() {
        return speed;
    }

    public double getThrowing() {
        return throwing;
    }

    public double getFielding() {
        return fielding;
    }

    public double getHitPower() {
        return hitPower;
    }

    public double getHitContact() {
        return hitContact;
    }

    public boolean getIsPitcher() {
        return isPitcher;
    }

    public char getHanded() {
        return handed;
    }

    public char getHits() {
        return hits;
    }

    public String getName() {return name;}

    public double[] getStatsArray() {
        double[] statsArray = {this.speed, this.throwing, this.fielding, this.hitPower, this.hitContact};
        return statsArray;
    }

    public void setPlayerPosition(String pos) {
        this.pos = pos;
    }

}

class Pitcher extends Player {

    private double fastball;
    private double curveball;
    private double slider;
    private double changeup;
    private boolean isPitcher;
    private double speed;
    private double throwing;
    private double fielding;
    private double hitPower;
    private double hitContact;
    private char handed;
    private char hits;
    private String name;
    private int number;
    private String pos;

    public Pitcher(boolean isPitcher,
                   double fastball,
                   double curveball,
                   double slider,
                   double changeup,
                   double speed,
                   double throwing,
                   double fielding,
                   double hitPower,
                   double hitContact,
                   char handed,
                   char hits,
                   String name,
                   int number) {
        super(isPitcher, speed, throwing, fielding, hitPower, hitContact, handed, hits, name, number);

        this.fastball = fastball;
        this.curveball = curveball;
        this.slider = slider;
        this.changeup = changeup;
        this.isPitcher = isPitcher;
        this.speed = speed;
        this.throwing = throwing;
        this.fielding = fielding;
        this.hitPower = hitPower;
        this.hitContact = hitContact;
        this.handed = handed;
        this.hits = hits;
        this.name = name;
        this.number = number;
    }

    public double getFastballRating(){return fastball;}
    public double getCurveballRating(){return curveball;}
    public double getSliderRating(){return slider;}
    public double getChangeupRating(){return changeup;}

    public double getPitchRating(int pitchType) {
        double pitchRating = 0.0;
        if (pitchType == 1)
            pitchRating = fastball;
        else if (pitchType == 2)
            pitchRating = curveball;
        else if (pitchType == 3)
            pitchRating = slider;
        else if (pitchType == 4)
            pitchRating = changeup;
        return pitchRating;
    }


}
