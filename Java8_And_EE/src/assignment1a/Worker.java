package assignment1a;
//Create Worker class
public class Worker {
	
	//Create variables
	public static final int RETIREMENT_AGE = 65;
	private String name;
	protected int age;
	private float earned;
	private float hourlyIncome;
	private Worker coWorker;
	
	public Worker(String name, float hourlyIncome, int age, Worker coWorker) {
		this.name = name;
		this.hourlyIncome = hourlyIncome;
		this.age = age;
		this.coWorker = coWorker;
	}
	
	//Create getters for worker
	public String getName() {
		return name;
	}

	public float getEarned() {
		return earned;
	}

	public void setEarned(float earned) {
		this.earned = earned;
	}

	public float getHourlyIncome() {
		return hourlyIncome;
	}

	public int getAge() {
		return age;
	}

	//Method to calculate income
	public void work(int hours) {
		//For statement to calculate amount
		for(int i = 1; i<=hours; i++) {
			setEarned(getEarned() + getHourlyIncome());
			//If no coworker then delegate one
			if(coWorker!=null && i%5==0)
				delegate(1); // from time to time
		}
	}
	
	//While age less than 65
	public void work() {
		while(age++ < RETIREMENT_AGE)
			work(1600);
	}
	
	//Method to delogate coworker
	private void delegate(int hours) {
		coWorker.work(hours);
	}
	
	//Method for print statement in main displaying amount of income
	public String info() {
		return getName() + " earned " + getEarned();
	}
	//Create main
	public static void main(String[] args) {
	
	//Create workers
	Worker jane = new Worker("Jane", 20, 25, null);
	Worker john = new Worker("John", 20, 45, jane);
	Workaholic bill = new Workaholic("Bill", 20, 25, null);
	
	//Call work method on workers
	john.work();
	jane.work();
	bill.work();
	
	//Print amount earned
	System.out.println(john.info());
	System.out.println(jane.info());
	System.out.println(bill.info());
	}
}
