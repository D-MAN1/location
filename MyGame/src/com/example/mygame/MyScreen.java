package com.example.mygame;



import java.util.Iterator;












import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.FitViewport;



public class MyScreen implements Screen {
	
private World world;
private Stage stage;
private Box2DDebugRenderer render;
private Fon fon;
private Player player;
private Ball ball1,ball2;
private Rat rat;
private Chees chees;
private Fly fly;
//private Vector2 p;
private GameGui gui;
// RayHandler handler;


private float spriteX=1;
private Joint joint1,joint2,joint3;
private float timeRat=2;
private float time1=2f;//������� ��������� ����
private float timeFly=4f;
private boolean flag=true;
private boolean flag1=true;
private boolean flag2=true;
private boolean flag3=true;
private boolean flagCreateFly=false;
 final static String LOG="myLogs";
 private Initialize initialize;
private EventListener eventListener;
private boolean exitFlag=true;

	public MyScreen(Initialize initialize) {
		this.initialize=initialize;
		 world=new World(new Vector2(0,-10), true);
		 stage= new Stage(new FitViewport(Gdx.graphics.getWidth()/20,Gdx.graphics.getHeight()/20));
}


	@Override
	public void show() {
		//Log.d(LOG, "Data.gameOver=");
		Data.arrayPosCheesArray.size=0;
   
   render= new Box2DDebugRenderer();
   fon= new Fon(world);
   player= new Player(world);
   ball1= new Ball(world,5);
   ball2= new Ball(world,10);

   gui= new GameGui();
   stage.addActor(fon);
   stage.addActor(player);
   stage.addActor(ball1);
   stage.addActor(ball2);
 
   createChess(1); 
 
stage.setDebugAll(true);
    RevoluteJointDef jointDef1 = new RevoluteJointDef();
    jointDef1.initialize(player.body,ball1.body,new Vector2(0,0));
    jointDef1.bodyA =ball1.body;
    jointDef1.bodyB =player.body;
    jointDef1.localAnchorA.set(0, 0);
    jointDef1.localAnchorB.set(-2, -1);
    
    joint1 = world.createJoint(jointDef1);
    
    RevoluteJointDef jointDef2 = new RevoluteJointDef();
    jointDef2.bodyA =ball2.body;
    jointDef2.bodyB =player.body;
    jointDef2.localAnchorA.set(0, 0);
    jointDef2.localAnchorB.set(2, -1);
    joint2 = world.createJoint(jointDef2);
    

 eventListener=(new InputListener(){
    	float x1=0;
    	@Override
    	public boolean touchDown(InputEvent event, float x, float y,
    			int pointer, int button) {
    
					 Timer.schedule(new Task() {
						int i=0;
					 int j= countBullet();
						
						@Override
						public void run() {
							i++;
							if(i>j)this.cancel();
							stage.addActor(new Bullet(world));
						//	Log.d(LOG, "i="+i);
						}
            	}, 0,0.01f);
		
    		
    		 if(x<player.getX()){player.body.setLinearVelocity(-20, 0);}
    		 if(x>player.getX()){player.body.setLinearVelocity(20, 0); } 
    		return true;
    	}
        @Override
        public void touchDragged(InputEvent event, float x, float y, int pointer) {
        	//if(Data.score>20)	stage.addActor(new Bullet(world));
       /* 	if(x<x1){
        	 x1=x;
        		player.body.setLinearVelocity(-20, 0);
        
        		}
        		if(x>x1){
           x1=x;
          	player.body.setLinearVelocity(20, 0);	
           }
   */
     
        	super.touchDragged(event, x, y, pointer);
        }
        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer,
        		int button) {
        	
        	//if(y>Gdx.graphics.getWidth()/40){
        	ball1.body.setAngularVelocity(0);
        	ball2.body.setAngularVelocity(0);
        //	player.body.setLinearVelocity(0, 0);
        	
        //	}
 /*      	if(joint!=null){
        		world.destroyJoint(joint);
        		joint=null;
        	   // Log.d(LOG, "3joint="+joint.getType());
        	    
            	}
            	*/
        //	MyContact.contact.setFriction(1000);
        	ball1.body.setAngularVelocity(0);
       // 	Data.gameOver=true;
        	super.touchUp(event, x, y, pointer, button);
        }
    
    });
  stage.addListener(eventListener);
    InputMultiplexer iM= new InputMultiplexer();
    iM.addProcessor(gui);
    iM.addProcessor(stage);
    Gdx.input.setInputProcessor(iM);
	}
	
	@Override
	public void render(float delta) {
	//	Log.d(LOG, "flyRemove="+Fly.flyRemove);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
Gdx.gl.glClearColor(0, 0, 0, 1);

if (Data.state== State.PLAY){
     world.step(delta, 4, 4);
     stage.act(delta);
     
   }
  stage.draw();
  gui.act(delta);
  gui.draw();
 // Log.d(LOG, "player.getX()="+player.getX());
  fon.sprite.setPosition(-player.getX()/10, 0);

  if(Data.score>50 & flag==true){createChess(5); flag=false;}
  if(Data.score>100 & flag1==true){createChess(10); flag1=false;}
  if(Data.score>150 & flag2==true){createChess(15); flag2=false;}
  if(Data.score>20& flag3==true){createChess(20); flag3=false; flagCreateFly=true;}
  if(Data.score>300){flagCreateFly=false;
  }
 if(flagCreateFly==true)  createFly();
  createRat(delta);
 
 
	if(Data.playerRemov==true){ 
	stage.addActor(new Part(world,Player.posX,2));
	Data.playerRemov=false;
	Data.gameOver=true; 
	//Log.d(LOG, "Data.gameOver="+Data.gameOver);
	}

	if(Data.gameOver & exitFlag){
	createExit();
	dispose();
	
		}
	render.render(world, stage.getCamera().combined);
       world.setContactListener(new MyContact(world,stage));
 }
	private void createExit() {
		ScreenshotFactory.saveScreenshot();
		stage.addActor(new Exit(world,initialize));
		exitFlag=false;
	}


	private void createChess(int j) {
		 for(int i=0;i<j;i++){
		
			  chees= new Chees(world);
		   	  stage.addActor(chees);
		      createRat(Gdx.graphics.getDeltaTime());
		    }
	}

	
 private void createRat(float delta) {
	timeRat-=delta;
	if(timeRat<0& Data.state== State.PLAY & Data.gameOver==false){
		if(Data.arrayPosCheesArray.size<1){ Data.cheesX=1; Data.cheesY=7; Data.gameOver=true;
		
		
		}
		
		 rat=new Rat(world);
		 time1-=0.001f;
		if(Data.arrayPosCheesArray.size>0) jointRat(rat);
        	stage.addActor(rat);
		    timeRat=time1;
	      }
	}
 public void createFly(){
	 timeFly-=Gdx.graphics.getDeltaTime();
		//Log.d(LOG, "timeFly="+timeFly);
	 if(timeFly<0){
	 fly=new Fly(world);
	 stage.addActor(fly);
	 timeFly = 6;
 }
	/* if(Fly.flyRemove){
		 for(int i=0;i<10; i++){
			stage.addActor(new Part(world,fly.getX(),fly.getY()));
			Fly.flyRemove=false;
		 }
		}
	 */
 }

 public void jointRat(Rat rat){
	 int i=MathUtils.random(0,3);
	
	Body anchorBody;
	if(i==1){
		anchorBody=player.body;
	} else{
		anchorBody=(Body)Data.arrayPosCheesArray.get(MathUtils.random(Data.arrayPosCheesArray.size-1)).getUserObject();
		}
	
		 DistanceJointDef jointDef3 = new DistanceJointDef();
		    
		    jointDef3.initialize(rat.body,anchorBody,new Vector2(0,0),new Vector2(0,0));
		    jointDef3.collideConnected=true;
		    jointDef3.bodyA =rat.body;
		    jointDef3.bodyB =anchorBody;
		    jointDef3.localAnchorA.set(0, 1);
		    jointDef3.localAnchorB.set(1,0);
		    jointDef3.frequencyHz=0.15f;//���� ���������� � �������
		    jointDef3.dampingRatio=0f; //��������� �����������
		    joint3 = world.createJoint(jointDef3);
		//    Log.d(LOG,"i="+i);
		    

}


	private int  countBullet() {
		int j=2;
		if(Data.score>50)j=5;
		if(Data.score>100)j=10;
		if(Data.score>150)j=15;
		if(Data.score>200)j=20;
		return j;
	}
	
	@Override
	public void dispose() {
	//	Data.score=30;
		flagCreateFly =false;
			stage.setDebugAll(false);
			 
			stage.removeListener(eventListener);
			Iterator<Actor> iter= stage.getActors().iterator();
			while (iter.hasNext()) {
			Actor actor=(Actor)iter.next();
			Body body;
			if(actor!=null & (Body)actor.getUserObject()!=null){
				body =(Body)actor.getUserObject();
				body.setActive(false);
				world.destroyBody(body);
				
			iter.remove();
			 
			 }
			
			}
			 Gdx.input.setInputProcessor(null);
			 
			
	}
	@Override
	public void hide() {
		
	}
	@Override
	public void pause() {}
    @Override
	public void resize(int arg0, int arg1) {}
    @Override
	public void resume() {}
    
    

}
