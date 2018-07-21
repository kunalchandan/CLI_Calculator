import java.util.HashMap;

public class Parser {

	String userInput;
	HashMap<String, Integer> operators;
					//E D M A S
	int[] operands = {0,1,2,3,4};

	public Parser(String input) {
		userInput = input;
		operators = new HashMap<String, Integer>();
		operators.put("^", new Integer(0));
		operators.put("/", new Integer(1));
		operators.put("*", new Integer(2));
		operators.put("+", new Integer(3));
		operators.put("-", new Integer(4));
	}

	public boolean exit(String input) {
		if(input.toLowerCase().equals("q")) {
			return true;
		}
		else {
			return false;
		}
	}

	public int expression(String input) {
		//Find outermost brackets in expression
		try {
			int start = input.indexOf("(")+1;
			int end  = input.indexOf(")");
			int ep2 = end+2;
			int operationToApply = -1;
			try {
				if(end < input.length()) {
					String operation = ""+input.charAt(end+1);
					operationToApply = operators.get(operation);
				}
			} catch(StringIndexOutOfBoundsException sioobe) {}
			switch(operationToApply) {
			case 0:
				return (int) Math.pow(expression(input.substring(start, end)), expression(input.substring(ep2, input.length())));
			case 1:
				return expression(input.substring(start, end))/expression(input.substring(ep2, input.length()));
			case 2:
				return expression(input.substring(start, end))*expression(input.substring(ep2, input.length()));
			case 3:
				return expression(input.substring(start, end))+expression(input.substring(ep2, input.length()));
			case 4:
				return expression(input.substring(start, end))-expression(input.substring(ep2, input.length()));
			}
			return expression(input.substring(start, end));
		} catch (NullPointerException npe) {
			//System.out.println("No brackets ");
		}
		//if cannot
			//apply operation between elements within

		try {
			return Integer.parseInt(input);
		} catch(NumberFormatException nfe) {
			int index = findOperand(input);
			if(index == -1) {
				throw nfe;
			}
			int type = findType(input, index);
			String[] numsAsString = null;
			switch(type) {
			case 0:
				numsAsString = input.split("^");
				break;
			case 1:
				numsAsString = input.split("/");
				break;
			case 2:
				numsAsString = input.split("\\*");
				break;
			case 3:
				numsAsString = input.split("\\+");
				break;
			case 4:
				numsAsString = input.split("-");
				break;
			}
			int[] numbers = {Integer.parseInt(numsAsString[0]),Integer.parseInt(numsAsString[1])};

			switch(type) {
			case 0:
				return (int) Math.pow(numbers[0], numbers[1]);
			case 1:
				return numbers[0]/numbers[1];
			case 2:
				return numbers[0]*numbers[1];
			case 3:
				return numbers[0]+numbers[1];
			case 4:
				return numbers[0]-numbers[1];
			}
		}
		return 0;
	}

	private int findType(String input, int index) {
		return operators.get(new String(""+input.charAt(index)));
	}

	private int findOperand(String input) {
		for(int x = 0; x < input.length(); x++) {
			if(!((input.charAt(x) >= 48) && (input.charAt(x) <= 57))) {
				return x;
			}
		}
		return -1;
	}
}
