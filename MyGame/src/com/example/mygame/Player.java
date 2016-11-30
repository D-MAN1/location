package com.example.mygame;


import MyTexture.MyTexture;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Player extends Actor{
	private static final String LOG = "myLogs";
	Body body;
	World world;
	Sprite sprite;
	static float posX;
	float time=0.03f;
	public Player(World world) {
		this.world=world;
		setBounds(Gdx.graphics.getWidth()/40, 5, 5,3);
		BodyDef bDef=new BodyDef();
		bDef.position.set(getX(),getY());
		bDef.type=BodyType.DynamicBody;
		body=world.createBody(bDef);
		
		FixtureDef fDef= new FixtureDef();
		fDef.density=10;
		fDef.restitution=0f;
		fDef.friction=0f;
		
		
		PolygonShape shape= new PolygonShape();
		shape.setAsBox(2.5f, 1.5f);
	//	CircleShape shape=new CircleShape();
	//	shape.setRadius(2);
		fDef.shape=shape;
		body.createFixture(fDef);
		
		body.setUserData("player");
		sprite= new Sprite(MyTexture.atlas.findRegion("launch_stop"));
		sprite.setBounds(0, 0, 5, 3);
		body.setFixedRotation(false);
		body.setActive(true);
		sprite.setColor(Color.BLUE);
		setUserObject(body);
	}

@Override
public void act(float delta) {
/*	if(body.isActive()==false){
		body.getWorld().destroyBody(body);
		this.remove();
	}*/
	//body.setTransform(getX(), getY(), 0);
	sprite.setRotation(MathUtils.radiansToDegrees*body.getAngle());
	if(MathUtils.radiansToDegrees*body.getAngle()>45)body.setAngularDamping(MathUtils.radiansToDegrees*45);
	sprite.setPosition(body.getPosition().x-getWidth()/2,body.getPosition(). y - getHeight()/2);
	posX=body.getPosition().x;
	
	if(body!=null & body.isActive()==false){
		Data.playerRemov=true;
		body.setActive(true);
	//	body.setLinearVelocity(0,3);
		body.setAngularVelocity(MathUtils.radiansToDegrees*0.5f);
		time-=delta;
		if(time<0){
			//Log.d(LOG, "body="+body.isActive());
			body.setActive(false);
			}
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
