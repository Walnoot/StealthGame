package walnoot.stealth.components;

import walnoot.stealth.Entity;
import walnoot.stealth.Map;
import walnoot.stealth.StealthGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class GuardComponent extends Component{
	public static final float VISION_ANGLE = MathUtils.PI / 4f;
	public static final float TURN_SPEED = 120f, WALK_SPEED = 0.2f; //per second
	private static final float ALERT_POPUP_TIME = 1f;
	
	private final Entity player;
	private boolean foundPlayer;
	
	
	private boolean turningLeft, turningRight, walking;
	private float turnDuration, walkDuration;
	private float alertPopupTimer;	
	
	/**
	 * @param player
	 *            - The entity this guard will try to find
	 */
	public GuardComponent(Entity owner, Entity player){
		super(owner);
		this.player = player;
	}
	
	public void update(Map map){
		SpriteComponent spriteComponent = (SpriteComponent) owner.getComponent(ComponentIdentifier.SPRITE_COMPONENT);
		if(spriteComponent != null) spriteComponent.getSprite().setColor(0.75f, 0.0625f, 0.0625f, 1);
		
		if(alertPopupTimer > 0) alertPopupTimer -= Gdx.graphics.getDeltaTime();
		
		//the angle between the guard and the player
		float angle = MathUtils.atan2(player.getyPos() - owner.getyPos(), player.getxPos() - owner.getxPos()) - MathUtils.PI / 2;
		
		//correct for the guards orientation
		angle -= MathUtils.degreesToRadians * owner.getRotation();
		
		//ensure angle is between -PI and PI
		while(angle < MathUtils.PI) angle += 2 * MathUtils.PI;
		while(angle > MathUtils.PI) angle -= 2 * MathUtils.PI;
		
		//if the player is in the cone of vision
		if(angle > -VISION_ANGLE && angle < VISION_ANGLE){
			if(!foundPlayer) alertPopupTimer = ALERT_POPUP_TIME;
			foundPlayer = true;
			walking = true;
			
			if(angle > 0.05f){
				turningLeft = true;
				turningRight = false;
			}else if(angle < -0.05f){
				turningRight = true;
				turningLeft = false;
			}else{
				turningLeft = false;
				turningRight = false;
			}
		}else foundPlayer = false;
		
		/*
		 * Random walk stuff. Sets a timer telling how long the guard will walk/turn or stop walking/turning
		 */
		if(!foundPlayer){
			if(turnDuration < 0){
				turnDuration = MathUtils.random(1, 3);
				
				if(turningLeft || turningRight){
					turningLeft = false;
					turningRight = false;
				}else{
					if(MathUtils.random() > 0.5f) turningLeft = true;
					else turningRight = true;
				}
			}
			if(walkDuration < 0){
				walkDuration = MathUtils.random(3, 8);
				walking = !walking;
			}
			
			turnDuration -= Gdx.graphics.getDeltaTime();
			walkDuration -= Gdx.graphics.getDeltaTime();
		}
		
		if(turningLeft) owner.setRotation(owner.getRotation() + (TURN_SPEED * Gdx.graphics.getDeltaTime()));
		if(turningRight) owner.setRotation(owner.getRotation() + (TURN_SPEED * -Gdx.graphics.getDeltaTime()));
		
		if(walking) owner.moveForward(WALK_SPEED * Gdx.graphics.getDeltaTime());
	}
	
	public void render(SpriteBatch batch){
		if(alertPopupTimer > 0){
			StealthGame.FONT.setScale(StealthGame.FONT_SCALE * (alertPopupTimer / ALERT_POPUP_TIME));
			StealthGame.FONT.draw(batch, "!", owner.getxPos() + 0.1f, owner.getyPos() + 0.5f);
		}
	}
	
	public Component getCopy(Entity owner){
		return new GuardComponent(owner, player);
	}
	
	public ComponentIdentifier getIdentifier(){
		return ComponentIdentifier.GUARD_COMPONENT;
	}
}
