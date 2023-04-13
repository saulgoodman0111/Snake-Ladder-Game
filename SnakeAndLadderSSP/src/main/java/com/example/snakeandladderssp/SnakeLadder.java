package com.example.snakeandladderssp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tilesize=40, width=10, height=10;
    public static final int buttonline = height*tilesize + 30, infoline= buttonline-30;
    private static Dice dice=new Dice();
    private Player playerone, playertwo;

    private boolean gamestart=false, playeroneturn=false, playertwoturn=false;
    private Pane createContent() {
        Pane root=new Pane();
        root.setPrefSize(width*tilesize , height*tilesize + 70);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile=new Tile(tilesize);
                tile.setTranslateX(j*tilesize);
                tile.setTranslateY(i*tilesize);
                root.getChildren().add(tile);
            }
        }
        // adding image of snake and ladder
        Image sal=new Image("C:\\Users\\SAURABH\\IdeaProjects\\SnakeAndLadderSSP\\src\\main\\sal.png");
        ImageView board= new ImageView();
        board.setImage(sal);
        board.setFitHeight(height*tilesize);
        board.setFitWidth(width*tilesize);

        Button playeronebutton = new Button("First Player");
        Button playertwobutton = new Button("Second Player");
        Button startbutton = new Button("Start game");
        playeronebutton.setTranslateY(buttonline);
        playeronebutton.setTranslateX(20);
        playeronebutton.setDisable(true);
        playertwobutton.setTranslateY(buttonline);
        playertwobutton.setTranslateX(290);
        playertwobutton.setDisable(true);
        startbutton.setTranslateY(buttonline);
        startbutton.setTranslateX(150);

        //Info display
        Label playeronelabel = new Label("");
        Label playertwolabel = new Label(" ");
        Label dicelabel = new Label("Start !");

        playeronelabel.setTranslateY(infoline);
        playeronelabel.setTranslateX(30);
        playertwolabel.setTranslateY(infoline);
        playertwolabel.setTranslateX(320);
        dicelabel.setTranslateY(infoline);
        dicelabel.setTranslateX(170);
        playerone = new Player(tilesize-10,Color.BLACK, "Shubham");
        playertwo = new Player(tilesize-20, Color.PINK, "Kaddu");

        playeronebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gamestart==true){
                    if(playeroneturn==true){
                        int dicevalue=dice.getrolleddicevalue();
                        dicelabel.setText("Dice value: "+dicevalue);
                        playerone.moveplayer(dicevalue);
                        // winning cond
                        if(playerone.winner()==true){
                        dicelabel.setText("Winner is "+playerone.getName());
                            playeroneturn=false;
                            playeronebutton.setDisable(true);
                            playeronelabel.setText(" ");

                            playertwoturn=false;
                            playertwobutton.setDisable(true);
                            playertwolabel.setText("");

                            startbutton.setDisable(false);
                            startbutton.setText("Restart game");
                            gamestart=false;
                        }
                        else {
                            playeroneturn=false;
                            playeronebutton.setDisable(true);
                            playeronelabel.setText(" ");

                            playertwoturn=true;
                            playertwobutton.setDisable(false);
                            playertwolabel.setText(playertwo.getName()+"'s turn");
                        }

                    }
                }
            }
        });

        playertwobutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gamestart == true) {
                    if (playertwoturn == true) {
                        int dicevalue = dice.getrolleddicevalue();
                        dicelabel.setText("Dice value: " + dicevalue);
                        playertwo.moveplayer(dicevalue);

                        // winning condition
                        if (playertwo.winner() == true) {
                            dicelabel.setText("Winner is " + playertwo.getName());
                            playertwoturn = false;
                            playertwobutton.setDisable(true);
                            playertwolabel.setText(" ");
//                        playeroneturn=true;
//                        playeronebutton.setDisable(false);
//                        playeronelabel.setText(playerone.getName()+"'s turn");
                            playeroneturn = false;
                            playeronebutton.setDisable(true);
                            playertwolabel.setText(" ");

                            startbutton.setDisable(false);
                            startbutton.setText("Restart game");
                            gamestart=false;
                        } else {
                            playertwoturn = false;
                            playertwobutton.setDisable(true);
                            playertwolabel.setText(" ");

                            playeroneturn = true;
                            playeronebutton.setDisable(false);
                            playeronelabel.setText(playerone.getName() + "'s turn");
                        }
                    }
                }
            }
        });

        startbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gamestart=true;
                dicelabel.setText("Game on");
                startbutton.setDisable(true);
                playeroneturn=true;
                playeronelabel.setText("Your turn "+playerone.getName());
                playeronebutton.setDisable(false);
                playerone.startpos();

                playertwoturn=false;
                playertwolabel.setText(" ");
                playertwobutton.setDisable(true);
                playertwo.startpos();
            }
        });

        root.getChildren().addAll(board, playeronebutton, playertwobutton, startbutton,
                playeronelabel, playertwolabel, dicelabel, playerone.getCoin(), playertwo.getCoin() );

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Welcome to Snakes & Ladders!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}