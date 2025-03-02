public class FishTankWaterStatus {

    // declaring and initializing static variable
    // array of Strings
    // used for enum of water quality before a water change
    public static final String[] mapPreChangeQuality = {"perfect", "good", "okay", "bad"};

    // declaring non-static / instance variables for a water change
    private String dateOfChange;
    private String commentOnChange;
    // the int mapped to Strings in mapPreChangeQuality
    private int preChangeQuality;

    // constructor with all parameters
    public FishTankWaterStatus(String dateOfChange, int preChangeQuality, String commentOnChange) {
        this.dateOfChange = dateOfChange;
        this.preChangeQuality = preChangeQuality;
        this.commentOnChange = commentOnChange;

    }

    public String getDateOfChange() {
        return this.dateOfChange;
    }

    // overridden toString method
    public String toString() {
        // enum with mapPreChangeQuality String and preChangeQuality int happening here
        return String.format("%-27s Water quality before water change: %-15s Comment:  %-65s", this.dateOfChange, mapPreChangeQuality[this.preChangeQuality], this.commentOnChange);

    }

}
