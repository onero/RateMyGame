/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratemygame.bll;

public abstract class IGameAdd {

    /**
     * Add a game to
     *
     * @param description
     * @param rating
     */
    public abstract void addGameRating(String description, double rating);

}
