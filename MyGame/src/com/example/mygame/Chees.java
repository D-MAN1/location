package com.example.mygame;

import java.util.Iterator;

import MyTexture.MyTexture;
import android.R.integer;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;


public class Chees extends Actor{

	 private static final String LOG = "myLogs";
	Body body;
	World world;
	Sprite sprite;
	public Chees(World world) {
		this.world=world;
		setBounds(
				MathUtils.random(4, Gdx.graphics.getWidth()/40)*2-4,
				MathUtils.random(4, Gdx.graphics.getHeight()/40)*2-2,
			//	Data.cheesX,
			//	Data.cheesY,
				2,2);
	//	Data.cheesX+=2;
		
		
		if(Data.cheesX>20){Data.cheesY+=2;Data.cheesX=1;}
		BodyDef bDef=new BodyDef();
		bDef.position.set(getX(),getY());
		bDef.type=BodyType.KinematicBody;
		body=world.createBody(bDef);
		
		FixtureDef fDef= new FixtureDef();
		fDef.density=14;
		fDef.restitution=0f;
		fDef.friction=0.9f;
		
		
		PolygonShape shape= new PolygonShape();
		shape.setAsBox(1, 1f);
	//	CircleShape shape=new CircleShape();	shape.setRadius(0.5f);
		fDef.shape=shape;
		Fixture fixture=body.createFixture(fDef);
		
		body.setUserData("chees");
		setUserObject(body);
		sprite= new Sprite(MyTexture.atlas2.findRegion("cheese"));
		sprite.setBounds(0, 0, 2,2);
		body.setFixedRotation(false);
		body.setGravityScale(1f);
		Filter f = new Filter();
		f.categoryBits=0x00016 ;
		f.maskBits=0x0002;
		//f.groupIndex = 1;
		//f.groupIndex = -2;
		fixture.setFilterData(f);
		
		Data.arrayPosCheesArray.add(this);
		
		
	}

@Override
public void act(float delta) {

	//body.setTransform(getX(), getY(), 0);
	sprite.setPosition(body.getPosition().x-getWidth()/2,body.getPosition(). y - getHeight()/2);
	if(body!=null & body.isActive()==false){
		
		
		// Log.d(LOG,"new Vector2(getX(),getY()"+v);
		Iterator<Actor> iter= Data.arrayPosCheesArray.iterator();
		while (iter.hasNext()) {
			Actor actor=iter.next();
		//	Log.d(LOG,"vector2"+vector2);
			Body body=(Body)actor.getUserObject();
			if(body.isActive()==false){
				 iter.remove();
				
			}
			
		}
	//	Data.chessQaliti=Data.arrayPosCheesArray.size;
		body.getWorld().destroyBody(body);
		this.remove();
	}
	
	super.act(delta);
}
@Override
public void draw(Batch batch, float parentAlpha) {
	setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);
	if(sprite!=null)
sprite.draw(batch);
	super.draw(batch, parentAlpha);
}
}
