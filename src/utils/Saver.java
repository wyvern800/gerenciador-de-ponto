package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;

/**
 * Best and simple Production ready utility to save/load (read/write) data
 * from/to file
 */

public class Saver {

    private static String file_location = "data/pontos.json";
    private static Gson gson = new Gson();
    // - TotalVotes
    private static class Data {
        private int maxed;

        public int getMaxed() {
            return maxed;
        }

        public void setMaxed(int maxed) {
            this.maxed = maxed;
        }

    }

    // Main Method
    public static void main(String[] args) {
        Data dataToBeSaved = new Data();
        dataToBeSaved.setMaxed(Integer.valueOf(readFromFile()) + 1);

        // Save data to file
        writeToFile(gson.toJson(dataToBeSaved));
        // Retrieve data from file
        System.out.println(readFromFile());
    }

    public static void saveMaxedPlayers(int maxeds) {
        Data dataToBeSaved = new Data();
        dataToBeSaved.setMaxed(Integer.valueOf(readFromFile()) + maxeds);
        // Save data to file
        writeToFile(gson.toJson(dataToBeSaved));
    }

    public static void setMaxedPlayers(int maxeds) {
        Data dataToBeSaved = new Data();
        dataToBeSaved.setMaxed(Integer.valueOf(readFromFile()) + maxeds);
        // Save data to file
        writeToFile(gson.toJson(dataToBeSaved));
    }

    /*
     * pega quantia de votos
     */
    public static String getMaxedPlayers() {
        return readFromFile();
    }

    // Save to file Utility
    private static void writeToFile(String myData) {
        File file = new File(file_location);
        if (!file.exists()) {
            try {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                //log("Excepton Occured: " + e.toString());
            }
        }

        try {
            // Convenience class for writing character files
            FileWriter writter;
            writter = new FileWriter(file.getAbsoluteFile(), false);

            // Writes text to a character-output stream
            BufferedWriter bufferWriter = new BufferedWriter(writter);

            bufferWriter.write(myData.toString());
            bufferWriter.flush();
            bufferWriter.close();

            //log("Data saved at file location: " + file_location + " Data: " + myData + "\n");
        } catch (IOException e) {
            //log("Hmm.. Got an error while saving data to file " + e.toString());
        }
    }

    // Read From File Utility
    public static String readFromFile() {
        File file = new File(file_location);
        if (!file.exists()) {
            //log("File doesn't exist");
        }

        InputStreamReader isReader;
        try {
            isReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            JsonReader myReader = new JsonReader(isReader);
            Data dataToBeReaded = gson.fromJson(myReader, Data.class);
            Integer votes = dataToBeReaded.getMaxed();
            return votes.toString();
        } catch (Exception e) {
            //log("error load cache from file " + e.toString());
        }

        //log("\n Data loaded successfully from file " + file_location);
        return "0";
    }

    private static void log(String string) {
        System.out.println(string);
    }

}