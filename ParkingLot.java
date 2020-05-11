package assignment;
// File: ParkingLot.java
// Description: Contains ParkingLot class used to create a ParkingLot object.
//Yilin Yang 300136515
import java.text.DecimalFormat;
public class ParkingLot 
{
    // Attributes - all private
   /* Add your attributes here */
    private Car [] spots;        // an array that represents the parking lot spots
                                 // is null when not car is present in the spot, and references a Car 
                                 // object when occupied.
    private static DecimalFormat curFmt = new DecimalFormat("$ #0.00");  // For formating currency values
    private String lotName;
    private double lotRate;
    private double lotMax;
    private int lotCapacity;
    private double dailyRevenue;

    
    // Sets up the parking lot object.  Note that the spots array is of fixed size (value of capacity)
    // an initially shall contain only null elements.
    // Parameters: 
    //         name - name for the lot
    //         rate - value for hourlyRate
    //         max - value for maxCharge
    //         capacity - value for capacity
    public ParkingLot (String name, double rate, double max, int capacity){
    	this.lotName = name;
    	this.lotRate = rate;
    	this.lotMax = max;
    	this.lotCapacity = capacity;
    	this.spots = new Car[capacity];
    }
    
    // Getter Methods
    public double getDailyRevenue() {
    	return dailyRevenue;
    }
    public double getHourlyRate() {
    	return lotRate;
    }
    public double getMaxCharge() {
    	return lotMax;
    }
    public int getCapacity() {
    	return lotCapacity;
    }
    
    // Reset method for clearing revenue (i.e. setting to 0)
    public void resetDailyRevenue() { 
    	this.dailyRevenue = 0;
    }
    // Reset method for update (also resets the spots array).
    // Parameters:
    //     rate - new rate
    //     max  - new maximum value
    //     capacity - new capacity
    public void resetLot(double rate, double max, int capacity) {
    	this.lotRate = rate;
    	this.lotMax = max;
    	this.lotCapacity = capacity;
    	this.spots = new Car[capacity];
    	resetDailyRevenue();
    }

    // Method: findSpot
    // Description: searches the spots array of the
    //              parking lot to find the index of the first available parking
    //              spot (i.e. entry is null).  
    //              If the lot is full, it returns the value of capacity.
    private int findSpot() {
    	for(int i = 0; i< spots.length;i++) {
    		if (spots [i] == null) {
    			return i;}
    	}
    	return this.lotCapacity;
    }
    

    // Method: findCar -- 
    // Parameters: 
    //     c - reference to a Car object.
    // Description: Searches the spot array for the
    //              given car.  If it is found it, returns the index of the car; if
    //              not it returns the capacity of the parking lot.
    private int findCar(Car c) {
    	for(int i = 0; i< spots.length;i++){
    		if (this.spots[i] !=null && c.getPlateNumber()== spots[i].getPlateNumber()){
    			return i;
    		}
    	}
    	return this.lotCapacity;
    }
    // Method: carEnter
    // Parameters: 
    //      c - a reference to a Car object  (car entering lot)
    //      t - a reference to a Time object (entering time)
    // Description: Allows a car to enter the lot and park in an
    //              available spot (see findSpot method). 1.The cars enteringTime
    //              is updated when the car parks in the available spot and outputs
    //              a message that the car has parked in the lot.
    //              2.If no spot is available, or 3. the car is already in the lot,
    //              then an error message is printed.
    public void carEnter(Car c, Time t) {
		int freeSpotIndex = findSpot();
		int carFoundIndex = findCar(c);
		String outputMessage;

		if (isLotFull(freeSpotIndex)) {
			outputMessage = String.format("%s - Lot is full. Car %s cannot enter %s", c.getEnteringTime().toString(),
					c.getPlateNumber(), this.toString());
		} else {
			carFoundIndex = findCar(c);
			if (isCarFound(carFoundIndex)) {
				outputMessage = String.format("%s - Car %s is already parking in %s", c.getEnteringTime().toString(),
						c.getPlateNumber(), this.toString());
			} else {
				c.setEnteringTime(t);
				this.spots[freeSpotIndex] = c;
				outputMessage = String.format("%s - Car %s entered %s", c.getEnteringTime().toString(), c.getPlateNumber().toString(),
						this.toString());
			}
		}
		System.out.println(outputMessage);
	}

