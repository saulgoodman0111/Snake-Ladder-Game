package com.example.snakeandladderssp;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Player {
    private Circle coin;
   private int currentposition;
    private String name;

    private static Board gameboard=new Board();

    public Player(int tilesize, Color coincolor, String playername){
        coin=new Circle(tilesize/2);
        coin.setFill(coincolor);
        currentposition=0;
        moveplayer(1);
        name=playername;
    }
    public void moveplayer(int dicevalue){
        if(currentposition+dicevalue<=100)
            currentposition+=dicevalue;
            TranslateTransition secondmove=null, firstmove=translateanimation(dicevalue);
//        int x=gameboard.getxcord(currentposition);
//        int y=gameboard.getycord(currentposition);
//        coin.setTranslateX(x);
//        coin.setTranslateY(y);
          int newposition=gameboard.getnewpos(currentposition);
          if(newposition != currentposition && newposition !=-1)
          { currentposition=newposition;
              secondmove= translateanimation(6);
          }
          if(secondmove==null)
              firstmove.play();

          else{
              SequentialTransition sequentialTransition = new SequentialTransition(firstmove, new PauseTransition(Duration.millis(1000)),
                 secondmove );
              sequentialTransition.play();

          }
    }

    private TranslateTransition translateanimation(int dicevalue){
        TranslateTransition animate=new TranslateTransition(Duration.millis(100*dicevalue),coin);
        animate.setToX(gameboard.getxcord(currentposition));
        animate.setToY(gameboard.getycord(currentposition));
        animate.setAutoReverse(false);
        return animate;
    }

    public void startpos(){
        currentposition=0;
        moveplayer(1);
    }
    boolean winner(){
        if(currentposition==100)
            return true;
        return false;
    }
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentposition() {
        return currentposition;
    }

    public String getName() {
        return name;
    }
}
