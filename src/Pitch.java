import java.util.Random;

public class Pitch {
    private double pitchRating;
    private int pitchType;

    public Pitch(double rating, int type) {
        pitchRating = rating;
        pitchType = type;
    }

    public boolean throwFastball(double rating, int type) {
        Random rand = new Random();
        boolean pitchSuccess = rand.nextDouble() <= (rating / 100); // if true, good pitch. if false, bad pitch.
        return pitchSuccess;
    }
}
