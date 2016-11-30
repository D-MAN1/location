package com.example.mygame;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class MyContact implements ContactListener {
	World world;
	Stage stage;
	int flyCount=0;
//	static Contact contact;
	 String LOG="myLogs";
	public MyContact(World world, Stage stage) {
		this.world=world;
		this.stage=stage;
	}
	@Override
	public void beginContact(Contact contact) {
		//this.contact=contact;
	
		//Fixture fixA=contact.getFixtureA();
		//Fixture fixB= contact.getFixtureB();
		
			
		//	MyScreen.ratName=i;
		//	MyScreen.deleteRat=true;
			//Rat.remov=true;
				//Gdx.app.exit();
		//	}
		//	}
	//	if((fixA.getBody().getUserData().equals("ball")&&fixB.getBody().getUserData().equals("player"))
		//		|(fixB.getBody().getUserData().equals("ball")&& fixA.getBody().getUserData().equals("player"))){
				//	Data.score++;
			//	}
	
	}

	@Override
	public void endContact(Contact arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse arg1) {
		Body bodyA=null;
		Body bodyB=null;
		Fixture fixA=contact.getFixtureA();
		Fixture fixB= contact.getFixtureB();
		
		
		if(fixA.getBody().getUserData()!=null&& fixB.getBody().getUserData()!=null){
			
			if((fixA.getBody().getUserData().equals("bullet")&& fixB.getBody().getUserData().equals("chees"))
				|( fixB.getBody().getUserData().equals("bullet")&& fixA.getBody().getUserData().equals("chees"))){
			//	fixA.setSensor(true);
           //		fixB.setSensor(true);
			}
			
		if((fixA.getBody().getUserData().equals("bomb")&& fixB.getBody().getUserData().equals("chees"))
			|( fixB.getBody().getUserData().equals("bomb")&& fixA.getBody().getUserData().equals("chees"))){
			//	fixA.setSensor(true);
			//	fixB.setSensor(true);
				}	
		
		if((fixA.getBody().getUserData().equals("bomb")&& fixB.getBody().getUserData().equals("rat"))
				|( fixB.getBody().getUserData().equals("bomb")&& fixA.getBody().getUserData().equals("rat"))){
			
			//	if(fixA.getBody().getUserData().equals("bomb")) {fixA.getBody().setActive(false); }
			//	if(fixB.getBody().getUserData().equals("bomb")) {fixB.getBody().setActive(false); }
				if(fixA.getBody().getUserData().equals("rat")) { fixA.getBody().setActive(false); }
				if(fixB.getBody().getUserData().equals("rat")) { fixB.getBody().setActive(false); }
			}
		
		if((fixA.getBody().getUserData().equals("bullet")&& fixB.getBody().getUserData().equals("bomb"))
				|( fixB.getBody().getUserData().equals("bullet")&& fixA.getBody().getUserData().equals("bomb"))){
				Data.score+=5;
//				fixA.setSensor(false);
//				fixB.setSensor(false);
				if(fixA.getBody().getUserData().equals("bullet")) {fixA.getBody().setActive(false); }
				if(fixB.getBody().getUserData().equals("bullet")) {fixB.getBody().setActive(false); }
				if(fixA.getBody().getUserData().equals("bomb")) { fixA.getBody().setActive(false); }
				if(fixB.getBody().getUserData().equals("bomb")) { fixB.getBody().setActive(false); }
				Log.d(LOG, "getBody().isActive()="+fixA.getBody().isActive()+fixB.getBody().isActive());
			}
		
		if((fixA.getBody().getUserData().equals("player")&& fixB.getBody().getUserData().equals("bomb"))
				|( fixB.getBody().getUserData().equals("player")&& fixA.getBody().getUserData().equals("bomb"))){
				//	if(fixA.getBody().getUserData().equals("player")){fixA.getBody().setActive(false);}
				//	if(fixB.getBody().getUserData().equals("player")){fixB.getBody().setActive(false);}
					}
		
		if((fixA.getBody().getUserData().equals("bullet")&& fixB.getBody().getUserData().equals("rat"))
			|( fixB.getBody().getUserData().equals("bullet")&& fixA.getBody().getUserData().equals("rat"))){
			Data.score++;
			if(fixA.getBody().getUserData().equals("bullet")) {fixA.getBody().setActive(false); }
			if(fixB.getBody().getUserData().equals("bullet")) {fixB.getBody().setActive(false); }
			if(fixA.getBody().getUserData().equals("rat")) { fixA.getBody().setActive(false); }
			if(fixB.getBody().getUserData().equals("rat")) { fixB.getBody().setActive(false); }
		}
		
		
		if((fixA.getBody().getUserData().equals("rat")&&fixB.getBody().getUserData().equals("chees"))
		|(fixB.getBody().getUserData().equals("rat")&& fixA.getBody().getUserData().equals("chees"))){
		Data.score--;
			if(fixA.getBody().getUserData().equals("chees")){fixA.getBody().setActive(false);}
			if(fixB.getBody().getUserData().equals("chees")){fixB.getBody().setActive(false);}
		}
		
		
		if((fixA.getBody().getUserData().equals("player")&& fixB.getBody().getUserData().equals("rat"))
		|( fixB.getBody().getUserData().equals("player")&& fixA.getBody().getUserData().equals("rat"))){
		//	if(fixA.getBody().getUserData().equals("player")){fixA.getBody().setActive(false);}
		//	if(fixB.getBody().getUserData().equals("player")){fixB.getBody().setActive(false);}
			}
		
		if((fixA.getBody().getUserData().equals("bullet")&& fixB.getBody().getUserData().equals("fly"))
				|( fixB.getBody().getUserData().equals("bullet")&& fixA.getBody().getUserData().equals("fly"))){
				Data.score++;
				if(fixA.getBody().getUserData().equals("bullet")) {fixA.getBody().setActive(false); }
				if(fixB.getBody().getUserData().equals("bullet")) {fixB.getBody().setActive(false); }
				
				if(fixA.getBody().getUserData().equals("fly")) { Fly.flyCount++;
			//	Log.d(LOG, "flyCount="+Fly.flyCount);
			    if(Fly.flyCount>Data.flyCount)	fixA.getBody().setActive(false); }
				if(fixB.getBody().getUserData().equals("fly")) { Fly.flyCount++;
			//	Log.d(LOG, "2flyCount="+Fly.flyCount);
				if(Fly.flyCount>Data.flyCount)	fixA.getBody().setActive(false); }
			}
			
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold arg1) {
		
	
	}

	
		
	}


