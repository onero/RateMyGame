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
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ratemygame.be.Game;
import ratemygame.bll.GameRatingManager;
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
    private final LineChart<Number, Number> chart;
    @FXML
    private Button btnClear;
    @FXML
    private TableView<Game> tableGameRatings;

    private final GameRatingTemplate gameRatingTemplate;
    private final GameRatingManager gameRatingManager;
    private final GameModel gameModel;
    
    private final NumberAxis xAxis = new NumberAxis();
    private final NumberAxis yAxis = new NumberAxis();
    private XYChart.Series series;

    /**
     *
     */
    public RateMyGameMainViewController() {
        gameRatingTemplate = new GameRatingTemplate();
        gameModel = new GameModel();
        gameRatingManager = new GameRatingManager();
        chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle("Hello World!");
        series = new XYChart.Series<>();
        series.setName("Hello World");
        series.getData().add(new XYChart.Data<>(10, 10));
        series.getData().add(new XYChart.Data<>(100, 100));
        chart.getData().add(series);
    }

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
        Game highestGame = gameRatingManager.getHighestRating(gameModel.getGameRatings());
        txtHighestRated.setText(gameModel.getHighestGameAsString(highestGame));
        Game lowestGame = gameRatingManager.getLowestRating(gameModel.getGameRatings());
        txtLowestRated.setText(gameModel.getLowestGameAsString(lowestGame));
        double averageScore = gameRatingManager.getAverageScore(gameModel.getGameRatings());
        txtAverage.setText("" + averageScore);
    }

    /**
     * Clears the ratings List.
     *
     * @param event
     */
    public void handleClearRatingList(ActionEvent event) {
        txtHighestRated.setText("");
        txtLowestRated.setText("");
        txtRate.setText("");
        gameModel.clearRatings();
    }
}
