import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;

public class FishTank {


    //Attributes

    // declare a collection for holding Fish objects for the fish tank
    private ArrayList<Fish> fishList;

    // declare a collection for holding FishTankWaterStatus objects for log of water changes of the fish tank
    private ArrayList<FishTankWaterStatus> changeList;

    // declare a collection for Feeding objects for log of feeding tne fish in the fish tank
    private ArrayList<Feeding> feedingList;

    // declaring a String for the name of the fish tank
    private String tankName;




    //Constructor with only name parameter
    public FishTank(String tankName){

        // initializing fish tanks name
        this.tankName = tankName;

        // create the collection for holding the fish of the fish tank
        this.fishList = new ArrayList<>();

        // create the collection for holding the log of water changes in the fish tank
        this.changeList = new ArrayList<>();
        // create the first instance of water change as it is assumed user starts with a filled tank
        this.changeList.add(new FishTankWaterStatus(formatNow(LocalDateTime.now()), 0, "First filling of tank"));

        // create the collection for holding the log of feeding the fish in the tank
        this.feedingList = new ArrayList<>();
    }



    public String getName() {
        // getter method for the name of the fish tank
        return this.tankName;
    }

    public String getInitialDate() {
        // getter method for the date of the first instance of water change
        // that will be when the fish tank was first set up for use

        return this.changeList.getFirst().getDateOfChange();
    }


    //Change water method
    public void changeWater(Scanner in){

        // print the title og current log of water changes
        System.out.println("WaterChange Log: ");

        // looping through the instances of water change in the collection
        // and printing them via overridden toString method
        for (FishTankWaterStatus fishTankWaterStatus : this.changeList) {
            System.out.println(fishTankWaterStatus);
        }

        // give user the option of adding a water change or going back to sub menu
        System.out.println("\nWould you like to make a new water change now?: (1)Yes (0 for exit)\n");

        // call helper method to get user input as relevant int
        int choice = FishTankApp.getMenuChoice(in, 0, 1);

        if(choice == 0) {
            // if user enters 0 end the health check and return to sub menu
            return;
        }



        // call helper method to get remark of relevant length from user
        String userRemark = getTextInput(in, "Remark about water change (max 60 characters): ", 1, 60 );

        // print option for water quality before the water change
        System.out.println("""
                Set water quality before water change:
                Type 1 for perfect
                Type 2 for good
                Type 3 for okay
                Type 4 for bad""");

        // get choice as relevant int
        int userWaterQuality = getMenuChoice(in, 1, 4);

        // create a new instance of water change for the collection
        // the current data and time are formatted by helper method
        // userWaterQuality adjusted for fitting enum i FishTankWaterStatus starting from 0
        this.changeList.add(new FishTankWaterStatus(formatNow(LocalDateTime.now()),userWaterQuality-1, userRemark));


    }

    public String toString() {
        // this is an override
        // it gets a String of the most recent water change
        // using overridden toString instance method on last FishTankWaterStatus instance in the collection

        return this.changeList.getLast().toString();
    }

    public void feedTheFish(Scanner in) {

        // print title of feeding log
        System.out.println("Feeding Log: ");

        // loop through feeding logs in collection
        // and print them via overriden toString in Feeding class
        for (Feeding feeding : this.feedingList) {
            System.out.println(feeding);
        }

        // show message if there is no instances in feeding collection
        if (this.feedingList.isEmpty()) {
            System.out.println("There hasn't been any feedings in this fish tank yet\n");
        }

        // let the user enter type of food for this feeding
        // or exiting to sub menu via return
        // using helper method to validate length of text
        String typeOfFood = getTextInput(in, "Enter type of food (max 15 characters): (0 for exit)", 1, 15);
        if (typeOfFood.equals("0")) {
            return;
        }

        // let the user enter a comment on fish behaviour during feeding
        // using helper method to validate length of text
        String commentOnFeeding = getTextInput(in, "Enter a comment on fish behavior (max 30 characters): ", 1, 30);

        // create new instance og feeding for the collection
        // using helper method to format current time and date
        this.feedingList.add(new Feeding(formatNow(LocalDateTime.now()), typeOfFood, commentOnFeeding));

    }



