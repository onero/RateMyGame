/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratemygame.gui.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ratemygame.be.Game;
import ratemygame.bll.GameRatingManager;
import ratemygame.bll.GameRatingTemplate;
import ratemygame.gui.model.GameModel;

public class RateMyGameMainViewController implements Initializable {

    @FXML
    private TextField txtDescription;
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
    private LineChart<Double, Integer> chart;

    private final GameRatingTemplate gameRatingTemplate;
    private final GameRatingManager gameRatingManager;
    private final GameModel gameModel;

    @FXML
    private TableView<Game> tableGameRatings;

    public RateMyGameMainViewController() {
        gameRatingTemplate = new GameRatingTemplate();
        gameModel = new GameModel();
        gameRatingManager = new GameRatingManager();
    }

    /**
     * Initialize the view with game rating information
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Game> ratingList
                = gameModel.getObservableRatings();
        tableDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableRate.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tableGameRatings.setItems(ratingList);
    }

    /**
     * Checks if the textFields is not empty. If yes, create a new Game object
     * and add it to the rating List.
     *
     */
    public void handleAddGameRating() {
        if (!txtDescription.getText().equals("")
                && !txtRate.getText().equals("")
                //Check that input is a number between 0-5 and 0.0-5.0
                && txtRate.getText().matches("([0-4].\\d|5.0|[0-5])")) {
            String gameDescription = txtDescription.getText();
            double gameRating = Double.parseDouble(txtRate.getText());
            //Create a new game and add it to the model
            Game game = gameRatingTemplate.addGameRating(gameDescription, gameRating);
            gameModel.addGameToRatings(game);
            //Clears the textFields after use.
            txtDescription.setText("");
            txtRate.setText("");
            //Uodate statistics after game is added
            getMeanRatings();
        }
    }

    /**
     * Updates highest, lowest and average score
     */
    private void getMeanRatings() {
        ArrayList<Game> highestGames = gameRatingManager.getHighestRating(gameModel.getGameRatings());
        ArrayList<Game> lowestGames = gameRatingManager.getLowestRating(gameModel.getGameRatings());
        //If more than one game has the same score then show how many share the same score, otherwise just show high/low game
        if (highestGames.size() > 1) {
            txtHighestRated.setText(highestGames.size() + " games with the rating of " + highestGames.get(0).getRating());
        } else {
            txtHighestRated.setText(gameModel.getHighestGameAsString(highestGames.get(0)));
        }
        if (lowestGames.size() > 1) {
            txtLowestRated.setText(lowestGames.size() + " games with the rating of " + lowestGames.get(0).getRating());
        } else {
            txtLowestRated.setText(gameModel.getLowestGameAsString(lowestGames.get(0)));
        }
        double averageScore = gameRatingManager.getAverageScore(gameModel.getGameRatings());
        txtAverage.setText("" + averageScore);
    }

    /**
     * Clears the ratings List.
     *
     */
    public void handleClearRatingList() {
        txtHighestRated.setText("");
        txtLowestRated.setText("");
        txtAverage.setText("");
        gameModel.clearRatings();
    }

    /**
     * Opens file and sends data to model
     *
     * @throws java.io.IOException
     */
    public void handleOpenFile() throws IOException {
        //Create new window for opening a file
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Find game ratings");
        File selectedFile = chooser.showOpenDialog(new Stage());
        gameModel.loadSavedGameRatings(gameRatingManager.readFile(selectedFile));
        getMeanRatings();
    }

    /**
     * Save the data from the model to a file
     *
     * @throws java.io.FileNotFoundException
     */
    public void handleSaveFile() throws FileNotFoundException {
        gameRatingManager.saveGameRatings(gameModel.getGameRatings());

    }
}
