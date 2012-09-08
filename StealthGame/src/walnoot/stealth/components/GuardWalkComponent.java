package walnoot.stealth.components;

import walnoot.stealth.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class GuardWalkComponent extends Component{
	public static final float TURN_SPEED = 60f, WALK_SPEED = 0.2f; //per second
	
	private boolean turning, turningLeft, walking;
	private float turnDuration, walkDuration;
	
	public GuardWalkComponent(Entity owner){
		super(owner);
	}
	
	public void update(){
		if(turnDuration < 0){
			turnDuration = MathUtils.random(1, 3);
			turning = !turning;
			turningLeft = MathUtils.random() > 0.5f;
		}
		if(walkDuration < 0){
			walkDuration = MathUtils.random(3, 8);
			walking = !walking;
		}
		
		turnDuration -= Gdx.graphics.getDeltaTime();
		walkDuration -= Gdx.graphics.getDeltaTime();
		
		if(turning) owner.setRotation(owner.getRotation() + (TURN_SPEED * Gdx.graphics.getDeltaTime()) * (turningLeft ? 1f : -1f));
		
		if(walking) owner.moveForward(WALK_SPEED * Gdx.graphics.getDeltaTime());
	}
	
	public ComponentIdentifier getIdentifier(){
		return ComponentIdentifier.GUARD_WALK_COMPONENT;
	}
	
	public void setTurning(boolean turning){
		this.turning = turning;
	}
	
	public void setTurningLeft(boolean turningLeft){
		this.turningLeft = turningLeft;
	}
	
	public void setWalking(boolean walking){
		this.walking = walking;
	}
}
