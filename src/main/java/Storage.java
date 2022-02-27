import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage represents the Read and Write functions of the system
 */
public class Storage {
    protected static String fileContent = "";
    protected static boolean isFileSaved = false;

    public Storage() {
    }

    /**
     * Try-Catch block of ArrayList when reading the file
     *
     * @param addLists The main list of the program
     * @return The main list back
     */
    protected static ArrayList<Task> tryCatchReadFile(ArrayList<Task> addLists) {
        try {
            File file = new File("savedDukeData.txt");
            if (file.exists()) {
                readFromFile("savedDukeData.txt");
                InsertFileContent insertFileContent = new InsertFileContent(fileContent, addLists);
                addLists = insertFileContent.getAddLists();
                isFileSaved = true;
            } else {
                PrintWriter writer = new PrintWriter("savedDukeData.txt", "UTF-8");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addLists;
    }

    /**
     * The function that starts the reading process
     *
     * @param filePath The path where the file is saved
     * @throws FileNotFoundException Exception thrown when no file is found
     */
    protected static void readFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            fileContent = fileContent.concat(scan.nextLine() + "\n");
        }
    }

    /**
     * The function that starts the saving process of the content
     *
     * @param addLists The main list of the program
     * @throws FileNotFoundException Exception thrown when no file is found
     */
    protected static void programmeExits(ArrayList<Task> addLists) throws FileNotFoundException {
        WriteOutFileContent writeOutFileContent = new WriteOutFileContent(addLists);
        writeOutFileContent.getWriteForAll();
        System.out.println("Bye. Until we meet again!");
    }

    /**
     * Lists out all the features of the product with example
     */
    protected static void helpCommand() {
        System.out.println("-------------------------");
        System.out.println("Here are the basic functions of the system.");
        System.out.println("List function example:");
        System.out.println("list");
        System.out.println("Mark function example:");
        System.out.println("mark <number in list>");
        System.out.println("Unmark function example:");
        System.out.println("unmark <number in list>");
        System.out.println("Todo function example:");
        System.out.println("todo <description>");
        System.out.println("Deadline function example:");
        System.out.println("deadline <description> /by <YYYY--MM-DD>");
        System.out.println("Event function example:");
        System.out.println("event <description> /at <YYYY--MM-DD>");
        System.out.println("Delete function example:");
        System.out.println("delete <number in list>");
        System.out.println("Find function example:");
        System.out.println("find <keyword>");
        System.out.println("Bye function example:");
        System.out.println("bye");
        System.out.println("-------------------------");
    }

}
