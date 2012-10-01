package walnoot.stealth.components;

import walnoot.stealth.Entity;
import walnoot.stealth.Map;
import walnoot.stealth.StealthGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class ControllerComponent extends Component{
	public static final float TURN_SPEED = 120f, WALK_SPEED = 0.6f;
	
	public ControllerComponent(Entity owner){
		super(owner);
	}
	
	public void update(Map map){
		if(Gdx.input.isKeyPressed(Keys.W)) owner.moveForward(WALK_SPEED * StealthGame.SECONDS_PER_UPDATE);
		
		if(Gdx.input.isKeyPressed(Keys.A)) owner.rotate(TURN_SPEED * StealthGame.SECONDS_PER_UPDATE);
		if(Gdx.input.isKeyPressed(Keys.D)) owner.rotate(-TURN_SPEED * StealthGame.SECONDS_PER_UPDATE);
	}
	
	public Component getCopy(Entity owner){
		return new ControllerComponent(owner);
	}
	
	public ComponentIdentifier getIdentifier(){
		return ComponentIdentifier.CONTROLLER_COMPONENT;
	}
}
