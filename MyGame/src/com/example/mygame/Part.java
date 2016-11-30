package com.example.mygame;

import MyTexture.MyTexture;
import android.util.Log;

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

public class Part extends Actor{

	Body body;
	World world;
	Sprite sprite;
	float time=0.05f;
	 final static String LOG="myLogs";
	public Part(World world,float x,float y) {
		this.world=world;
		setBounds(x+MathUtils.random(-2,2), y+MathUtils.random(0,2), 0.5f,0.5f);
		BodyDef bDef=new BodyDef();
		bDef.position.set(getX(),getY());
		bDef.type=BodyType.DynamicBody;
		body=world.createBody(bDef);
		
		FixtureDef fDef= new FixtureDef();
		fDef.density=14;
		fDef.restitution=0f;
		fDef.friction=0f;
		
		
	//	PolygonShape shape= new PolygonShape();
	//	shape.setAsBox(0.5f,0.5f);
	CircleShape shape=new CircleShape();
		shape.setRadius(0.5f);
		fDef.shape=shape;
		body.createFixture(fDef);
		
		body.setUserData("part");
		setUserObject(body);
		sprite= new Sprite(MyTexture.atlas.findRegion("launch_stop"));
	sprite.setBounds(0, 0, 0.8f,0.8f);
		body.setFixedRotation(true);
		
	}

@Override
public void act(float delta) {
	//body.setTransform(getX(), getY(), 0);
	sprite.setRotation(MathUtils.radiansToDegrees*body.getAngle());
	sprite.setPosition(body.getPosition().x-getWidth()/2,body.getPosition(). y - getHeight()/2);
	time-=delta;
	if(time<0){
		//Log.d(LOG, "time="+Data.playerRemov);
		body.getWorld().destroyBody(body);
		this.remove();
		time=0.05f;
	}
	
	super.act(delta);
}
@Override
public void draw(Batch batch, float parentAlpha) {
	
	setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);
 body.setLinearVelocity(MathUtils.random(-5,5), MathUtils.random(-5,5));
//	Log.d(LOG, "actor="+Player.body.getPosition().x);

	if(sprite!=null)
sprite.draw(batch);
	super.draw(batch, parentAlpha);
}
}

