import java.util.List;

public class ValidNumberCheck
{
	private List<String> numbers;
	
	public ValidNumberCheck(List<String> numberList)
	{
		numbers = numberList;
	}
	
	public boolean validInput()
	{
		for(String word : numbers)
		{
			validWord(word);
		}
		validOrder(numbers);
		return true;
	}
	
	private boolean validWord(String word)
	{
		NumberConverter nc = new NumberConverter();
		if(nc.checkIfNegative(word) || nc.checkIfZero(word) || nc.checkIfOne(word) || nc.checkIfTen(word) || nc.checkIfTeen(word) || word.equals("million") || word.equals("thousand") || word.equals("hundred"))
		{
			return true;
		}
		else
		{
			System.err.println("'" + word + "' is not a valid word");
			System.exit(7);
			return false;
		}
	}
	
	private boolean validOrder(List<String> input)
	{
		List<String> tempNumbers = input;
		int millionCount = 0;
		int thousandCount = 0;
		
		tempNumbers = validNegative(tempNumbers);
		while(!tempNumbers.isEmpty())
		{
			if(tempNumbers.contains("million"))
			{
				if(millionCount != 0)
				{
					System.err.println("Only one 'million' allowed");
					System.exit(7);
				}
				else
				{
					validMillion(tempNumbers.subList(0, tempNumbers.indexOf("million")));
					tempNumbers = tempNumbers.subList(tempNumbers.indexOf("million")+1, tempNumbers.size());
					millionCount++;
				}
			}
			else if(tempNumbers.contains("thousand"))
			{
				if(thousandCount != 0)
				{
					System.err.println("Only one 'thousand' allowed");
					System.exit(7);
				}
				else
				{
					validThousand(tempNumbers.subList(0, tempNumbers.indexOf("thousand")));
					tempNumbers = tempNumbers.subList(tempNumbers.indexOf("thousand")+1, tempNumbers.size());
					thousandCount++;
				}
			}
			else
			{
				validPrefix(tempNumbers);
				tempNumbers = tempNumbers.subList(0, 0);
			}
		}
		return true;
	}
	
	private List<String> validNegative(List<String> words)
	{
		List<String> tempNumbers = words;
		int negCount = 0;
		while(tempNumbers.contains("negative") || tempNumbers.contains("minus"))
		{
			if(negCount != 0)
			{
				System.err.println("Only one 'negative' or 'minus' allowed");
				System.exit(7);
			}
			else if(tempNumbers.contains("negative"))
			{
				if(tempNumbers.indexOf("negative") != 0)
				{
					System.err.println("negative can only be the first word");
					System.exit(7);
				}
				else
				{
					tempNumbers.remove("negative");
				}
			}
			else if(tempNumbers.contains("minus"))
			{
				if(tempNumbers.indexOf("minus") != 0)
				{
					System.err.println("minus can only be the first word");
					System.exit(7);
				}
				else
				{
					tempNumbers.remove("minus");
				}
			}
			negCount++;
		}
		return tempNumbers;
	}
	
	private boolean validMillion(List<String> words)
	{
		List<String> tempNumbers = words;
		if(tempNumbers.size() < 1)
		{
			System.err.println("'million' must be preceded by a number one through nine hundred ninty nine");
			System.exit(7);
		}
		else if(tempNumbers.contains("thousand"))
		{
			System.err.println("'thousand' can not come before 'million'");
			System.exit(7);
		}
		else if(validPrefix(tempNumbers))
		{
			return true;
		}
		else
		{
			System.err.println("validMillion error");
			System.exit(7);
		}
		return false;
	}
	
	private boolean validThousand(List<String> words)
	{
		List<String> tempNumbers = words;
		if(tempNumbers.size() < 1)
		{
			System.err.println("'thousand' must be preceded by a number one through nine hundred ninty nine");
			System.exit(7);
		}
		else if(validPrefix(tempNumbers))
		{
			return true;
		}
		else
		{
			System.err.println("validThousand error");
			System.exit(7);
		}
		return false;
	}
	
	private boolean validPrefix(List<String> words)
	{
		List<String> tempNumbers = words;
		int hundredCount = 0;
		NumberConverter nc = new NumberConverter();
		while(!tempNumbers.isEmpty())
		{
			if(tempNumbers.contains("hundred"))
			{
				if(hundredCount != 0)
				{
					System.err.println("Too many instances of 'hundred'");
					System.exit(7);
				}
				else if(tempNumbers.indexOf("hundred") != 1 || !nc.checkIfOne(tempNumbers.get(0)))
				{
					System.err.println("'hundred' must have a number between one and nine before it");
					System.exit(7);
				}
				else
				{
					tempNumbers = tempNumbers.subList(tempNumbers.indexOf("hundred")+1, tempNumbers.size());
					hundredCount++;
				}
			}
			else if(nc.checkIfTen(tempNumbers.get(0)))
			{
				if(!nc.checkIfOne(tempNumbers.get(1)) || tempNumbers.size() > 2)
				{
					System.err.println("'" + tempNumbers.get(0) + "' must be followed by a single number one through nine");
					System.exit(7);
				}
				else
				{
					tempNumbers = tempNumbers.subList(0, 0);
				}
			}
			else if(nc.checkIfTeen(tempNumbers.get(0)))
			{
				if(tempNumbers.size() > 1)
				{
					System.err.println("'" + tempNumbers.get(0) + "' must be followed by 'million', 'thousand', or nothing");
					System.exit(7);
				}
				else
				{
					tempNumbers = tempNumbers.subList(0, 0);
				}
			}
			else if(nc.checkIfOne(tempNumbers.get(0)))
			{
				if(tempNumbers.size() > 1)
				{
					System.err.println("'" + tempNumbers.get(0) + "' was used incorrectly");
					System.exit(7);
				}
				else
				{
					tempNumbers = tempNumbers.subList(0, 0);
				}
			}
			else
			{
				System.err.println("How did you mess up that badly?");
				System.exit(7);
			}
		}
		return true;
	}
}
