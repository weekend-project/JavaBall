import java.util.Random;

public class Pitch {
    private double pitchRating;
    private int pitchType;

    public Pitch(double rating, int type) {
        pitchRating = rating;
        pitchType = type;
    }

    public boolean throwFastball(double rating) {
        Random rand = new Random();
        boolean pitchSuccess = rand.nextDouble() <= (rating / 100); // if true, good pitch. if false, bad pitch.
        return pitchSuccess;
    }

    public boolean throwCurveball(double rating) {
        Random rand = new Random();
        boolean pitchSuccess = rand.nextDouble() <= (rating / 100); // if true, good pitch. if false, bad pitch.
        return pitchSuccess;
    }

    public boolean throwSlider(double rating) {
        Random rand = new Random();
        boolean pitchSuccess = rand.nextDouble() <= (rating / 100); // if true, good pitch. if false, bad pitch.
        return pitchSuccess;
    }

    public boolean throwChangeup(double rating) {
        Random rand = new Random();
        boolean pitchSuccess = rand.nextDouble() <= (rating / 100); // if true, good pitch. if false, bad pitch.
        return pitchSuccess;
    }

}
