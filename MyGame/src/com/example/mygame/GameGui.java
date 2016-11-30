package com.example.mygame;

import MyTexture.MyTexture;
import android.nfc.NfcAdapter.CreateBeamUrisCallback;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameGui extends Stage{
	private static final String LOG = "myLogs";
	private Group menu;
	float time=6;
	Initialize initialize;
	public GameGui(){
		
		super(new FitViewport(Gdx.graphics.getWidth()/20,Gdx.graphics.getHeight()/20));
		initialize= new Initialize();
		Button p= new Button(MyTexture.p_button);
		p.setBounds(Gdx.graphics.getWidth()/20-7,Gdx.graphics.getHeight()/20-3, 10, 5);
		
		p.addListener(new ClickListener(){
		@Override
		public void clicked(InputEvent event, float x, float y) {
			if(Data.state==State.PLAY ){
				Data.state= State.PAUSE;
				menu.addAction(Actions.moveTo(0, 0, 1));
			}else {
				Data.state=State.PLAY;
				menu.addAction(Actions.moveTo(Gdx.graphics.getWidth()/20+5, 10, 1));
			}
			super.clicked(event, x, y);
		}	
		});
		addActor(p);
		createMenu();
	}
	private void createMenu(){
		menu= new Group();
		menu.setBounds(Gdx.graphics.getWidth()/20+5, 0, 10, 10);
	
		TextButton textButton= new TextButton("e xi t", MyTexture.textButton);
		textButton.setBounds(20, 10, 5, 5);
		textButton.setPosition(20, 10);
		textButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
			//	Data.gameOver=false;
				Data.score=0;
				Data.playerRemov=false;
			//	 Data.chessQaliti=0;
				 Data.arrayPosCheesArray.clear();
				// Gdx.input.setInputProcessor(null);
				// initialize.createStartMenu();
				Gdx.app.exit();
				super.clicked(event, x, y);
			}
		});
		
		menu.addActor(textButton);
		
		addActor(menu);
	}
	@Override
	public void act() {
	
		super.act();
	}
@Override
public void draw() {
	
	super.draw();
	getBatch().begin();
	//Log.d(LOG, "Data.gameOver="+Data.gameOver);
	/*	if( Data.gameOver==true){Data.state= State.PAUSE;
	menu.addAction(Actions.moveTo(0, 0, 1));
}
	if(Data.gameOver){
		MyTexture.font.draw(getBatch(), "GAME  O V E R",Gdx.graphics.getWidth()/20-40,Gdx.graphics.getHeight()/20-15);	
		time-=Gdx.graphics.getDeltaTime();
		
		if(time<0){
			Data.gameOver=false;
			Data.score=0;
			Data.playerRemov=false;
		//	 Data.chessQaliti=0;
			 Data.arrayPosCheesArray.clear();
			 Gdx.input.setInputProcessor(null);
		//	 initialize.createStartMenu();
			//Gdx.app.exit();
		}
		
	} */
	MyTexture.font.draw(getBatch(), "s co r e:  "+Data.score,Gdx.graphics.getWidth()/20-20,Gdx.graphics.getHeight()/20-6);
	getBatch().end();
}
}
