/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratemygame.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ratemygame.be.Game;
import ratemygame.bll.GameRatingTemplate;
import ratemygame.gui.model.GameModel;

/**
 *
 * @author Adamino
 */
public class RateMyGameMainViewController implements Initializable {

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
    @FXML
    private TableView<Game> tableGameRatings;

    /**
     *
     */
    public RateMyGameMainViewController() {
        gameRatingTemplate = new GameRatingTemplate();
        gameModel = new GameModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Game> ratingList
                = gameModel.getRatings();
        tableDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableRate.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tableGameRatings.setItems(ratingList);
    }

    /**
     * Checks if the textFields is not empty. If yes, create a new Game object
     * and add it to the rating List.
     *
     * @param event
     */
    public void handleAddGameRating(ActionEvent event) {
        if (!txtDescription.getText().equals("") && !txtRate.getText().equals("") && txtRate.getText().matches("\\d")) {
            String gameDescription = txtDescription.getText();
            double gameRating = Double.parseDouble(txtRate.getText());
            Game game = gameRatingTemplate.addGameRating(gameDescription, gameRating);
            gameModel.addGameToRatings(game);
            //Clears the textFields after use.
            txtDescription.setText("");
            txtRate.setText("");
            getMeanRatings();
        }
    }

    /**
     * Updates highest, lowest and average score
     */
    private void getMeanRatings() {
        String highestGame;
        double highestGameRating;

    }
}
