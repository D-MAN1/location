package com.example.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import android.R.integer;
import android.util.Log;

public class Data {
public  static int score=0;
public static int v= 30;
public static State state= State.PLAY;
public static int i=0;
public static String LOG="myLogs";
public static String name1= "1";
public static String name2= "2";
public static int cheesX=1;
public static int cheesY=7;
public static Array<Actor>arrayPosCheesArray=new Array<Actor>();

public static boolean playerRemov=false;
public static boolean gameOver= false;
public static int flyCount=10;
       public Data(){
    	   
}
}
