import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Plane {
	private List<String> passengers;
	private int  maxPassengers;
	private Date lastTakeOffTime;
	private Date lastLandingTime;
	 
	Plane(int maxPassengers) {
		
		this.maxPassengers = maxPassengers;
		this.passengers = new  ArrayList<String>();
	}
	public void onboard(String passengerName) {
		this.passengers.add(passengerName);
		
	}
	
	public Date Settakeoff() {
		this.lastTakeOffTime = new Date();
		return lastTakeOffTime;
	}
	public Date getTakeOffTime() {
        return this.lastTakeOffTime;
    }
	public void setland() {
		this.lastLandingTime = new Date();
		this.passengers.clear();
	}
	
	public Date getLastTimeLanded() {
		return this.lastLandingTime;
	}
	public List<String> getPassengers()
	{
		return this.passengers;
	}
}


public class Activity6 {
	
	public static void main(String[] args) throws InterruptedException {
		
		//Create an object of plane 
		Plane b747 = new Plane(10);
		b747.onboard("Sravani");
		b747.onboard("Hari");
		b747.onboard("Chandu");
		b747.onboard("Manu");
		b747.onboard("Ramu");
		
		//set the takeoff Time
		b747.Settakeoff();
		System.out.println("Plane took of at:" + b747.getTakeOffTime());
		System.out.println("The passengers in the plane" +b747.getPassengers());
		
		//Flying 
		System.out.println("the Plane is flying....");
		Thread.sleep(5000);
		
		//set the landing Time 
		b747.setland();
		System.out.println("Plabe landed at "+b747.getLastTimeLanded());
		System.out.println("The passengers after plane landing " + b747.getPassengers());
		
	}

}