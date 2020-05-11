package assignment;
// File: Car.java
// Description:  the Car class use to create objects representing cars.
//Yilin Yang 300136515

public class Car {
    // Class attributes
    private String plateNumber;  
    // the plate number of a car
    private Time enteringTime;   
    // the time when the car enters a parking lot
    // Constructor
    // Parameter: plate - a string giving the plate of the car
    // Description: initializes the plateNumber of the car.
    //              Sets the enteringTime to a save value.
    public Car(String plate){
    	this.plateNumber = plate;
    }

    // Getter methods
    public Time getEnteringTime(){
    	return enteringTime;
    }
    public String getPlateNumber(){
    	return plateNumber;
   }
    
    // Setter methods
    public void setEnteringTime(Time t){
    	this.enteringTime = t;
    } 
    
    // Method: toString
    // Description: Allows the reference to the Car object to be 
    //              included in a concatenation operation.  That is
    //              it provides the means to give a string representation
    //              associated to the object.
    public String toString(){
    
    	return(plateNumber);
    }
}
