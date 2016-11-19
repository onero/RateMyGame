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

    /**
     * Reads from the file parsed and returns the data as an ArrayList
     *
     * @param selectedFile
     * @return
     * @throws java.io.FileNotFoundException
     */
    public ArrayList<Game> readFromFile(File selectedFile) throws FileNotFoundException, IOException {
        ArrayList<Game> savedGameRatings = new ArrayList<>();
        try (BufferedReader gameReader = new BufferedReader(new FileReader(selectedFile))) {
            String line = gameReader.readLine();
            while (line != null) {
                String[] dataArray = line.split(",");
                savedGameRatings.add(new Game(
                        dataArray[0],
                        Double.parseDouble(dataArray[1])));
                line = gameReader.readLine();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
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
        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Text document (*.txt)", "*.txt");
        saveFile.getExtensionFilters().add(txtFilter);
        saveFile.setSelectedExtensionFilter(txtFilter);
        saveFile.setInitialFileName("gameRating");
        File gameRatingsFile = saveFile.showSaveDialog(new Stage());
        try (PrintWriter out = new PrintWriter(gameRatingsFile)) {
            for (Game gameRating : gameRatings) {
                out.write(gameRating.getDescription() + "," + gameRating.getRating());
                out.println();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        }
    }

}
