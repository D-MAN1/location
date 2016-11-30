package com.example.mygame;

import MyTexture.MyTexture;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class StartMenu implements Screen{

	private static final String LOG = "myLogs";
	private World world;
	private Stage stage;
//	private Box2DDebugRenderer render;
	Initialize initialize;
	private TextButton start;
	private TextButton exit;
	public StartMenu(Initialize initialize) {
		this.initialize=initialize;
		 world=new World(new Vector2(0,-10), true);
		 stage= new Stage(new FitViewport(Gdx.graphics.getWidth()/20,Gdx.graphics.getHeight()/20));
	}

	@Override
	public void show() {
		//    render= new Box2DDebugRenderer();
		   start= new TextButton("s t a r t",MyTexture.textButton);
			start.setBounds(1,Gdx.graphics.getHeight()/20-6, 19, 6);
			start.addListener(new ClickListener(){
		 @Override
		public void clicked(InputEvent event, float x, float y) {
			dispose();
		    initialize.createMyScreen();
			super.clicked(event, x, y);
			}	
			});
		stage.addActor(start);
		
		  exit= new TextButton("e xi t",MyTexture.textButton);
		  exit.setBounds(1,2, 19, 6);
		  exit.addListener(new ClickListener(){
		 @Override
		public void clicked(InputEvent event, float x, float y) {
			dispose();
		    Gdx.app.exit();
			super.clicked(event, x, y);
			}	
			});
		stage.addActor(exit);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		//Log.d(LOG, "render"+delta);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 1, 1);
		  world.step(delta, 4, 4);
	     stage.act(delta);
		  stage.draw();
		//  render.render(world, stage.getCamera().combined);
		
		  stage.getBatch().begin();
		  if(Data.gameOver){
				MyTexture.font.draw(stage.getBatch(), "GAME  O V E R",Gdx.graphics.getWidth()/20-40,Gdx.graphics.getHeight()/20-15);	
			
				
			} 
			MyTexture.font.draw(stage.getBatch(), "s co r e:  "+Data.score,Gdx.graphics.getWidth()/20-20,Gdx.graphics.getHeight()/20-6);
			stage.getBatch().end();
		}
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
	//	render.dispose();
		
		Data.gameOver=false;
		start.remove();
		exit.remove();
		
	}

}
