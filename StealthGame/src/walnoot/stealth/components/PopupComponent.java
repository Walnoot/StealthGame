package walnoot.stealth.components;

import walnoot.stealth.Entity;

public class PopupComponent extends Component{
	private String text;

	public PopupComponent(Entity owner, String text){
		super(owner);
		this.text = text;
	}
	
	public Component getCopy(Entity owner){
		return new PopupComponent(owner, text);
	}
	
	public ComponentIdentifier getIdentifier(){
		return ComponentIdentifier.POPUP_COMPONENT;
	}
}
