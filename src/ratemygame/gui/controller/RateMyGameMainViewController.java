/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratemygame.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import ratemygame.be.Game;
import ratemygame.bll.GameRatingTemplate;
import ratemygame.gui.model.GameModel;

/**
 *
 * @author Adamino
 */
public class RateMyGameMainViewController implements Initializable {

    private Label label;
    @FXML
    private TextField txtDescription;
    @FXML
    private Button btnOpen;
    @FXML
    private Button btnSave;
    @FXML
    private TableColumn<Game, String> tableDescription;
    @FXML
    private TableColumn<Game, Double> tableRate;
    @FXML
    private TextField txtRate;
    @FXML
    private TextField txtHighestRated;
    @FXML
    private TextField txtLowestRated;
    @FXML
    private TextField txtAverage;
    @FXML
    private LineChart<?, ?> chart;
    @FXML
    private Button btnClear;

    private final GameRatingTemplate gameRatingTemplate;
    private final GameModel gameModel;

    /**
     *
     */
    public RateMyGameMainViewController() {
        gameRatingTemplate = new GameRatingTemplate();
        gameModel = new GameModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void handleAddGameRating(ActionEvent event) {

    }
}
