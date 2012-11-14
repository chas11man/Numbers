import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberScanner
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		List<String> lines = new ArrayList<String>();
		
		while(in.hasNext())
		{
			lines.add(in.next());
		}
		
		ValidNumberCheck vnc = new ValidNumberCheck();
		vnc.validInput(lines);
		
		NumberConverter nc = new NumberConverter();
		int number = nc.convertNumber(lines);
		System.out.println(number);
		in.close();
	}
}