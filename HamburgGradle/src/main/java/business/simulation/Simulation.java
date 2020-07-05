package business.simulation;


import java.util.ArrayList;

import business.components.Street;
import business.components.StreetType;
import business.components.Trafficlight;
import business.components.Vehicle;

public class Simulation {

	/* Main
	 * 
	 * Mit gridgröße, anzahl der straßenelemente, anzahl der autos, 
	 * wird in der fxsimulation aufgerufen
	 */
	
	private SimulationPreview simulationPreview;
	private ArrayList<Street> crossing;
	private ArrayList<Street> curve;
	private ArrayList<Street> junction;
	private ArrayList<Street> straight;
	private ArrayList<Trafficlight> trafficLights;
	private ArrayList<Vehicle> vehicles;
	
	public Simulation(SimulationPreview simulationPreview,ArrayList<Street> crossing,ArrayList<Street> curve,ArrayList<Street> junction,
			ArrayList<Street> straight,ArrayList<Trafficlight> trafficLights,ArrayList<Vehicle> vehicles) {
		
		this.simulationPreview = simulationPreview;
		this.crossing = crossing;
		this.curve = curve;
		this.junction = junction;
		this.straight = straight;
		this.trafficLights = trafficLights;
		this.vehicles = vehicles;
	
	}
	
	public void addStreetItems(Street item) {
		int y= item.getPositionY();
		int x = item.getPositionX();
		
		if(item.getType() == StreetType.CROSSING) {
			this.crossing.add(item);
		}else if(item.getType() == StreetType.CURVE) {
			this.curve.add(item);
		}else if(item.getType() == StreetType.JUNCTION) {
			this.junction.add(item);
		}else if(item.getType() == StreetType.STRAIGHT) {
			this.straight.add(item);
		}
	}
	
	
	public void addTrafficLight(Trafficlight trafficlight) {
		trafficLights.add(trafficlight);
	}
	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}
	
	
	
	public ArrayList<Street> getCrossing() {
		return crossing;
	}


	public void setCrossing(ArrayList<Street> crossing) {
		this.crossing = crossing;
	}


	public ArrayList<Street> getCurve() {
		return curve;
	}


	public void setCurve(ArrayList<Street> curve) {
		this.curve = curve;
	}


	public ArrayList<Street> getJunction() {
		return junction;
	}


	public void setJunction(ArrayList<Street> junction) {
		this.junction = junction;
	}


	public ArrayList<Street> getStraight() {
		return straight;
	}


	public void setStraight(ArrayList<Street> straight) {
		this.straight = straight;
	}


	public ArrayList<Trafficlight> getTrafficLights() {
		return trafficLights;
	}


	public void setTrafficLights(ArrayList<Trafficlight> trafficLights) {
		this.trafficLights = trafficLights;
	}


	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}


	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	
}
