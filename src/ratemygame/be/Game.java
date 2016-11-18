/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratemygame.be;

public class Game {

    private String description;
    private double rating;

    public Game(String description, double rating) {
        this.description = description;
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public double getRating() {
        return rating;
    }

}
