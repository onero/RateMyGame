/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratemygame.bll;

import ratemygame.dal.GameDAO;

public class GameRatingManager {

    private final GameDAO gameDAO;

    public GameRatingManager() {

        gameDAO = new GameDAO();
    }

}
