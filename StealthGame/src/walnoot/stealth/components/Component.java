package walnoot.stealth.components;

import walnoot.stealth.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Component{
	protected final Entity owner;

	public Component(Entity owner){
		this.owner = owner;
	}
	
	public void render(SpriteBatch batch){
		
	}
	
	public void update(){
		
	}
	
	public abstract ComponentIdentifier getIdentifier();
}