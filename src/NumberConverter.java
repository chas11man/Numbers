import java.util.Scanner;
import java.util.ArrayList;

public class NumberConverter
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		ArrayList<String> lines = new ArrayList<String>();
		
		while(in.hasNext())
		{
			lines.add(in.nextLine());
		}
		
		//Convert and print
	}
	
	public int convertNumber(ArrayList<String> input)
	{
		if(input.get(0) == "naught" || input.get(0) == "zero")
		{
			return 0;
		}
		
		int number = 0;
		boolean negative = false;
		while(!input.isEmpty())
		{
			if(input.contains("negative") || input.contains("minus"))
			{
				negative = true;
				input.remove("negative");
				input.remove("positive");
			}
			else if(input.contains("million"))
			{
				number += million(input.subList(0, input.indexOf("million")));
			}
		}
	}
	
	private int million(ArrayList<String> subList)
	{
		
	}
	
	private int hundred(ArrayList<String> subList)
	{
		
	}
	
	private int ten(ArrayList<String> subList)
	{
		
	}
	
	private int one(String num)
	{
		
	}
}