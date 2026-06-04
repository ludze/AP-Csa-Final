import java.util.ArrayList;

public class Participant {
//Charlie did this part
    private String name;
        private int grad;
      private int id;
     private ArrayList<String> acts;
    private int score;

    // constructor name, grade, id and activities and their score
    public Participant(String n, int g, int i, ArrayList<String> a, int s) {
        name = n;
               grad = g;
        id = i;
        acts = a;
               score = s;
    }

    // some get methods.
    public String getName() {
            return name;
    }

    public int getGrad() {
        return grad;
    }

    public int getId() {
        return id;
    }

    public ArrayList<String> getActs() {
        return  acts;
    }

    public int getScore() {
        return score;
    }

    // setter mnethods
    public void setScore(int s) {
        score = s;
    }
//Evan did this method for tostring
    public String toString() {
        return name + " || grade: " + grad + " || id: " + id + " || " + acts + " || " +
                " score: " + score;
    }
}
