package walnoot.stealth;

import java.util.ArrayList;

import walnoot.stealth.components.CollideComponent;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map{
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<CollideComponent> collideComponents = new ArrayList<CollideComponent>();
	
	public void update(){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update(this);
		}
	}
	
	public void render(SpriteBatch batch){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(batch);
		}
	}
	
	public ArrayList<Entity> getEntities(){
		return entities;
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	public void removeEntity(Entity e){
		entities.remove(e);
	}
	
	public ArrayList<CollideComponent> getCollidables(){
		return collideComponents;
	}
	
	public void addCollidable(CollideComponent e){
		collideComponents.add(e);
	}
	
	public void removeCollidable(CollideComponent e){
		collideComponents.remove(e);
	}
}
