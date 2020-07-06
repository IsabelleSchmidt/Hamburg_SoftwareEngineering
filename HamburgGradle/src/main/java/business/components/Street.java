package business.components;

public class Street extends Item{

	//muss sich merken welche straﬂe und wo es gesetzt wurde
	
	
	private StreetType type;

    public Street(StreetType type,int positionX, int positionY, int alignment){
    	super(positionX, positionY, alignment);
    	this.type = type;
    }
    
    public Street(int positionX, int positionY, int alignment){
    	super(positionX, positionY, alignment);
    	
    }

   
    public void setType(StreetType type) {
    	this.type = type;
    }
    public StreetType getType() {
		return type;
	}
}
