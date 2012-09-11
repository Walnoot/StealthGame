package walnoot.stealth.components;

import walnoot.stealth.Entity;

public class PopupComponent extends Component{
	
	public PopupComponent(Entity owner, String text){
		super(owner);
	}
	
	public ComponentIdentifier getIdentifier(){
		return ComponentIdentifier.POPUP_COMPONENT;
	}
}
