/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratemygame.bll;

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
    public Game getHighestRating(ArrayList<Game> games) {
        Game highestGame = null;
        double highestGameRating = -1;
        for (Game currentGame : games) {
            if (currentGame.getRating() > highestGameRating) {
                highestGame = currentGame;
                highestGameRating = currentGame.getRating();
            }
        }
        return highestGame;
    }

    /**
     * Get the lowest rating
     *
     * @param games
     * @return
     */
    public Game getLowestRating(ArrayList<Game> games) {
        Game highestGame = null;
        double highestGameRating = 100;
        for (Game currentGame : games) {
            if (currentGame.getRating() < highestGameRating) {
                highestGame = currentGame;
                highestGameRating = currentGame.getRating();
            }
        }
        return highestGame;
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
     * Gets all ratings and puts them in an arrayList. Then loop that arrayList
     * and check what range the rating is inside and add one to that rating.
     * Then save the amount of each rating in an arrayList and return that List.
     * @param games A List of all games that need to be checked.
     * @return A list containing how many games got a rating in each category.
     */
    public ArrayList<Integer> getAmountOfGamesWithSameRating(ArrayList<Game> games)
    {
        ArrayList<Double> ratingOfAllgames = new ArrayList<>();
        int ratingZero = 0, ratingOne = 0, ratingTwo = 0, ratingThree = 0, ratingFour = 0, ratingFive = 0;
        
        //Gets all the ratings and store them in an ArrayList.
        for(int i = 0; i < games.size(); i++)
        {
            ratingOfAllgames.add(games.get(i).getRating());
        }
        //Checks all ratings for what category they belong to and add one to that category.
        for(int i = 0; i < ratingOfAllgames.size(); i++)
        {
            if(ratingOfAllgames.get(i) >= 0 && ratingOfAllgames.get(i) < 1)
            {
                ratingZero++;
            }
            else if(ratingOfAllgames.get(i) >= 1 && ratingOfAllgames.get(i) < 2)
            {
                ratingOne++;
            }
            else if(ratingOfAllgames.get(i) >= 2 && ratingOfAllgames.get(i) < 3)
            {
                ratingTwo++;
            }
            else if(ratingOfAllgames.get(i) >= 3 && ratingOfAllgames.get(i) < 4)
            {
                ratingThree++;
            }
            else if(ratingOfAllgames.get(i) >= 4 && ratingOfAllgames.get(i) < 5)
            {
                ratingFour++;
            }
            else
            {
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
