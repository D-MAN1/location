package com.example.mygame;

import MyTexture.MyTexture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Exit extends Actor{
	private static final String LOG = "myLogs";
	
	World world;
	Sprite sprite;
	Initialize initialize;
	public  Exit(World world,Initialize initialize) {
		this.world=world;
		this.initialize=initialize;
		setBounds(0, 0, 30, 20);
	
		
		
		Texture texture = new Texture(Gdx.files.external( "/screenshot.png"));
		 sprite = new Sprite(texture);
		//sprite.setBounds(-20, 0, 30, 20);
	
	}
float x= -30;
@Override
public void act(float delta) {
	//setBounds(-20, 0, 3+Gdx.input.getAzimuth()*0.1f, 20);
	sprite.setBounds(-10, 0, 30+Gdx.input.getAccelerometerX()*0.5f, 20+ Gdx.input.getAccelerometerY()* 0.5f);
	setPosition(x, 0);
	sprite.setPosition(getX(),getY());
	x+=0.2f;
	if(x>Gdx.graphics.getWidth()/20) initialize.createStartMenu();
	super.act(delta);
}
@Override
public void draw(Batch batch, float parentAlpha) {

	if(sprite!=null)
sprite.draw(batch);
	super.draw(batch, parentAlpha);
}
}
