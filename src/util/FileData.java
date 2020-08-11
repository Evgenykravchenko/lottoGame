package util;

import javafx.stage.Stage;

import java.io.*;

public class FileData {

    private static final String FILE_PATH = "src/resources/data/config.txt";

    private static boolean isMusicPlay;

    public FileData() {
        loadFile();
    }

    private void loadFile() {
        File file = new File(FILE_PATH);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                isMusicPlay = Boolean.parseBoolean(line);
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isMusicPlay() {
        return isMusicPlay;
    }

    public void updateData(String data) {
        try(FileWriter writer = new FileWriter(FILE_PATH, false))
        {
            writer.write(data);
            // запись по символам
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        loadFile();
    }


}
