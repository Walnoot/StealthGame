package walnoot.stealth.components;

import walnoot.stealth.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class GuardComponent extends Component{
	public static final float VISION_ANGLE = MathUtils.PI / 4f;
	public static final float TURN_SPEED = 120f, WALK_SPEED = 0.2f; //per second
	
	private final Entity player;
	private boolean foundPlayer;
	
	
	private boolean turning, turningLeft, walking;
	private float turnDuration, walkDuration;
	
	/**
	 * @param player
	 *            - The entity this guard will try to find
	 */
	public GuardComponent(Entity owner, Entity player){
		super(owner);
		this.player = player;
	}
	
	public void update(){
		SpriteComponent spriteComponent = (SpriteComponent) owner.getComponent(ComponentIdentifier.SPRITE_COMPONENT);
		if(spriteComponent != null) spriteComponent.getSprite().setColor(1, 0, 0, 1);
		
		//the angle between the guard and the player
		float angle = MathUtils.atan2(player.getyPos() - owner.getyPos(), player.getxPos() - owner.getxPos()) - MathUtils.PI / 2;
		
		//correct for the guards orientation
		angle -= MathUtils.degreesToRadians * owner.getRotation();
		
		while(angle < MathUtils.PI) angle += 2 * MathUtils.PI;
		while(angle > MathUtils.PI) angle -= 2 * MathUtils.PI;
		
		if(angle > -VISION_ANGLE && angle < VISION_ANGLE){
			foundPlayer = true;
			
			if(angle > 0){
				turningLeft = true;
			}else if(angle < 0){
				turningLeft = false;
			}
		}else foundPlayer = false;
		
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
		
		if(turning || foundPlayer) owner.setRotation(owner.getRotation() + (TURN_SPEED * Gdx.graphics.getDeltaTime()) * (turningLeft ? 1f : -1f));
		
		if(walking || foundPlayer) owner.moveForward(WALK_SPEED * Gdx.graphics.getDeltaTime());
	}
	
	public ComponentIdentifier getIdentifier(){
		return ComponentIdentifier.GUARD_COMPONENT;
	}
}
