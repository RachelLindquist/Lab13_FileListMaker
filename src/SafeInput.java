import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SafeInput {

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = "";  // Set this to zero length. Loop runs until it isn’t
        do
        {
            System.out.print("\n" +prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;

    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return an int response
     */
    public static int getInt(Scanner pipe, String prompt){
        int retInt = 0;
        String trash = "";
        boolean badInput = true;
        do{
            System.out.print("\n" +prompt + ": "); // show prompt add space
            if(pipe.hasNextInt()){
                retInt = pipe.nextInt();
                pipe.nextLine();
                badInput = false;
            } else{
                trash = pipe.nextLine();
                pipe.nextLine();
            }
        }while (badInput);

        return retInt;
    }


    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a double response
     */
    public static double getDouble(Scanner pipe, String prompt){
        double retDouble = 0;
        String trash = "";
        boolean badInput = true;
        do{
            System.out.print("\n" +prompt + ": "); // show prompt add space
            if(pipe.hasNextDouble()){
                retDouble = pipe.nextDouble();
                pipe.nextLine();
                badInput = false;
            } else{
                trash = pipe.nextLine();
                pipe.nextLine();
            }

        }while (badInput);

        return retDouble;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param low low value for input range
     * @param high high value for input range
     * @return an int response between the 2 values
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high){
        int retInt = 0;
        String trash = "";
        boolean badInput = true;
        do{
            System.out.print("\n" +prompt + "(between " + low + " and " + high + "): "); // show prompt add space
            if(pipe.hasNextInt()){
                retInt = pipe.nextInt();
                if (retInt >= low && retInt <= high){
                    badInput = false;
                }
                pipe.nextLine();
            } else{
                trash = pipe.nextLine();
                pipe.nextLine();
            }

        }while (badInput);

        return retInt;
    }


    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param low low value for input range
     * @param high high value for input range
     * @return a double response between the 2 values
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high){
        double retDouble = 0;
        String trash = "";
        boolean badInput = true;
        do{
            System.out.print("\n" + prompt + "(between " + low + " and " + high + "): "); // show prompt add space
            if(pipe.hasNextDouble()){
                retDouble = pipe.nextDouble();
                if (retDouble >= low && retDouble <= high){
                    badInput = false;
                }
                pipe.nextLine();
            } else{
                trash = pipe.nextLine();
                pipe.nextLine();
            }

        }while (badInput);

        return retDouble;
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a double response between the 2 values
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt){
        String retString = "";  // Set this to zero length. Loop runs until it isn’t
        do
        {
            System.out.print("\n" +prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
            pipe.nextLine();
        }while(!(retString.equalsIgnoreCase("n") || retString.equalsIgnoreCase("y")));
        if (retString.equalsIgnoreCase("n")){
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param regEx the regEx pattern in java String format to use for matching
     * @return a double response between the 2 values
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx){
        Pattern pattern = Pattern.compile(regEx);
        String inputString = "";
        boolean match = false;
        do
        {
            System.out.print("\n" +prompt + " (Using format " + regEx + "):"); // show prompt add space
            inputString = pipe.nextLine();
            Matcher matcher = pattern.matcher(inputString);
            match = matcher.find();
        }while(!match);

        return inputString;
    }

    /**
     *
     * @param msg a message to be output
     */
    public static void prettyHeader(String msg){
        for (int i = 0; i < 60; i++){
            System.out.print("*");
        }
        System.out.print("\n***");  //I could use a for loop here, but it's only 3 stars
        if (msg.length()%2 == 0){
            for (int i = 0; i < (27 - msg.length() / 2); i++) {
                System.out.print(" ");
            }
            System.out.print(msg);
            for (int i = 0; i < (27 - msg.length() / 2); i++) {
                System.out.print(" ");
            }
        } else { //He's gonna be off center a bit
            for (int i = 0; i < (26 - msg.length() / 2); i++) {
                System.out.print(" ");
            }
            System.out.print(msg);
            for (int i = 0; i < (27 - msg.length() / 2); i++) {
                System.out.print(" ");
            }
        }
        System.out.print("***\n");
        for (int i = 0; i < 60; i++){
            System.out.print("*");
        }
    }



}
