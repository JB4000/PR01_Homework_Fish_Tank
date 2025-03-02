import java.util.Scanner;
import java.util.ArrayList;

public class FishTankApp {


    public static void main(String[] args) {

        // Creating a collection of fish tanks
        ArrayList<FishTank> tankList;
        tankList = new ArrayList<>();

        // Creating an instance of FishTank class for Dianas first fish tank
        tankList.add(new FishTank("Dianas first fish tank"));
        // Creating two instances of Fish class for Dianas first fish tank
        tankList.getFirst().addFish("Wogglie");
        tankList.getFirst().addFish("Swimmie");






        // Creating an instance of Scanner for use in the whole program
        Scanner in = new Scanner(System.in);



        // declaring a variable for users choice of particular fish tank, exit or creating new
        int choice;

        // declaring a variable to hold users choice in a particular fish tanks sub menu
        int menuChoice;

        // declaring flag for loop condition of sub menu for particular fish tank
        boolean flagAppOn;


        // do while loop for main menu (need at least one presentation og main menu)
        do {
            // preparing flag for sub menu while loop
            // relevant when jump back and forth between main and sub menu

            flagAppOn = true;

            // call method for letting user choose a fish tank, creating more tanks or exiting
            choice = printTankMenu(in, tankList);
            //System.out.println(choice);

            // if user made the choice of exiting the program
            if (choice == 0) {
                // break out of main()
                break;
            }

            // printing sub menu for a particular fish tank and receiving choices
            while (flagAppOn) {

                // calling method for printing sub menu of particular fish tank (choice)
                // passing reference to collection of fish tanks
                printSubMenuOptions(choice, tankList);

                // calling helper method to get user sub menu choice in int of relevant range
                // the literals in the call are because the number of possible choices are always the same
                // should probable make a constant when not thinking a more options

                menuChoice = getMenuChoice(in, 0, 5);


                if (menuChoice == 1) {
                    // 1.  View this tanks fish and their latest health status

                    // call the relevant instance method of relevant fish tank instance in collection
                    tankList.get(choice-1).printFishList();


                } else if (menuChoice == 2) {
                    // 2.  View and update health logs of this tanks fish

                    // call the relevant instance method of relevant fish tank instance in collection
                    tankList.get(choice-1).updateFishListStatus(in);

                } else if (menuChoice == 3) {
                    // 3.  Add a new fish to this tank

                    // call the relevant instance method of relevant fish tank instance in collection
                    tankList.get(choice-1).addFish(in);


                } else if (menuChoice == 4) {
                    // 4.  View and update feeding log for this tank

                    // call the relevant instance method of relevant fish tank instance in collection
                    tankList.get(choice-1).feedTheFish(in);


                } else if (menuChoice == 5) {
                    // 5.  View and update log for water change of this tank

                    // call the relevant instance method of relevant fish tank instance in collection
                    tankList.get(choice-1).changeWater(in);


                } else if (menuChoice == 0) {
                    // 0 for exiting sub menu and going back to main fish tank menu

                    // set flag to false for ending the while loop of sub menu
                    flagAppOn = false;


                }

            }

        } while (true);

        in.close();

    }


    public static void printSubMenuOptions(int choice, ArrayList<FishTank> tankList) {

        // print which particular fish tank this standard sub menu is for
        System.out.println("\n\nThis is the menu for fish tank: " + tankList.get(choice-1).getName());

        System.out.println("Choose an option from the menu:");
        System.out.println();
        System.out.println("1.  View this tanks fish and their latest health status");
        System.out.println("2.  View and update health logs of this tanks fish");
        System.out.println("3.  Add a new fish to this tank");
        System.out.println("4.  View and update feeding log for this tank ");
        System.out.println("5.  View and update log for water change of this tank");

        System.out.println("(0 for returning to main fish tank menu)");


    }

    public static int printTankMenu(Scanner in, ArrayList<FishTank> tankList) {

        // declaring local variable for user choice in main menu
        int choice;

        // declaring local flag for stopping or continuing menu
        boolean flagTankMenu;

        // declaring a variable for names of new fish tanks
        String newTankName;

        // do while loop for presenting menu and making choice
        do {

            // printing available fish tanks
            System.out.println("These are your fishtanks:");

            // loop for geting and printing fish tank names and dates of creation
            for (int i = 0; i < tankList.size(); i++) {
                System.out.printf("%2d. %-25s          Established: %21s\n", (i + 1), tankList.get(i).getName(), tankList.get(i).getInitialDate());
            }
            System.out.printf("\nChoose a tank (0 for exit, %2d to add a new tank)\n", tankList.size()+1);

            // calling helper method to a int choice within relevant range
            choice = getMenuChoice(in, 0, tankList.size()+1);

            // when entering number just above total number of fish tanks a new will be created
            if (choice == tankList.size()+1) {
                // calling helper method with prompt and ranges to get text input of new tank name
                newTankName = getTextInput(in, "name the new tank (max 20 characters:\n", 1, 20);

                // add a fish tank instance to the collection of fish tanks
                tankList.add(new FishTank(newTankName));

                // user still needs to see the main fish tank menu after creating a new fish tank
                flagTankMenu = true;

            } else {
                // when choosing an existing fish tank or 0 for exit we are done with the loop
                flagTankMenu = false;

            }

        } while (flagTankMenu);

        // return the choice of an existing fish tank or the choice to exit program (0)
        return choice;
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
                System.err.printf("You entered %d characters.\n The length of your input has to be %2d to %2d characters long\nTry again.\n", userInput.length(), minLength, maxLength);

            } else {
                // if within range of length stop the do while loop
                accepted = true;
            }

        } while (!accepted);

        // return validated String
        return userInput;
    }




}







