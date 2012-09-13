package walnoot.stealth.components;

import walnoot.stealth.Entity;
import walnoot.stealth.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Component implements Cloneable{
	protected final Entity owner;

	public Component(Entity owner){
		this.owner = owner;
	}
	
	public void render(SpriteBatch batch){
		
	}
	
	public void update(Map map){
		
	}
	
	public abstract Component getCopy(Entity owner);
	
	public abstract ComponentIdentifier getIdentifier();
}
