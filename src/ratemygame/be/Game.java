/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratemygame.be;

public class Game {

    private final String title;
    private final double rating;

    public Game(String title, double rating) {
        this.title = title;
        this.rating = rating;
    }
    
    /**
     * Return the title of the game.
     * @return 
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Return the rating of the game.
     * @return 
     */
    public double getRating() {
        return rating;
    }

}
