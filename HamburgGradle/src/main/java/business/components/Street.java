package business.components;

public class Street extends Item{

	private StreetType type;

    public Street(StreetType type,int positionX, int positionY, int alignment){
    	super(positionX, positionY, alignment);
    	this.type = type;
    }
    public Street(int positionX, int positionY, int alignment){
    	super(positionX, positionY, alignment);
    	
    }

    public void remove(){

    }
    public StreetType getType() {
		return type;
	}
}
