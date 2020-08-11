package util;

import java.io.*;
import java.util.Properties;

public class PropertiesData {

    private static final String FILE_PATH = "src/resources/data/config.properties";

    private static boolean isMusicPlay;
    private static Properties properties;

    public PropertiesData() {
        loadFile();
    }

    private void loadFile() {
        File file = new File(FILE_PATH);

        properties = new Properties();

        try {
            properties.load(new FileReader(file));
            isMusicPlay = Boolean.parseBoolean(properties.getProperty("isMusicPlay"));
        } catch (IOException exception) {
            System.err.println("Can't open: " + FILE_PATH);
        }
    }

    public boolean getIsMusicPlay() {
        return isMusicPlay;
    }

    public void rewriteByKeyAndUpdateData(final String key, final String value) throws IOException {
        properties.setProperty(key, value);
        properties.store(new FileOutputStream(FILE_PATH), null);
        loadFile();
    }

    public static void main(String[] args) throws IOException {
        PropertiesData data = new PropertiesData();
        System.out.println(data.getIsMusicPlay());
        data.rewriteByKeyAndUpdateData("isMusicPlay", "true");
    }

}
