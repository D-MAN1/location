package com.example.mygame;



import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;







public class MyObject extends Actor{
Sprite sprite;
Body body;
 World world;
public MyObject(World world){
	this.world=world;
	
}
public void createBody(Shape shape,BodyDef.BodyType type){
	BodyDef bDef=new BodyDef();
	bDef.position.set(getX(),getY());
	bDef.type=type;
	body=world.createBody(bDef);
	
	FixtureDef fDef= new FixtureDef();
	fDef.density=14;
	fDef.restitution=0.1f;
	fDef.friction=1000f;
	fDef.shape=shape;
	
	body.createFixture(fDef);

}

@Override
	public void act(float arg0) {
		setPosition(body.getPosition().x-getWidth()/2, body.getPosition().y-getHeight()/2);
		super.act(arg0);
	}
@Override
public void draw(Batch batch, float parentAlpha) {
	if(sprite!=null)
//	sprite.draw(batch);
	super.draw(batch, parentAlpha);
}
}
