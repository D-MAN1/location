package com.example.mygame;

import MyTexture.MyTexture;
import android.R.integer;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Fly extends Actor{
	private static final String LOG = "myLogs";
	private float time1=1;
	private World world;
	private Sprite sprite;
	private Body body;
	//static boolean flyRemove=false;
	public static int flyCount=0;
	static float posX;
	float time=0.03f;
	public Fly(World world) {
		this.world=world;
		flyCount=0;
		int r= MathUtils.random(1);
		if(r==0){ 
			setBounds(-1, 24, 5,3);
			addAction(Actions.moveTo(Gdx.graphics.getWidth()/20, 17, 5));
		}else {setBounds(Gdx.graphics.getWidth()/20-2, 24, 5,3);
		addAction(Actions.moveTo(-5, 17, 5));
		}
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
		Fixture fixture= body.createFixture(fDef);
		body.setUserData("fly");
		setUserObject(body);
		if(r>0){Texture texture= new Texture("fly.png");
		sprite= new Sprite(texture);
		}else 
			{Texture texture= new Texture("flyLeft.PNG");
		sprite= new Sprite(texture);
			}
		sprite.setBounds(0, 0, 5, 3);
		
		Filter f = new Filter();
		f.categoryBits=0x00032;
		f.maskBits= 0x0008 ;  // с какой категорией сталкивается. 0x0001- это категория всех объектов, кому не назначена категория.
		fixture.setFilterData(f);
	}

@Override
public void act(float delta) {
	body.setTransform(getX()+getWidth()/2, getY()+getHeight()/2, 0);
	//Log.d(LOG, "body.isActive()="+body.isActive());
	if(body!=null & body.isActive()==false){
		sprite.setBounds(body.getPosition().x-3,body.getPosition().y-3, 7,4 );
		sprite.setColor(Color.PINK);
		time1-=0.2;
		if(time1<0){
		
	//	Log.d(LOG, "posX="+posX);
		for(int i=0;i<30; i++){
			getStage().addActor(new Part(world,body.getPosition().x,body.getPosition().y));
		}
		body.getWorld().destroyBody(body);
		this.remove();
		//flyRemove=true;
	 }
	
		
	}
	//posX=getX();
	sprite.setPosition(getX(),getY());
	if(getX()<-4 |getX() > Gdx.graphics.getWidth()/20-1){
		body.setActive(false);
		body.getWorld().destroyBody(body);
	    this.remove();
	}
		time-=delta;
		if(body.isActive()&time<0){
			getStage().addActor(new Bomb(world, getX()));
		time=MathUtils.random(2f);
		//Log.d(LOG, "time)="+time);
}
	super.act(delta);
}
@Override
public void draw(Batch batch, float parentAlpha) {
	
	if(sprite!=null)
sprite.draw(batch);
	super.draw(batch, parentAlpha);
}
}
