/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratemygame.bll;

import ratemygame.be.Game;

/**
 *
 * @author Rasmus
 */
public class GameRatingTemplate implements IGameAdd
{
    /**
     * Returns a new Game object with the description and rating.
     * @param description of the game as String.
     * @param rating the rating of the game as Double.
     * @return  a Game object with the description and rating.
     */
    @Override
    public Game addGameRating(String description, double rating)
    {
        Game gameToAdd = new Game(description, rating);
        return gameToAdd;
    }    
}
