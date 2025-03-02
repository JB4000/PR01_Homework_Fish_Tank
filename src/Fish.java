import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;

public class Fish {

    // decklaring instance variable for name of fish
    private String name;

    // declaring collection for holding log of health checks
    private ArrayList<FishHealthStatus> healthlog;

    // constructor
    public Fish(String name) {
        // initialize name instance variable of new fish
        this.name = name;

        // create collection for holding log of health checks for this fish
        this.healthlog = new ArrayList<>();

        // add a health check to collection of this fish
        // assuming that the fish is healthy at arrival
        // using helper method for formatting date and time of arrival
        this.healthlog.add(new FishHealthStatus(formatNow(LocalDateTime.now()), "Received in good health"));


    }

    public String getName() {
        // getter for getting name of this fish
        return this.name;
    }


    public void setName(String name) {
        // setter for setting name of this fish
        this.name = name;
    }


    public void updateHealthStatus(Scanner in) {
        // set health status for this fish
        // called from method updateFishListStatus() in FishTank instance
        // which lets the user choose between all the fish in the fish tank instance

        // declaring and initializing flag for do while loop of health menu for this fish
        boolean mainHealthMenuOn = true;

        do {
            // print the title of the log of health checks
            System.out.printf("The status of fish: " + this.name);
            System.out.println();

            // traverse through and print all the health logs for this fish
            // using the overridden toString in FishHealthStatus class
            for (FishHealthStatus fishHealthStatus : this.healthlog) {
                System.out.println(fishHealthStatus);
            }

            // ask if user wants to add a health check to the list
            System.out.println("Would you like to update the status right now (1)Yes (0)Exit \n");

            // get choice from user via helper method
            int choice = FishTankApp.getMenuChoice(in, 0, 1);

            // user wants to add a health check
            if (choice == 1) {

                // get new note from user
                // validated by helper method
                String update = getTextInput(in, "Enter the new status note (max 30 characters): ", 1, 30);

                // create new instance for the collection of health checks
                // date and time formatted by helper method
                healthlog.add(new FishHealthStatus(formatNow(LocalDateTime.now()), update));

                // print the newly created health log for this fish as a confirmation
                System.out.printf("The status of fish: " + this.name);
                System.out.println(this.healthlog.get(healthlog.size() - 1));

                // set flag for continuing do while loop for possible further checks
                mainHealthMenuOn = true;

            } else if (choice == 0) {
                // user wants to exit to sub menu
                // adjust flag to stop the do while loop
                mainHealthMenuOn = false;

            }
        } while (mainHealthMenuOn);


    }

    @Override
    public String toString() {
        // return String with name of this fish and its latest health check
        return String.format("Fish name: %-25s     %-25s", this.name, this.healthlog.get(healthlog.size() - 1));

    }



    public static String formatNow(LocalDateTime nowDateTime) {
        // helper method for formatting time and date
        return String.format("Date: %02d-%02d-%d  %02d:%02d:%02d ", nowDateTime.getDayOfMonth(), nowDateTime.getMonthValue(), nowDateTime.getYear(), nowDateTime.getHour(), nowDateTime.getMinute(), nowDateTime.getSecond());
    }

    public static String getTextInput(Scanner in, String prompt, int minLength, int maxLength) {

        // this is a helper method
        // it shows a message (prompt) and gets a String from user of length within parameters

        // print the prompt
        System.out.println(prompt);

        // declare empty String and a flag for do while loop
        String userInput;
        boolean accepted = false;

        do {

            // get input from user
            userInput = in.nextLine();


            // validate length
            if (userInput.length() < minLength || userInput.length() > maxLength) {
                // if out of range of length show tip and continue do while loop
                System.err.printf("You entered %2d characters.\n The length of your input has to be %2d to %2d characters long\nTry again.\n", userInput.length(), minLength, maxLength);

            } else {
                // if within range of length stop the do while loop
                accepted = true;
            }

        } while (!accepted);

        // return validated String
        return userInput;
    }
}




