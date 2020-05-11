//Yilin Yang 300136515
package assignment;
import java.util.Arrays;
// File: ParkEventList.java
// Description: Contains the ParkEventList class used to create an object which has a list of
//              ParkEvent objects.

public class ParkEventList {
	// List of park events (an array of objects)
    ParkEvent [] events = new ParkEvent[0]; // initially set with 0 elements
    
    /*
     * Method: add
     * Parameters: ev - a parking event
     * Returns: nothing.
     * Description: Places the event into the list "events" according
     *              to the value of its event time.
     */
    public void add(ParkEvent ev){
    	ParkEvent []updatedEvents = new ParkEvent[events.length +1];
    	int insertIndex = getInsertIndex(ev);
    	
    	for (int index = 0; index < insertIndex;index++) {
    		updatedEvents[index] = events[index];
    	}
    	
    	updatedEvents[insertIndex] = ev;
    	
    	for (int index = insertIndex+1; index < updatedEvents.length;index++) {
    		updatedEvents[index] = events[index-1];
    	}
    	events = updatedEvents;
    }
    private int getInsertIndex (ParkEvent ev) {
    	int insertIndex;
    	
    	for (insertIndex = 0; insertIndex < events.length; insertIndex++) {
    		if (events[insertIndex].getTime().isAfter(ev.getTime())) {
    			break;
    		}
    	}
    	return insertIndex;
    }
    
    /*
     * Method: removeFirstEvent
     * Parameters: none
     * Returns: ev - a parking event or null if events is empty
     * Description: Removes the first event from the "events" list
     *              and returns its reference.
     */
    public ParkEvent removeFirstEvent() {
    	ParkEvent ev =null;
    	if (events.length>0) {
    		ev = events[0];
    		events = Arrays.copyOfRange(events,1,events.length);
    	}
    	return ev;
    }

    /*
     * Method: getEvent
     * Parameters: ix - index of event to get
     * Returns: ParkEvent - a reference to a ParkEvent or null if events is empty
     * Description: Returns the reference to the ParkEvent object in "events" at
     *              index ix.
     */
    public ParkEvent getEvent(int ix) {
    	ParkEvent event = null;
    	if (ix>=0 && ix< events.length) {
    		event = events[ix];
    	}
    	return event;
    }
    
    /*
     * Method: print()
     * Description: Displays the list of events.  Notes that using the reference
     *              to an Event object in the System.out.println, will have the
     *              toString method in the Event class executed to create the 
     *              string to be displayed.
     */
     public void print(){
    	 System.out.println("--------------------------------------------");
    	 for (int index = 0; index < events.length; index++) {
    		 System.out.println(events[index].toString());
    	 }
    	 System.out.println("--------------------------------------------");
    }

}
