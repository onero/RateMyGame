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
import java.util.logging.Level;
import java.util.logging.Logger;
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
        }
        return savedGameRatings;
    }

    /**
     * Saves the file
     *
     * @param gameRatings
     */
    public void saveFile(ArrayList<Game> gameRatings) {
        try {
            PrintWriter out = new PrintWriter("src/ratemygame/assets/gameRatings.txt");
            for (Game gameRating : gameRatings) {
                out.write(gameRating.getDescription() + "," + gameRating.getRating());
                out.println();
            }
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
