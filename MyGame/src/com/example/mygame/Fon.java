package com.example.mygame;

import MyTexture.MyTexture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Fon extends MyObject {

	public Fon(World world) {
		super(world);
		setBounds(0, 0, Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/20);
		float []f={0, Gdx.graphics.getHeight()/20,
				0,0,
				Gdx.graphics.getWidth()/20,0,
				Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/20,
				0, Gdx.graphics.getHeight()/20};
		ChainShape shape= new ChainShape();
		shape.createChain(f);
		createBody(shape, BodyType.KinematicBody);
		sprite=new Sprite();
		sprite.setRegion(MyTexture.atlas2.findRegion("android"));
		sprite.setBounds(0, 0, Gdx.graphics.getWidth()/20+5, Gdx.graphics.getHeight()/20);
		body.setUserData("fon");
		setUserObject(body);
	}
	@Override
	public void act(float arg0) {
		if(body.isActive()==false){
			body.getWorld().destroyBody(body);
			this.remove();
		}
		setPosition(body.getPosition().x, body.getPosition().y);
		//sprite.setPosition(0, 0);
	//super. act(arg0);
	}
@Override
public void draw(Batch batch, float parentAlpha) {
	if(sprite!=null){
	sprite.draw(batch);
	}
	super.draw(batch, parentAlpha);
}
}
