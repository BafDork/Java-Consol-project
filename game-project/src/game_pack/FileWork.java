package game_pack;

import java.io.*;

public class FileWork {

    static String[] loadFromFile(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            return reader.lines().toArray(size -> new String[size]);
        }
        catch (FileNotFoundException ex) {
            System.out.println("File not found");
            ex.printStackTrace();
            return null;
        }
        catch (Exception ex) {
            System.out.println("Error File Read");
            ex.printStackTrace();
            return null;
        }
    }

    static void saveToFile(String file, String[] lines) {
        try(PrintStream printStream = new PrintStream(file)) {
            for (String line: lines)
                printStream.println(line);
        }
        catch(IOException ex){
            System.out.println("Error File Save");
            ex.printStackTrace();
        }
    }
}
