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
    private static final int HIGHEST_RATING = 5;
    private static final int LOWEST_RATING = -1;

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
        double highestGameRating = LOWEST_RATING;
        //For every game in the list check if the next one has a higher rating
        for (Game currentGame : games) {
            if (currentGame.getRating() > highestGameRating) {
                highestGame = currentGame;
                highestGameRating = currentGame.getRating();
            }
        }
        //For every game check how many game has the highest rating and add them to highGames
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
        double lowestGameRating = HIGHEST_RATING;
        //For every game in the list check if the next one has a lower rating
        for (Game currentGame : games) {
            if (currentGame.getRating() < lowestGameRating) {
                highestGame = currentGame;
                lowestGameRating = currentGame.getRating();
            }
        }
        //For every game check how many game has the lowest rating and add them to highGames
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
     * @throws java.io.FileNotFoundException
     */
    public void saveGameRatings(ArrayList<Game> gameRatings) throws FileNotFoundException {
        gameDAO.saveFile(gameRatings);
    }

    /**
     * Gets all ratings and puts them in an arrayList. Then loop that arrayList
     * and check what range the rating is inside and add one to that rating.
     * Then save the amount of each rating in an arrayList and return that List.
     *
     * @param games A List of all games that need to be checked.
     * @return A list containing how many games got a rating in each category.
     */
    public ArrayList<Integer> getAmountOfGamesWithSameRating(ArrayList<Game> games) {
        ArrayList<Double> ratingOfAllgames = new ArrayList<>();
        int ratingZero = 0, ratingOne = 0, ratingTwo = 0, ratingThree = 0, ratingFour = 0, ratingFive = 0;

        //Gets all the ratings and store them in an ArrayList.
        for (int i = 0; i < games.size(); i++) {
            ratingOfAllgames.add(games.get(i).getRating());
        }
        //Checks all ratings for what category they belong to and add one to that category.
        for (int i = 0; i < ratingOfAllgames.size(); i++) {
            if (ratingOfAllgames.get(i) >= 0 && ratingOfAllgames.get(i) < 1) {
                ratingZero++;
            } else if (ratingOfAllgames.get(i) >= 1 && ratingOfAllgames.get(i) < 2) {
                ratingOne++;
            } else if (ratingOfAllgames.get(i) >= 2 && ratingOfAllgames.get(i) < 3) {
                ratingTwo++;
            } else if (ratingOfAllgames.get(i) >= 3 && ratingOfAllgames.get(i) < 4) {
                ratingThree++;
            } else if (ratingOfAllgames.get(i) >= 4 && ratingOfAllgames.get(i) < 5) {
                ratingFour++;
            } else {
                ratingFive++;
            }
        }
        //Puts all the categorys in an ArrayList to return all the categorys.
        ArrayList<Integer> amountOfGamesWithSameRating = new ArrayList<>();
        amountOfGamesWithSameRating.add(ratingZero);
        amountOfGamesWithSameRating.add(ratingOne);
        amountOfGamesWithSameRating.add(ratingTwo);
        amountOfGamesWithSameRating.add(ratingThree);
        amountOfGamesWithSameRating.add(ratingFour);
        amountOfGamesWithSameRating.add(ratingFive);
        return amountOfGamesWithSameRating;
    }
}
