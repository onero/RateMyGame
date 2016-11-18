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

}
