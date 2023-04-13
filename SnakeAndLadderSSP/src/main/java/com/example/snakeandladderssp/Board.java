package com.example.snakeandladderssp;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer,Integer>> poscord;

    ArrayList<Integer> snakeladdderpos;

    public Board(){
        poscord=new ArrayList<>();
        populate();
        populatesnakeladder();
    }

    private void populate(){
        poscord.add(new Pair<>(0,0)); //dummy
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                int xcord=0;
                if(i%2==0)
                    xcord=j*SnakeLadder.tilesize + SnakeLadder.tilesize/2;
                else
                    xcord= SnakeLadder.tilesize*SnakeLadder.height-(j*SnakeLadder.tilesize)-(SnakeLadder.tilesize/2);

                int ycord=SnakeLadder.tilesize*SnakeLadder.height-(i*SnakeLadder.tilesize)-(SnakeLadder.tilesize/2);
                poscord.add(new Pair<>(xcord,ycord));
            }
            
        }
    }


    private void populatesnakeladder(){
        snakeladdderpos=new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeladdderpos.add(i);
        }
        snakeladdderpos.set(2,38);
        snakeladdderpos.set(8,31);
        snakeladdderpos.set(21,42);
        snakeladdderpos.set(46,84);
        snakeladdderpos.set(51,67);
        snakeladdderpos.set(71,91);
        snakeladdderpos.set(80,99);
        snakeladdderpos.set(33,5);
        snakeladdderpos.set(54,34);
        snakeladdderpos.set(63,16);
        snakeladdderpos.set(93,74);
        snakeladdderpos.set(97,61);
    }

    public int getnewpos(int currentpos){
        if(currentpos>0 && currentpos<=100)
            return snakeladdderpos.get(currentpos);
        return -1;
    }
    int getxcord(int position){
        if(position>=1 && position<=100)
            return poscord.get(position).getKey();
        return -1;

    }

    int getycord(int position){
        if(position>=1 && position<=100)
            return poscord.get(position).getValue();
        return -1;}

//    public static void main(String[] args) {
//        Board board = new Board();
//        for (int i = 0; i < board.poscord.size(); i++) {
//            System.out.println(i+" "+board.poscord.get(i).getKey()+" "+board.poscord.get(i).getValue());
//        }
//    }
}
