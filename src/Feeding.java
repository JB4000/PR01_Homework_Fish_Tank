public class Feeding {

    // declaring instance variables for a feeding
    private String dateOfFeeding;
    private String typeOfFood;
    private String commentOnFeeding;

    // constructor with all parameters
    public Feeding(String dateOfFeeding, String typeOfFood, String commentOnFeeding) {
        this.dateOfFeeding = dateOfFeeding;
        this.typeOfFood = typeOfFood;
        this.commentOnFeeding = commentOnFeeding;


    }

    // overridden toString method
    public String toString() {
        return String.format("%-25s  Food: %-25s   Comment: %-60s", this.dateOfFeeding, this.typeOfFood, this.commentOnFeeding);

    }
}
