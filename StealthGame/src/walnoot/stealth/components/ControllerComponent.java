package walnoot.stealth.components;

import walnoot.stealth.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class ControllerComponent extends Component{
	public static final float TURN_SPEED = 60f, WALK_SPEED = 0.2f;
	
	public ControllerComponent(Entity owner){
		super(owner);
	}
	
	public void update(){
		if(Gdx.input.isKeyPressed(Keys.W)) owner.moveForward(WALK_SPEED * Gdx.graphics.getDeltaTime());
		
		if(Gdx.input.isKeyPressed(Keys.A)) owner.rotate(TURN_SPEED * Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Keys.D)) owner.rotate(-TURN_SPEED * Gdx.graphics.getDeltaTime());
	}
	
	public ComponentIdentifier getIdentifier(){
		return ComponentIdentifier.CONTROLLER_COMPONENT;
	}
}
