package walnoot.stealth.components;

import walnoot.stealth.Entity;
import walnoot.stealth.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class ControllerComponent extends Component{
	public static final float TURN_SPEED = 120f, WALK_SPEED = 0.6f;
	
	public ControllerComponent(Entity owner){
		super(owner);
	}
	
	public void update(Map map){
		if(Gdx.input.isKeyPressed(Keys.W)) owner.moveForward(WALK_SPEED * Gdx.graphics.getDeltaTime());
		
		if(Gdx.input.isKeyPressed(Keys.A)) owner.rotate(TURN_SPEED * Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Keys.D)) owner.rotate(-TURN_SPEED * Gdx.graphics.getDeltaTime());
	}
	
	public Component getCopy(Entity owner){
		return new ControllerComponent(owner);
	}
	
	public ComponentIdentifier getIdentifier(){
		return ComponentIdentifier.CONTROLLER_COMPONENT;
	}
}
