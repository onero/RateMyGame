/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratemygame.dal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ratemygame.be.Game;

public class GameDAO {

    private static final String SPLIT_STRING = ",";
    public static final String DEFAULT_DIRECTORY = "src/ratemygame/assets";

    /**
     * Reads from the file parsed and returns the data as an ArrayList
     *
     * @param selectedFile
     * @return
     * @throws java.io.FileNotFoundException
     */
    public ArrayList<Game> readFromFile(File selectedFile) throws FileNotFoundException, IOException {
        ArrayList<Game> savedGameRatings = new ArrayList<>();
        //Try to open the parsed file
        try (BufferedReader gameReader = new BufferedReader(new FileReader(selectedFile))) {
            String line = gameReader.readLine();
            while (line != null) {
                //On each line split Array entry on ","
                String[] dataArray = line.split(SPLIT_STRING);
                savedGameRatings.add(new Game(
                        //Add description of game as first entry
                        dataArray[0],
                        //Add game rating as second entry
                        Double.parseDouble(dataArray[1])));
                line = gameReader.readLine();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        } catch (IOException ioe) {
            System.out.println("Something terrible just happened!");
        }
        return savedGameRatings;
    }

    /**
     * Save the file
     *
     * @param gameRatings
     * @throws java.io.FileNotFoundException
     */
    public void saveFile(ArrayList<Game> gameRatings) throws FileNotFoundException {
        FileChooser saveFile = new FileChooser();
        //Define that only .txt files will be saved
        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Text document (*.txt)", "*.txt");
        saveFile.getExtensionFilters().add(txtFilter);
        saveFile.setSelectedExtensionFilter(txtFilter);
        File defaultDirectory = new File(DEFAULT_DIRECTORY);
        saveFile.setInitialDirectory(defaultDirectory);
        //Define initial file name
        saveFile.setInitialFileName("gameRating");
        File gameRatingsFile = saveFile.showSaveDialog(new Stage());
        try (PrintWriter out = new PrintWriter(gameRatingsFile)) {
            for (Game gameRating : gameRatings) {
                out.write(gameRating.getTitle() + "," + gameRating.getRating());
                out.println();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        }
    }

}
