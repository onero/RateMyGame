/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratemygame.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ratemygame.be.Game;

public class GameModel {

    private final ObservableList<Game> ratings;

    public GameModel() {
        this.ratings = FXCollections.observableArrayList();
    }

    /**
     * Get the ratings
     *
     * @return
     */
    public ObservableList<Game> getRatings() {
        return ratings;
    }
    
    /**
     * Add a Game object to ratings List.
     * @param gameToAdd the Game object to be added.
     */
    public void addGameToRatings(Game gameToAdd)
    {
        ratings.add(gameToAdd);
    }

}
