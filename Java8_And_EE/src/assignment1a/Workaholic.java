package assignment1a;
//Create Workaholic class, extend Worker class
public class Workaholic extends Worker {
	//Create overtime variable
	public static final int OVERTIME = 500;

	public Workaholic(String name, float hourlyIncome, int age, Worker coWorker) {
		super(name, hourlyIncome, age, coWorker);
	}
	
	//Method to calculate income
	public void work(int hours) {
		for(int i = 1; i<=hours; i++)
			setEarned(getEarned() + getHourlyIncome());
		//For loop to calculate overtime
		for(int j = 1; j<=OVERTIME; j++)
			setEarned(getEarned() + getHourlyIncome());
	}
	 
	//While age under 65 call work method passing hours
	public void work() {
		while(age++ < RETIREMENT_AGE)
			work(2000);
	}
	
	//Print statement for main
	public String info() {
		return getName() + " earned " + getEarned();
	}
}