	// Return true if the lot is at capacity
	private boolean isLotFull(int freeSpotIndex) {
		return freeSpotIndex == lotCapacity;
	}

	// Return true if the car is already in the lot
	private boolean isCarFound(int carFoundIndex) {
		return carFoundIndex != lotCapacity;
	}

	// Method: carLeave
	// Parameters:
	// c - reference to a Car object (car leaving)
	// t - reference to a Time object (leaving time).
	// Description: Allows the car to leave. The occupied spot is
	// set to null, the dailyRevenue is updated. The parking fee
	// is computed on an 1/2 hour basis. If the charge is greater than
	// maxCharge, then only maxCharge is added to the revenue.
	// If the the car is not in the parking lot or the leaving time
	// is earlier than the entering time, then an error message is printed.
	public void carLeave(Car c, Time t) {
		int carFoundIndex = this.findCar(c);;
		double parkingFee;
		String outputMessage;

		if (isCarFound(carFoundIndex)) {
			if (t.isBefore(c.getEnteringTime())) {
				outputMessage = "Invalid leave time.  Leave time can not before enter time Leave.";
			} else {
				parkingFee = getParkingFee(c.getEnteringTime().duration(t));
				this.dailyRevenue = this.dailyRevenue + parkingFee;
				spots[carFoundIndex] = null;
				outputMessage = String.format("%s - Car %s left Test Lot and paid %s.", t.toString(),c.getPlateNumber(),
						curFmt.format(parkingFee));
			}
		} else {
			outputMessage = String.format("%s - Car %s is not in %s", t.toString(), c.getPlateNumber(), this.toString());
		}

		System.out.println(outputMessage);
	}

	private double getParkingFee(double duration) {
		long hours = (long) duration;
		double cost;
		double minutes = (duration - hours);
		double finalParkingFee;

		cost = getHourFee(hours) + getMinuteFee(minutes);
		if (cost > this.lotMax) {
			finalParkingFee = this.lotMax;
		} else {
			finalParkingFee = cost;
		}
		return finalParkingFee;
	}

	/*private double calulateFinalParkingFee(double cost) {
		double finalParkingFee;

		if (cost > this.lotMax) {
			finalParkingFee = this.lotMax;
		} else {
			finalParkingFee = cost;
		}

		return finalParkingFee;
	}*/

	private double getHourFee(double hours) {
		double cost = 0;

		cost = this.lotRate * hours;

		return cost;
	}

	private double getMinuteFee(double minutes) {
		double cost = 0;

		if (minutes > 0 && minutes <= .5) {
			cost = this.lotRate / 2;
		} else if (minutes > .5) {
			cost = this.lotRate;
		}
		
		return cost;
	}
    public void print(){
    	System.out.println(String.format(">--------- %s ----------<", this.toString()));

		buildLotOutPut();

		System.out.println(String.format("Daily Revenue: %s", curFmt.format(this.dailyRevenue)));
		System.out.println(">--------------------<");
	}

 // Assuming the length do not change and at least one element in the array.
 	private void buildLotOutPut() {
 		String topAndBottom;
 		String middle;
 		Car c;
 		int index;
 		
 		if(spots.length > 0) {
 			 topAndBottom = "+";
 			 middle = "|";

 		for (index = 0; index < spots.length; index++) {
 			c = spots[index];
 			if (c != null) {
 				middle = middle + " " + c.getPlateNumber() + " |";

 			} else {
 				middle = middle + "  Empty  |";
 			}

 			topAndBottom = topAndBottom + "---------+";
 		}

 		System.out.println(topAndBottom);
 		System.out.println(middle);
 		System.out.println(topAndBottom);
 		}
 	}
    
    // Method: toString
    // Description: Allows the references to a parkingLot object to be included
    //              in a String concatenation expression. Simply returns the lot name.
    public String toString(){
    	return this.lotName;
    }
}
