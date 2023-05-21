package com.example.tictac;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;


public class TicTacToe extends Application {
    private Button buttons [][]= new Button[3][3];
    private Label playerXLabel,playerOLabel;
    private boolean playerXTurn=true;
    private  int playerXScore=0,playerOScore=0;
public BorderPane createcontent(){
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(20));

    //label
    Label titleLabel= new Label("Tic Tac Toe");
    titleLabel.setStyle("-fx-font-size: 35pt; -fx-font-weight:bold;");
    root.setTop(titleLabel);
    BorderPane.setAlignment(titleLabel, Pos.CENTER);
    //gameBoard
    GridPane gridPane= new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setAlignment(Pos.CENTER);

    for (int i = 0;  i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            Button button= new Button();
            button.setStyle("-fx-font-size: 24pt; -fx-font-weight:bold;");

            button.setPrefSize(100,100);
            button.setOnAction(event->buttonclicked(button));
            buttons[i][j]=button;
            gridPane.add(button,i,j);

        }

    }
    root.setCenter(gridPane);
    BorderPane.setAlignment(gridPane, Pos.CENTER);
    //score
    HBox scoreBoard= new HBox(20);
    scoreBoard.setAlignment(Pos.CENTER);
    playerXLabel= new Label("Player X: 0");
    playerXLabel.setStyle("-fx-font-size: 12pt; -fx-font-weight:bold;");
    playerOLabel= new Label("Player O: 0");
    playerOLabel.setStyle("-fx-font-size: 12pt; -fx-font-weight:bold;");

    scoreBoard.getChildren().addAll(playerXLabel,playerOLabel);
    root.setBottom(scoreBoard);

    return  root;
}
//
private void buttonclicked(Button button){
    if(button.getText().equals("")) {
        if (playerXTurn)
            button.setText("X");
        else
            button.setText("O");

        playerXTurn = !playerXTurn;

    }
    checkwinner();
    return;
}

private void checkwinner(){

    //row
    for (int row = 0; row <3 ; row++) {
        if(buttons[row][0].getText().equals(buttons[row][1].getText())&&buttons[row][1].getText().equals(buttons[row][2].getText())&&!buttons[row][0].getText().isEmpty()){
//            System.out.println("winner");
            String winner = buttons[row][0].getText();
            winnerDialogue(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
        if(buttons[0][row].getText().equals(buttons[1][row].getText())&&buttons[1][row].getText().equals(buttons[2][row].getText())&&!buttons[0][row].getText().isEmpty()){
//            System.out.println("winner");
            String winner = buttons[0][row].getText();
            winnerDialogue(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
        if(buttons[0][0].getText().equals(buttons[1][1].getText())&&buttons[1][1].getText().equals(buttons[2][2].getText())&&!buttons[0][0].getText().isEmpty()){
//            System.out.println("winner");
            String winner = buttons[0][0].getText();
            winnerDialogue(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
        if(buttons[2][0].getText().equals(buttons[1][1].getText())&&buttons[1][1].getText().equals(buttons[0][2].getText())&&!buttons[2][0].getText().isEmpty()){
//            System.out.println("winner");
            String winner = buttons[2][0].getText();
            winnerDialogue(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
        boolean tie=true;
        for (Button rows[]:buttons)
        {
            for(Button button:rows){
               if(button.getText().isEmpty())
                   tie=false;
            }
        }
        if(tie){
            tieDialogue();
            resetBoard();
        }


    }
}
private void winnerDialogue(String winner ){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Winner");
    alert.setContentText("Congratulations "+winner+" !You won the game");
    alert.setHeaderText("");
    alert.showAndWait();
}

    private void tieDialogue( ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("Game Over! It's a tie");
        alert.setHeaderText("");
        alert.showAndWait();
    }


private void updateScore(String winner){
    if(winner.equals("X")){
        playerXScore++;
        playerXLabel.setText("Player X : "+playerXScore);
    }
    else{
        playerOScore++;
        playerOLabel.setText("Player O : "+playerOScore);
    }

}

private void resetBoard(){
    for (Button row[]:buttons)
    {
        for(Button button:row){
            button.setText("");
        }
    }
}

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createcontent());
        stage.setTitle("Tic Tac Toe   ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}