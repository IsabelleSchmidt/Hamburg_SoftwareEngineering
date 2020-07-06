package business.components;

public class Street extends Item{

	//muss sich merken welche straﬂe und wo es gesetzt wurde
	
	
	private String type;

    public Street(String type,int positionX, int positionY, int alignment){
    	super(positionX, positionY, alignment);
    	this.type = type;
    }
    public Street(int positionX, int positionY, int alignment){
    	super(positionX, positionY, alignment);
    	
    }

   
    public String getType() {
		return type;
	}
}
