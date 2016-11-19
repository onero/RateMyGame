/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratemygame.bll;

import ratemygame.be.Game;

public interface IGameAdd {

    /**
     * Add a game to
     *
     * @param description
     * @param rating
     * @return 
     */
    public Game addGameRating(String description, double rating);

}