    public void printFishList() {
        // method for showing the fish in a particular fish tank
        // including the latest health check with data and time

        // traverse the fish in the collection
        // print them using the overridden toString in Fish class

        for (int i = 0; i < this.fishList.size(); i++) {
            System.out.printf("%2d. %-25s\n", (i+1), this.fishList.get(i));
        }

        // show message if collection of fish for the fish tank is empty
        if (this.fishList.isEmpty()) {
            System.out.println("This fish tank doesn't have any fish yet\n");
        }
    }

    public Fish getFish(int index) {
        // getter that returns a particular Fish from the collection of fish in this fish tank
        return this.fishList.get(index);
    }

    public void updateFishListStatus(Scanner in) {
        // method for change health status of a fish

        // setting flag for the do while loop of the menu og choosing a fish
        boolean mainHealthMenuOn = true;

        // do while loop for letting the user get back to choosing another fish
        // after working on a particular fish
        // without having to leave all the way to the sub menu

        do {

            // print the list of fish in this fish tank
            // showing only the latest health update for every fish
            this.printFishList();

            // if the fish tank doesn't have fish then return to sub menu
            if (this.fishList.isEmpty()) {
                // the user will be informed via the printFishList method
                // print that the fish tank doesn't have any fish
                return;
            }

            // if the fish tank has fish ask the user to choose one
            System.out.println("Which fish do you want to change health status for? (0 for exit)");

            // use helper method to get an int within range of length of collection of fish
            int choice = FishTankApp.getMenuChoice(in, 0, this.fishList.size());

            // if the user choose a fish lets continue working on that fish
            if (choice != 0) {
                // call relevant instance method of chosen fish in collection of this fish tank
                // choice is adjusted to match first fish as index 0
                this.fishList.get(choice - 1).updateHealthStatus(in);

            } else {
                // if the user choose 0 for exit set the flag for stopping this health proces
                mainHealthMenuOn = false;
            }

        } while (mainHealthMenuOn);

    }

    public void addFish(Scanner in) {
        // add a fish to this tank
        // entering the name in the proces

        // get name of new fish from user
        // using helper method validating length
        String newName = getTextInput(in, "Enter name of new fish (max 20 characters)", 1, 20);

        // create new instance of Fish for the collection of fish in this fish tank
        this.fishList.add(new Fish(newName));

    }

    public void addFish(String newName) {
        // add a fish to this tank
        // with the name from parameter

        // create new instance of Fish for the collection of fish in this fish tank
        this.fishList.add(new Fish(newName));

    }

    public static String formatNow(LocalDateTime nowDateTime) {
        // helper method for formatting the time and date information in the parameter
        return String.format("%02d-%02d-%d  %02d:%02d:%02d ", nowDateTime.getDayOfMonth(), nowDateTime.getMonthValue(), nowDateTime.getYear(), nowDateTime.getHour(), nowDateTime.getMinute(), nowDateTime.getSecond());
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

    public static int getMenuChoice(Scanner in, int min, int max) {

        // this is a helper method returning an int from the user
        // it validates input of an int within range of min and max parameters

        // declaration of the int that will later be returned
        int choice;

        // declaring flag for controlling outer do while loop checking for range
        boolean accepted = false;

        // loop for range
        do {

            // loop for type
            while (true) {
                if (in.hasNextInt()) {
                    // if the user entered som kind of int break to outer loop for validating range
                    break;
                } else {
                    // if user entered something that is not an int give message and continue while loop
                    String tempString = in.next();
                    System.err.println(tempString + "is not a number. Try again");
                }
            }

            // assign entered int to variable
            choice = in.nextInt();

            // compare range of int to parameters
            if (choice < min || choice > max) {
                // if int outside range continue outer do while loop with inner while loop
                System.err.printf("You entered a number outside the range of: %2d to %2d\nTry again.\n", min, max);

            } else {
                // if int inside range stop the outer do while loop
                accepted = true;
            }

        } while (!accepted);

        // clear buffer to avoid Scanner bug when later entering text
        in.nextLine();

        // return the validated int
        return choice;
    }

}

