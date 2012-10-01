package walnoot.stealth.components;

import walnoot.stealth.Entity;
import walnoot.stealth.Map;
import walnoot.stealth.StealthGame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FootprintComponent extends Component{
	public static final float SPACE_BETWEEN_STEPS = 0.25f, STEP_ALIVE_TIME = 2f;
	
	private final TextureRegion footprintTexture;
	private float lastXPos, lastYPos;//the coordinates of the walker when he last left a footprint
	private boolean mirrorStep; //left or right step

	public FootprintComponent(Entity owner, TextureRegion footprint){
		super(owner);
		this.footprintTexture = footprint;
		
		lastXPos = owner.getxPos();
		lastYPos = owner.getyPos();
	}
	
	public void update(Map map){
		float dx = lastXPos - owner.getxPos();
		float dy = lastYPos - owner.getyPos();
		
		if(dx * dx + dy * dy > SPACE_BETWEEN_STEPS * SPACE_BETWEEN_STEPS){
			lastXPos = owner.getxPos();
			lastYPos = owner.getyPos();
			
			mirrorStep = !mirrorStep;
			
			Entity footprint = new Entity(owner.getxPos(), owner.getyPos(), owner.getRotation()){
				private float timer;
				
				public void update(Map map){
					timer += StealthGame.SECONDS_PER_UPDATE;
					
					if(timer > STEP_ALIVE_TIME) map.removeEntity(this);
					
					SpriteComponent spriteComponent = (SpriteComponent) getComponent(ComponentIdentifier.SPRITE_COMPONENT);
					spriteComponent.getSprite().setColor(1, 1, 1, 1 - timer / STEP_ALIVE_TIME);
				}
			};
			SpriteComponent sprite = new SpriteComponent(footprint, footprintTexture);
			sprite.getSprite().flip(mirrorStep, false);
			sprite.getSprite().setScale(.25f);
			footprint.addComponent(sprite);
			
			map.addEntity(footprint);
		}
	}
	
	public Component getCopy(Entity owner){
		return new FootprintComponent(owner, footprintTexture);
	}
	
	public ComponentIdentifier getIdentifier(){
		return ComponentIdentifier.FOOTPRINT_COMPONENT;
	}
}
