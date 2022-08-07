import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        //Instead of a single list held in memory, the new refactored program will be able to
        // save and load lists from disk.

        //You will add:
        //O – Open a list file from disk
        //S – Save the current list file to disk
        //C – Clear removes all the elements from the current list
        //V -  CHANGE THE P Print OPTION to V for View
        //You might notice that there is no explicit option for creating a new list.
        // To do this, we merely use the add option to build the list in memory.
        //The big challenge here is to keep track of the program state:
        //Use a boolean variable like needsToBeSaved to keep track of list edits.
        // (Traditionally this has been called a ‘dirty’ flag.
        // The file becomes ‘dirty’ when it needs to be saved.
        //If the user loads a list it does not need to be saved until
        // it is changed by adding or deleting items.
        // (The user could load a list only to view it and
        // then would want to load another in its place without saving…)
        //If the user begins to build a list by adding items and
        // does not load an existing list keep track of this new list.
        // Prompt the user on exit to save the list or abandon it.
        //Similarly, prompt the user to save an unsaved
        // list before loading a new list from disk.. etc.
        //Loaded lists are always saved with the same filename.
        //All list files have the .txt extension

        Scanner in = new Scanner(System.in);
        ArrayList<String> inputList = new ArrayList<>();
        boolean unsaved = false;
        do {
            String listOption = "";
            listOption = SafeInput.getRegExString(in,"Please pick an option", "[AaDdQqOoSsCcVv]");
            if (listOption.equalsIgnoreCase("a")){
                String addValue = SafeInput.getNonZeroLenString(in,"What would you like to add to the list?");
                inputList.add(addValue);
                unsaved = true;
            }else if (listOption.equalsIgnoreCase("d")) {
                int removeValue = SafeInput.getRangedInt(in, "Which value would you like to remove?", 0, inputList.size() - 1);
                inputList.remove(removeValue);
                unsaved = true;
            } else if (listOption.equalsIgnoreCase("o")){
                if (unsaved){
                    if (SafeInput.getYNConfirm(in,"You have an unsaved file, would you like to save?")){
                        String listName = SafeInput.getNonZeroLenString(in,"What would you like to name the list?");
                        String fileName = listName +".txt";
                        File newList = new File(fileName);
                        if (newList.createNewFile()) {
                            System.out.println("File created: " + newList.getName());
                        } else {
                            System.out.println("File already exists, overwriting");
                        }
                        FileWriter writer = new FileWriter(fileName);
                        for (int i = 0; i < inputList.size(); i++){
                            writer.write(inputList.get(i));
                        }
                        writer.close();
                    }
                }
                try {
                    //https://stackoverflow.com/questions/28141885/jfilechooser-showsavedialog-not-showing-up
                    //Used this to get JFileChooser working
                    in.nextLine();
                    in.close();
                    System.out.println("Opening File");
                    JFrame jf = new JFrame( "Dialog" );
                    jf.setAlwaysOnTop( true );
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog( jf );
                    jf.dispose();
                    File openedFile = fileChooser.getSelectedFile();
                    Scanner reader = new Scanner(openedFile);
                    //read each line (one at a time)
                   while (reader.hasNextLine()) {
                        String line = reader.nextLine();
                        inputList.add(line);
                    }
                    reader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred with the list you picked");
                    e.printStackTrace();
                }
                System.out.println("Opening file");
                display(inputList);
            }else if(listOption.equalsIgnoreCase("s")){ // save the current list file to disk
                String listName = SafeInput.getNonZeroLenString(in,"What would you like to name the list?");
                String fileName = listName +".txt";
                File newList = new File(fileName);
                if (newList.createNewFile()) {
                    System.out.println("File created: " + newList.getName());
                } else {
                    System.out.println("File already exists, overwriting");
                }
                FileWriter writer = new FileWriter(fileName);
                for (int i = 0; i < inputList.size(); i++){
                    writer.write(inputList.get(i));
                }
                writer.close();

            }else if(listOption.equalsIgnoreCase("c")){ // clear all elements from the current list
                inputList.clear();
            }else if(listOption.equalsIgnoreCase("v")){ // New version of Print
                display(inputList);
            }else{ // equals Q
                if (SafeInput.getYNConfirm(in,"Are you sure you want to quit? Unsaved lists will be lost")){
                    break;
                }
            }
        } while (true);
    }

    /**
     * @param list - list to be displayed
     * Displays the command options and list
     */
    private static void display(ArrayList<String> list){
        System.out.println("A - Add an item to the list\n" +
                "D - Delete an item from the list\n" +
                "V - View (i.e. display) the list\n" +
                "Q - Quit the program \n" +
                "O - Open a list file from disk \n" +
                "S - Save the current list file to disk\n" +
                "C - Clear removes all the elements from the current list\n"
        );

        System.out.println("List:");
        for (int i = 0; i < list.size(); i++){
            System.out.print(i + " " + list.get(i) + " \n");
        }
    }
}