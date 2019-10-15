package assignment1b;
//Create abstract class GeometricFugure2
abstract class GeometricFigure2 {
	
	//Create display method
	public void display() {
		System.out.println("\nThis is some geometric figure.");
	}
	//Create abstract calcArea method
	public abstract double calcArea();
	
	//Create main method
	public static <T> void main(String[] args) {
		
		//Create octagon
		Octagon<T> oct = new Octagon<T>(0);
		
		//Call Methods
		oct.updateFromConsole();
		oct.WriteToConsole();
		oct.display();
		oct.compareTo(oct);	
	}
}
