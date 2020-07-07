package business.simulation;

import java.util.ArrayList;

import business.components.Item;
import business.components.Street;
import business.components.StreetType;
import business.components.Trafficlight;
import business.components.Vehicle;

public class Simulation {

	public Grid grid;
	private ArrayList<Item> elemente;
	Street street;

	/*
	 * Main
	 * 
	 * Mit gridgröße, anzahl der straßenelemente, anzahl der autos, wird in der
	 * fxsimulation aufgerufen
	 */

	public Simulation() {
		grid = new Grid();
		elemente = new ArrayList<>();

	}

	public void addItem(String name) {

		grid.setGrid(name);

		if (name.equals("Curve")) {
			street = new Street(StreetType.CURVE, grid.getX(), grid.getY(), 0);
			elemente.add(street);
			System.out.println("liste: " + elemente.toString());
		}
		if (name.equals("Crossing")) {
			street = new Street(StreetType.CROSSING, grid.getX(), grid.getY(), 0);
			elemente.add(street);
			System.out.println("liste: " + elemente.toString());
		}
		if (name.equals("Straight")) {
			street = new Street(StreetType.STRAIGHT, grid.getX(), grid.getY(), 0);
			elemente.add(street);
			System.out.println("liste: " + elemente.toString());
		}
		if (name.equals("Junction")) {
			street = new Street(StreetType.JUNCTION, grid.getX(), grid.getY(), 0);
			elemente.add(street);
			System.out.println("liste: " + elemente.toString());
		}
		System.out.println("SimulationADD test: " + name + " " + grid.getX() + " " + grid.getY());
	}

	public void removeItem(int x, int y) {

		if ( grid.getX()== x && grid.getY() == y) {
			
			elemente.remove(street);
		}
		System.out.println("Remove: " + street.getPositionX() + street.getPositionY());
		System.out.println("RemoveList: " + elemente.toString());
		grid.removeFromGrid();

	}
	public ArrayList<Item> getElemente() {
		return elemente;
	}

	public Grid getGrid() {
		return grid;
	}
}
/*
 * private ArrayList<Street> streets; private ArrayList<Trafficlight>
 * trafficLights; private ArrayList<Vehicle> vehicles; private ArrayList<Item>
 * allItems = new ArrayList<>();
 * 
 * public Simulation(ArrayList<Street> streets, ArrayList<Trafficlight>
 * trafficLights,ArrayList<Vehicle> vehicles) {
 * 
 * this.streets = streets; this.trafficLights = trafficLights; this.vehicles =
 * vehicles;
 * 
 * allItems.addAll(streets); allItems.addAll(trafficLights);
 * allItems.addAll(vehicles); }
 * 
 * public void addStreetItems(Street street) { int y= street.getPositionY(); int
 * x = street.getPositionX(); streets.add(street); }
 * 
 * 
 * public void addTrafficLight(Trafficlight trafficlight) { int y=
 * trafficlight.getPositionY(); int x = trafficlight.getPositionX();
 * trafficLights.add(trafficlight); } public void addVehicle(Vehicle vehicle) {
 * int y= vehicle.getPositionY(); int x = vehicle.getPositionX();
 * vehicles.add(vehicle); }
 * 
 * public void removeStreet(Street street) { streets.remove(street); }
 * 
 * public void removeTrafficLight(Trafficlight trafficlight) {
 * trafficLights.remove(trafficlight); } public void removeVehicle(Vehicle
 * vehicle) { vehicles.remove(vehicle); }
 * 
 * 
 * 
 * public ArrayList<Street> getStreet() { return streets; }
 * 
 * 
 * public void setStreet(ArrayList<Street> streets) { this.streets = streets; }
 * 
 * 
 * 
 * 
 * public ArrayList<Trafficlight> getTrafficLights() { return trafficLights; }
 * 
 * 
 * public void setTrafficLights(ArrayList<Trafficlight> trafficLights) {
 * this.trafficLights = trafficLights; }
 * 
 * 
 * public ArrayList<Vehicle> getVehicles() { return vehicles; }
 * 
 * 
 * public void setVehicles(ArrayList<Vehicle> vehicles) { this.vehicles =
 * vehicles; }
 * 
 * public ArrayList<Item> getAllItems(){ return allItems; } }
 */
