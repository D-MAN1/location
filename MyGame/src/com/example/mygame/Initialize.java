package com.example.mygame;

import MyTexture.MyTexture;
import android.util.Log;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Initialize extends Game{

	
	private static final String LOG = "myLogs";
	private World world;
	private Stage stage;
	MyScreen myScreen;
	@Override
	public void create() {
   
  //myScreen= new MyScreen(this);
		MyTexture.loadTexture();
		
		//setScreen(new MyScreen());
		setScreen(new StartMenu(this));
	}
	public void createMyScreen(){
		dispose();
		 myScreen= new MyScreen(this);
		setScreen(myScreen);
}
	public void createStartMenu(){
		myScreen=null;
		dispose();
		setScreen(new StartMenu(this));
		
		//Log.d(LOG, "Data.gameOver="+Data.gameOver);
	}
	/*public void render() {
        super.render(); // важно!
    } 
	*/
	
	
}
