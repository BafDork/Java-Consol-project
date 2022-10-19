package game_pack;

import java.util.HashMap;

public class Graphics {

    private static String frame;
    private final static String[] files;
    private final static String loadFile;
    private final static HashMap<String, String[]> graphics;

    public Graphics() {};

    static {
        frame = "deadEnd";
        graphics = new HashMap<>();
        loadFile = "game_data/graphics/";
        files = new String[]{"name", "chest", "forward", "left", "right", "right-left",
        "leftForward", "rightForward", "allParties", "deadEnd", "exit",
        "skeleton", "reaper", "alien", "devil", "dragon", "gryphon"};
    }

    public static void loadGraphics() {
        for (String file : files) {
            String[] frame = FileWork.loadFromFile(loadFile + file + ".txt");
            if (frame != null) {
                graphics.put(file, frame);
            }
            else {
                System.out.println("The file is empty");
            }
        }
    }

    public static void showFrame() {
        String[] frame = graphics.get(getFrame());
        for (String line : frame) {
            System.out.println(line);
        }
    }

    public static void showFrame(String name, boolean flag) {
        String[] frame = graphics.get(name);
        if (flag) {
            setFrame(name);
        }
        for (String line : frame) {
            System.out.println(line);
        }
    }

    public static String getFrame() {
        return frame;
    }

    public static void setFrame(String frame) {
        Graphics.frame = frame;
    }
}
