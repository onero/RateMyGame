/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratemygame.bll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import ratemygame.be.Game;
import ratemygame.dal.GameDAO;

public class GameRatingManager {

    private final GameDAO gameDAO;

    public GameRatingManager() {

        gameDAO = new GameDAO();
    }

    /**
     * Get the highest rating
     *
     * @param games
     * @return
     */
    public ArrayList<Game> getHighestRating(ArrayList<Game> games) {
        Game highestGame = null;
        ArrayList<Game> highGames = new ArrayList<>();
        double highestGameRating = -1;
        for (Game currentGame : games) {
            if (currentGame.getRating() > highestGameRating) {
                highestGame = currentGame;
                highestGameRating = currentGame.getRating();
            }
        }
        for (Game currentGame : games) {
            if (currentGame.getRating() == highestGame.getRating()) {
                highGames.add(currentGame);
            }
        }
        return highGames;
    }

    /**
     * Get the lowest rating
     *
     * @param games
     * @return
     */
    public ArrayList<Game> getLowestRating(ArrayList<Game> games) {
        Game highestGame = null;
        ArrayList<Game> lowGames = new ArrayList<>();
        double highestGameRating = 100;
        for (Game currentGame : games) {
            if (currentGame.getRating() < highestGameRating) {
                highestGame = currentGame;
                highestGameRating = currentGame.getRating();
            }
        }
        for (Game currentGame : games) {
            if (currentGame.getRating() == highestGame.getRating()) {
                lowGames.add(currentGame);
            }
        }
        return lowGames;
    }

    /**
     * Get the average rating
     *
     * @param games
     * @return
     */
    public double getAverageScore(ArrayList<Game> games) {
        double total = 0;
        int amount = 0;
        double average;
        for (Game game : games) {
            total += game.getRating();
            amount++;
        }
        average = total / amount;
        return average;
    }

    /**
     * Opens new file and gets data to controller
     *
     * @return
     */
    public ArrayList<Game> savedRatings() {
        return null;
    }

    /**
     * Sends the file to the GameDAO and gets back information for the
     * controller
     *
     * @param selectedFile
     * @return
     * @throws java.io.IOException
     */
    public ArrayList<Game> readFile(File selectedFile) throws IOException {
        return gameDAO.readFromFile(selectedFile);
    }

    /**
     * Sends the file to the GameDAO to save the file at the selected location
     *
     * @param gameRatings
     */
    public void saveGameRatings(ArrayList<Game> gameRatings) throws FileNotFoundException {
        gameDAO.saveFile(gameRatings);
    }
}
