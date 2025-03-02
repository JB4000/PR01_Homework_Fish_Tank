public class FishHealthStatus {

    // declaring instance variable for date of health check
    private String dateOfCheck;

    // declaring instance variable for note on health check
    private String commentOnCheck;

    // constructor with no parameters
    public FishHealthStatus() {
        this.dateOfCheck = null;
        this.commentOnCheck = null;
    }

    // constructor with all parameters
    public FishHealthStatus(String dateOfCheck, String commentOnCheck) {
        this.dateOfCheck = dateOfCheck;
        this.commentOnCheck = commentOnCheck;
    }

    public void setDateOfCheck(String dateOfCheck) {
        this.dateOfCheck = dateOfCheck;
    }

    public void setCommentOnCheck(String commentOnCheck) {
        this.commentOnCheck = commentOnCheck;
    }

    public String getDateOfCheck (String dateOfCheck) {
        return this.dateOfCheck;
    }

    public String getCommentOnCheck (String commentOnCheck) {
        return this.commentOnCheck;
    }

    // overridden toString method
    public String toString() {
        return String.format("%-25s      Note: %-70s", this.dateOfCheck, this.commentOnCheck);

    }
}


