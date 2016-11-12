import java.util.Scanner;

public class HellurWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner (System.in);
		String name = scanner.nextLine();
		System.out.println("Hellur " + name);
		System.out.println("Hyuk is da man");
		
		
	}
	
	// returns a string that consists of the alphabet separated by underscores
	public static String makeABC() {
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String newABC = "";

		for (int i = 0; i < abc.length() - 1; i++) {
			newABC += abc.charAt(i) + "_";
		}
		newABC += abc.charAt(abc.length() - 1);

		return newABC;
	}
}
