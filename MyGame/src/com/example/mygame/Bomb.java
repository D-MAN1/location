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
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Bomb extends Actor{

	private Body body;
	private World world;
	private Sprite sprite;
	float time= 2;
	private float time1=0;
	 final static String LOG="myLogs";
	public Bomb(World world,float boundsX) {
		this.world=world;
		setBounds(boundsX, 23, 2,2);
		BodyDef bDef=new BodyDef();
		bDef.position.set(getX(),getY());
		bDef.type=BodyType.DynamicBody;
		body=world.createBody(bDef);
		
		FixtureDef fDef= new FixtureDef();
		fDef.density=14;
		fDef.restitution=0f;
		fDef.friction=0f;
		
		
		//PolygonShape shape= new PolygonShape();
		//shape.setAsBox(0.5f,0.5f);
		CircleShape shape=new CircleShape();
		shape.setRadius(1);
		fDef.shape=shape;
	Fixture fixture=	body.createFixture(fDef);
		
		body.setUserData("bomb");
		setUserObject(body);
		sprite= new Sprite(MyTexture.atlas.findRegion("internal_browser"));
		int random =MathUtils.random(2);
		if(random==0)
		sprite.setColor(Color.RED);
		if(random==1)
			sprite.setColor(Color.GREEN);
		if(random==2)
			sprite.setColor(Color.BLUE);
	sprite.setBounds(getX(),getY(),2,2);
		body.setFixedRotation(true);
		Filter f = new Filter();
		f.categoryBits=0x0004;
		 f.maskBits= 0x0001 |0x0002 |0x0008 ;
		
		fixture.setFilterData(f);
	}

@Override
public void act(float delta) {
	//body.setTransform(getX(), getY(), 0);
	setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);
	sprite.setPosition(body.getPosition().x-getWidth()/2,body.getPosition(). y - getHeight()/2);
	if(body!=null & body.isActive()==false){
		sprite.setBounds(body.getPosition().x-3,body.getPosition().y-3, 10,10 );
		sprite.setColor(Color.BROWN);
		time1-=0.02;
		if(time1<0){
	for(int i=0;i<30; i++){
			getStage().addActor(new Part(world,body.getPosition().x,body.getPosition().y));
		}
		body.getWorld().destroyBody(body);
		this.remove();
	
	 }
	
		
	}
	if(body.getPosition().y<2)
	{
		time-= 0.02f;
		if(time<0){
		body.getWorld().destroyBody(body);
		this.remove();
		}
		
	}
	super.act(delta);
}
@Override
public void draw(Batch batch, float parentAlpha) {
	
	
	//body.setLinearVelocity(0, 40);
	//Log.d(LOG, "body.getPosition().y="+body.getPosition().y);

	if(sprite!=null)
sprite.draw(batch);
	super.draw(batch, parentAlpha);
}
}
