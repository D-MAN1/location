package com.example.mygame;
import MyTexture.MyTexture;
import android.R.integer;
import android.R.string;
import android.util.Log;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;





public class Ball extends Actor{
 String LOG="myLogs";
 World world;
 Body body;
 Sprite sprite;
 Color color;
	public Ball(World world, float i) {
		this.world= world;
		Data.i=Data.i+1;

	setBounds(
			//	MathUtils.random(25)
			i, 4, 2, 2);
		CircleShape shape=new CircleShape();
		shape.setRadius(1);
		BodyDef bDef=new BodyDef();
		bDef.position.set(getX(),getY());
		bDef.type=BodyType.DynamicBody;
		body=world.createBody(bDef);
		
		FixtureDef fDef= new FixtureDef();
		fDef.density=1;
		
		fDef.friction=1000f;
		fDef.shape=shape;
		
		body.createFixture(fDef);
		int random =MathUtils.random(2);
		
		sprite=new Sprite();
		sprite.setRegion(MyTexture.atlas.findRegion("internal_browser"));
		sprite.setBounds(0, 0, 2, 2);
		
		if(random==0)
		sprite.setColor(Color.RED);
		if(random==1)
			sprite.setColor(Color.GREEN);
		if(random==2)
			sprite.setColor(Color.BLUE);
		sprite.setOriginCenter();
		setUserObject(body);
		
	}
	   @Override
       public Ball hit (float x, float y, boolean touchable) {
           return x >= 0 && x < getX() && y >= 0 && y < getY() ? this : null;
       }

@Override
public void act(float arg0) {
	if(body.isActive()==false){
		body.getWorld().destroyBody(body);
		this.remove();
	}
	setPosition(body.getPosition().x-getWidth()/2, body.getPosition().y-getHeight()/2);
	sprite.setRotation(MathUtils.radiansToDegrees*body.getAngle());
	sprite.setPosition(body.getPosition().x-getWidth()/2,body.getPosition(). y-getHeight()/2);

//Log.d(LOG, "body.getLinearVelocity().y="+body.getLinearVelocity().y);
	if(body.getPosition().y<1&& body.getLinearVelocity().y> 0 && body.getLinearVelocity().y< 5)
	{
	//	body.getWorld().destroyBody(body);
	//	this.remove();
		
		
	}
	super.act(arg0);
}
@Override
public void draw(Batch batch, float parentAlpha) {
	if(sprite!=null){
		sprite.draw(batch);
		if(body.getPosition().y>2)body.setLinearVelocity(0, -10);
	}
	super.draw(batch, parentAlpha);
}
}
