/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratemygame.gui.model;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ratemygame.be.Game;

public class GameModel {

    private final ObservableList<Game> ratings;

    public GameModel() {
        this.ratings = FXCollections.observableArrayList();
        ratings.add(new Game("Skyrim", 5));
        ratings.add(new Game("The Witcher 3", 5));
        ratings.add(new Game("No Mans Sky", 0));
    }

    /**
     * Get the ratings
     *
     * @return
     */
    public ObservableList<Game> getObservableRatings() {
        return ratings;
    }

    /**
     * Get the games as ArrayList
     *
     * @return
     */
    public ArrayList<Game> getGameRatings() {
        ArrayList<Game> gameRatings = new ArrayList<>();
        for (Game rating : ratings) {
            gameRatings.add(rating);
        }
        return gameRatings;
    }

    /**
     * Add a Game object to ratings List.
     *
     * @param gameToAdd the Game object to be added.
     */
    public void addGameToRatings(Game gameToAdd) {
        ratings.add(gameToAdd);
    }

    /**
     * Clears the ratings List.
     */
    public void clearRatings() {
        ratings.clear();
    }

    /**
     * Returns the highest game rating as String
     *
     * @param highestGame
     * @return
     */
    public String getHighestGameAsString(Game highestGame) {
        String highestGameAsString = "";
        highestGameAsString += highestGame.getTitle() + " - ";
        highestGameAsString += highestGame.getRating();
        return highestGameAsString;
    }

    /**
     * Returns the highest game rating as String
     *
     * @param lowestGame
     * @return
     */
    public String getLowestGameAsString(Game lowestGame) {
        String lowestGameAsString = "";
        lowestGameAsString += lowestGame.getTitle() + " - ";
        lowestGameAsString += lowestGame.getRating();
        return lowestGameAsString;
    }

    /**
     * Load saved list of game ratings
     *
     * @param savedGameRatings
     */
    public void loadSavedGameRatings(ArrayList<Game> savedGameRatings) {
        ratings.clear();
        ratings.addAll(savedGameRatings);
    }
}
