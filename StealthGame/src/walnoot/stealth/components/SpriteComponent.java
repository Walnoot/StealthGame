package walnoot.stealth.components;

import walnoot.stealth.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteComponent extends Component{
	private Sprite sprite;
	
	public SpriteComponent(Entity owner, Texture texture){
		super(owner);
		
		sprite = new Sprite(new TextureRegion(texture, 0, 0, 256, 256));
		sprite.setSize(0.5f, 0.5f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
	}
	
	public void render(SpriteBatch batch){
		sprite.setPosition(owner.getxPos() - sprite.getWidth() / 2, owner.getyPos() - sprite.getHeight() / 2);
		sprite.setRotation(owner.getRotation());
		sprite.draw(batch);
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public ComponentIdentifier getIdentifier(){
		return ComponentIdentifier.SPRITE_COMPONENT;
	}
}
