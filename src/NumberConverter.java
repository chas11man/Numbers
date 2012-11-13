import java.util.List;

public class NumberConverter
{
	public int convertNumber(List<String> input)
	{
		if(checkIfZero(input.get(0)))
		{
			return 0;
		}
		
		int number = 0;
		boolean negative = false;
		while(!input.isEmpty())
		{
			if(checkIfNegative(input.get(0)))
			{
				negative = true;
				input.remove("negative");
				input.remove("positive");
			}
			else if(input.contains("million"))
			{
				number += million(input.subList(0, input.indexOf("million")));
				input = input.subList(input.indexOf("million")+1, input.size());
			}
			else if(input.contains("thousand"))
			{
				number += thousand(input.subList(0, input.indexOf("thousand")));
				input = input.subList(input.indexOf("thousand")+1, input.size());
			}
			else if(input.contains("hundred"))
			{
				number += hundred(input.subList(0, input.indexOf("hundred")));
				input = input.subList(input.indexOf("hundred")+1, input.size());
			}
			else
			{
				number += ten(input);
				input = input.subList(0, 0);
			}
		}
		if(negative == true)
		{
			number = -number;
		}
		return number;
	}
	
	private int million(List<String> subList)
	{
		int subNumber = prefix(subList);
		return(subNumber*1000000);
	}
	
	private int thousand(List<String> subList)
	{
		int subNumber = prefix(subList);
		return(subNumber*1000);
	}
	
	private int hundred(List<String> subList)
	{
		return(one(subList.get(0))*100);
	}
	
	private int prefix(List<String> subList)
	{
		int subNumber = 0;
		while(!subList.isEmpty())
		{
			if(subList.contains("hundred"))
			{
				subNumber += hundred(subList.subList(0, subList.indexOf("hundred")));
				subList = subList.subList(subList.indexOf("hundred")+1, subList.size());
			}
			else
			{
				subNumber += ten(subList);
				subList = subList.subList(0, 0);
			}
		}
		return subNumber;
	}
	
	private int ten(List<String> subList)
	{
		int subNumber = 0;
		for(String word : subList)
		{
			if(checkIfTen(word))
			{
				subNumber += ten(word);
			}
			else if(checkIfTeen(word))
			{
				subNumber += teen(word);
			}
			else if(checkIfOne(word))
			{
				subNumber += one(word);
			}
		}
		return subNumber;
	}
	
	public boolean checkIfTen(String num)
	{
		switch(num)
		{
			case "twenty":	return true;
			case "thirty":	return true;
			case "forty":	return true;
			case "fifty":	return true;
			case "sixty":	return true;
			case "seventy":	return true;
			case "eighty":	return true;
			case "ninety":	return true;
			default:		return false;
		}
	}
	
	private int ten(String num)
	{
		switch(num)
		{
			case "twenty":	return 20;
			case "thirty":	return 30;
			case "forty":	return 40;
			case "fifty":	return 50;
			case "sixty":	return 60;
			case "seventy":	return 70;
			case "eighty":	return 80;
			case "ninety":	return 90;
		}
		return 0;
	}
	
	public boolean checkIfTeen(String num)
	{
		switch(num)
		{
			case "ten":			return true;
			case "eleven":		return true;
			case "twelve":		return true;
			case "thirteen":	return true;
			case "fourteen":	return true;
			case "fifteen":		return true;
			case "sixteen":		return true;
			case "seventeen":	return true;
			case "eighteen":	return true;
			case "nineteen":	return true;
			default:			return false;
		}
	}
	
	private int teen(String num)
	{
		switch(num)
		{
			case "ten":			return 10;
			case "eleven":		return 11;
			case "twelve":		return 12;
			case "thirteen":	return 13;
			case "fourteen":	return 14;
			case "fifteen":		return 15;
			case "sixteen":		return 16;
			case "seventeen":	return 17;
			case "eighteen":	return 18;
			case "nineteen":	return 19;
		}
		return 0;
	}
	
	public boolean checkIfOne(String num)
	{
		switch(num)
		{
			case "one":		return true;
			case "two":		return true;
			case "three":	return true;
			case "four":	return true;
			case "five":	return true;
			case "six":		return true;
			case "seven":	return true;
			case "eight":	return true;
			case "nine":	return true;
			default:		return false;
		}
	}
	
	private int one(String num)
	{
		switch(num)
		{
			case "one":		return 1;
			case "two":		return 2;
			case "three":	return 3;
			case "four":	return 4;
			case "five":	return 5;
			case "six":		return 6;
			case "seven":	return 7;
			case "eight":	return 8;
			case "nine":	return 9;
			default:		return 0;
		}
	}
	
	public boolean checkIfNegative(String num)
	{
		switch(num)
		{
			case "negative":	return true;
			case "minus":		return true;
			default:			return false;
		}
	}
	
	public boolean checkIfZero(String num)
	{
		switch(num)
		{
			case "zero":	return true;
			case "naught":	return true;
			default:		return false;
		}
	}
}