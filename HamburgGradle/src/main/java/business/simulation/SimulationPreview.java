package business.simulation;

import persistence.JsonHandler;

public class SimulationPreview {
	private String name;
	private JsonHandler jsonHandler;
	
	public SimulationPreview(String name, JsonHandler jsonHandler) {
		this.setName(name);
		this.jsonHandler = jsonHandler;
			
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
