/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratemygame.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
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
    private LineChart<Number, Number> chart;
    @FXML
    private Button btnClear;
    @FXML
    private TableView<Game> tableGameRatings;

    private final GameRatingTemplate gameRatingTemplate;
    private final GameRatingManager gameRatingManager;
    private final GameModel gameModel;

    
    public RateMyGameMainViewController() {
        gameRatingTemplate = new GameRatingTemplate();
        gameModel = new GameModel();
        gameRatingManager = new GameRatingManager();  
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Game> ratingList
                = gameModel.getObservableRatings();
        tableDescription.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableRate.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tableGameRatings.setItems(ratingList);
        
        //
        updateLineChart();
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
            
            //Set the chart.
            updateLineChart();
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
        txtAverage.setText("");
        txtRate.setText("");
        gameModel.clearRatings();
        updateLineChart();
    }
    
    /**
     * Updates the lineChart.
     */
    private void updateLineChart()
    {
        //Clears the chart for exiting data, ready to receive new one.
        chart.getData().clear(); 
        //Gets the data the chart shall display.
        ArrayList<Integer> amountOfGamesWithSameRating = gameRatingManager.getAmountOfGamesWithSameRating(gameModel.getGameRatings());
        //Create the serie that holds the data in the chart.
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for(int i = 0; i < amountOfGamesWithSameRating.size(); i++)
        {
            //Filling the serie with the data.
            series.getData().add(new XYChart.Data<>(i, amountOfGamesWithSameRating.get(i)));
        }
        //Adds the serie to the chart, so it will display it.
        chart.getData().add(series);
    }
}
