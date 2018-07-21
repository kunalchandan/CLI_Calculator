import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		boolean run = true;
		Scanner in = new Scanner(System.in);
		while(run) {
			// Great Example Expression (2*3)^(5-2) {Should evaluate to 216}
			String input = in.nextLine();
			Parser parse = new Parser(input);
			
			if(parse.exit(input)) {
				run = false;
			}
			
			System.out.println(parse.expression(input));
		}
		in.close();
	}
}
