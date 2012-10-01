package walnoot.stealth;

import java.util.ArrayList;

import walnoot.stealth.components.CircleCollideComponent;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map{
	public static final String MAP_FOLDER_NAME = "maps";
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<CircleCollideComponent> collideComponents = new ArrayList<CircleCollideComponent>();
	
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
	
	/*public static Map loadMap(String filename){
		FileHandle folder = Gdx.files.internal(MAP_FOLDER_NAME);
		
		//if(!folder.exists()) folder.mkdirs();
		
		return null;
	}*/
	
	public ArrayList<Entity> getEntities(){
		return entities;
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	public void removeEntity(Entity e){
		entities.remove(e);
	}
	
	public ArrayList<CircleCollideComponent> getCollidables(){
		return collideComponents;
	}
	
	public void addCollidable(CircleCollideComponent e){
		collideComponents.add(e);
	}
	
	public void removeCollidable(CircleCollideComponent e){
		collideComponents.remove(e);
	}
}
