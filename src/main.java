import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		boolean run = true;
		Scanner in = new Scanner(System.in);
		//General loop, keeps asking for input until user presses "q"
		while(run) {
			// Great Example Expression (2*3)^(5-2) {Should evaluate to 216}
			String input = in.nextLine();
			Parser parse = new Parser(input);
			
			if(parse.exit(input)) {
				run = false;
			}
			
			System.out.println(parse.expression(input));
		}
		//Prevent memory leaks
		in.close();
	}
}
