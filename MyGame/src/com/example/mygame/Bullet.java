package com.example.mygame;

import MyTexture.MyTexture;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet extends Actor{
	Body body;
	World world;
	Sprite sprite;
	 final static String LOG="myLogs";
	public Bullet(World world) {
		this.world=world;
		setBounds(Player.posX, 5, 0.5f,0.5f);
		BodyDef bDef=new BodyDef();
		bDef.position.set(getX(),getY());
		bDef.type=BodyType.DynamicBody;
		body=world.createBody(bDef);
		
		FixtureDef fDef= new FixtureDef();
		fDef.density=14;
		fDef.restitution=0f;
		fDef.friction=0f;
		
		
		PolygonShape shape= new PolygonShape();
		shape.setAsBox(0.5f,0.5f);
	//	CircleShape shape=new CircleShape();
	//	shape.setRadius(2);
		fDef.shape=shape;
	   Fixture fixture=	body.createFixture(fDef);
		
		body.setUserData("bullet");
		setUserObject(body);
		sprite= new Sprite(MyTexture.atlas.findRegion("internal_browser"));
	    sprite.setBounds(0, 0, 0.5f,1f);
		body.setFixedRotation(false);
		Filter f = new Filter();
		f.categoryBits=0x0008;
		f.maskBits=0x0001 |0x0002 |0x0004 | 0x00032 ;
		//f.groupIndex=0;
		fixture.setFilterData(f);
	}

@Override
public void act(float delta) {
	//body.setTransform(getX(), getY(), 0);
	sprite.setPosition(body.getPosition().x-getWidth()/2,body.getPosition(). y - getHeight()/2);
	if(body.isActive()==false){
		body.getWorld().destroyBody(body);
		this.remove();
	}
	
	//super.act(delta);
}
@Override
public void draw(Batch batch, float parentAlpha) {
	
	setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);
	body.setLinearVelocity(0, 40);
//	Log.d(LOG, "actor="+Player.body.getPosition().x);
	if(body.getPosition().y> Gdx.graphics.getHeight()/20-2)
	{
		body.getWorld().destroyBody(body);
		this.remove();
		
		
	}
	if(sprite!=null)
sprite.draw(batch);
	super.draw(batch, parentAlpha);
}
}
