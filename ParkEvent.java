package assignment;

// File: ParkEvent.java
// Description:  Contains the ParkEvent class that allows the creation of park events, i.e. an object
//               that defines cars entering and leaving a parking lot.

public class ParkEvent 
{
	public final static String ENTER = "Enter";  // Defines the event as an enter event
	public final static String LEAVE = "Leave";  // Defines the event as a leave event
	private Car theCar; // references a Car object involved in the event
	private ParkingLot theLot;  // references a ParkingLot object involved in the event
	private Time eventTime;     // Gives the time the event occurs.  Used to order events in a
	                            // ParkEventList object
	private String eventType;   // "Leave" the car leaves the parking lot, "Enter" the car enters the parking log
	
	// Constructor
	// Parameters: c, reference to a Car object
	//             p, reference to a ParkingLot object
	//             t, reference to a Time object
	//             evnt, reference to type of event - should be either enter or leave string (see above)
	public ParkEvent(Car c, ParkingLot p, Time t, String evnt){
		this.theCar = c;
		this.theLot = p;
		this.eventTime = t;
		this.eventType = evnt;
	}
	
	// Getter methods
	public Car getCar() {
		return theCar;
	}
	public ParkingLot getParkingLot() {
		return theLot;
	}
	public Time getTime() { 
		return eventTime;
	}
	
	// Method: isEnterEvent
	// Description: Returns true if event is Enter event, that is,
	//              eventType is enter String (see above).
	public boolean isEnterEvent(){
		return this.eventType == ENTER;
	}
	
	// Method: isLeaveEvent
	// Description: Returns true if event is Leave event, that is,
	//              eventType is leave String (see above).
	public boolean isLeaveEvent() {
		return this.eventType == LEAVE;
	}

	// Method: toString
	// Description:  Returns a string associated to the object (see sample output).  
        //               Allows a reference to the object to be used in a String 
        //               concatenation expression.
	public String toString() {
		return String.format("%s : Car is %s, Lot is %s, %s event",eventTime.toString(),theCar.getPlateNumber().toString(),theLot.toString(),this.eventType);
	}
}
