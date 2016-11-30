package com.example.mygame;


import MyTexture.MyTexture;
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

public class Rat extends Actor{
	
	static boolean remov=false;
	private float time=4;
	private float time1=1;

    public Body body;
    private World world;
    private Sprite sprite;
    private String LOG="myLogs";
    private BodyDef bDef;
	
	public Rat(World world) {
		this.world=world;
		Data.i=Data.i+1;
		int random =MathUtils.random(2);
		if(random==0) setBounds(2, 20, 3,3);
		if(random==1) setBounds(Gdx.graphics.getWidth()/40-1, 20, 3,3);
		if(random==2) setBounds(Gdx.graphics.getWidth()/20-2, 20, 3,3);
		 bDef=new BodyDef();
		bDef.position.set(getX(),getY());
		bDef.type=BodyType.DynamicBody;
		body=world.createBody(bDef);
	
		//setName(body.getUserData().toString());
	//	userData=new Array<String>();
		//userData.add("rat"+Data.i);
	//	
		FixtureDef fDef= new FixtureDef();
		fDef.density=1f;
		fDef.restitution=0.3f;
		fDef.friction=0.9f;
		
		
	//	PolygonShape shape= new PolygonShape();
		 //shape.setAsBox(2, 1.5f);
		CircleShape shape=new CircleShape();
		shape.setRadius(1.5f);
		fDef.shape=shape;
		Fixture fixture = body.createFixture(fDef);
	//	Log.d(LOG, "getName="+getName());
	//	body.setUserData(getName());
		sprite= new Sprite(MyTexture.atlas2.findRegion("rat"));
		sprite.setBounds(0, 0, 4, 4);
		body.setFixedRotation(false);
		body.setUserData("rat");
		setUserObject(body);
	//	body.setLinearVelocity(MathUtils.random(-15,-5),0);
		//body.setLinearDamping(5);
		body.setGravityScale(0f);
		Filter f = new Filter();
		f.categoryBits=0x0002;
		f.maskBits= 0x0001 | 0x0004 |0x0008 | 0x00016;  // с какой категорией сталкивается. 0x0001- это категория всех объектов, кому не назначена категория.
		fixture.setFilterData(f);
		
	}

@Override
public void act(float delta) {
	//body.setTransform(getX(), getY(), 0);
	
	sprite.setPosition(body.getPosition().x-getWidth()/2,body.getPosition(). y - getHeight()/2);
	time-=delta;
	//Log.d(LOG, "time="+time);
	if(time<0){
		body.applyForceToCenter(MathUtils.random(-5000,5000), MathUtils.random(-3000,3000), true);
	//	body.setLinearVelocity(MathUtils.random(-10,10),MathUtils.random(-5,5));
		time=1;
	}
	
/*	if(body.getPosition().x<2){
		//bDef.position.set(10,10);
		// body.setLinearVelocity(7, 0);
		body.applyForceToCenter(500, 0, true);
	//	Log.d(LOG, "body.getPosition().x="+body.getPosition().x);
	}
	if(body.getPosition().x>Gdx.graphics.getWidth()/20-2){
		// body.setLinearVelocity(-7, 0);
		body.applyForceToCenter(-500, 0, true);
	}
	if(body.getPosition().y<7){
		// body.setLinearVelocity(0, 7);
		body.applyForceToCenter(0, 500, true);
	}
	if(body.getPosition().y>Gdx.graphics.getHeight()/20-2){
     //  body.setLinearVelocity(0,-7);
		body.applyForceToCenter(0, -500, true);
		
	}
	*/
	

	
	if(body!=null & body.isActive()==false){
		sprite.setBounds(body.getPosition().x-3,body.getPosition().y-3, 6,6 );
		sprite.setColor(Color.RED);
		time1-=0.2;
		if(time1<0){
		remov=true;
	//	Log.d(LOG, "posX="+posX);
		for(int i=0;i<30; i++){
			getStage().addActor(new Part(world,body.getPosition().x,body.getPosition().y));
		}
		body.getWorld().destroyBody(body);
		this.remove();
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
