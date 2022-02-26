import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected static String fileContent = "";
    protected static boolean isFileSaved = false;

    public Storage() {
    }

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

    protected static void readFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            fileContent = fileContent.concat(scan.nextLine() + "\n");
            //addListsCounter++;
        }
    }

    protected static void programmeExits(ArrayList<Task> addLists) throws FileNotFoundException {
        WriteOutFileContent writeOutFileContent = new WriteOutFileContent(addLists);
        writeOutFileContent.getWriteForAll();
        System.out.println("Bye. Until we meet again!");
    }

}
