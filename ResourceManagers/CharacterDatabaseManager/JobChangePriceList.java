package ResourceManagers.CharacterDatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class JobChangePriceList 
{
	private ArrayList<JobPrices> jobs = new ArrayList<JobPrices>();
	protected JobChangePriceList(byte[] file) 
	{
		String data = new String(file);
		String[] lines = data.split("\n");
		//Skip First Line
		for(String line : lines)
		{
			if(line.indexOf("PRICE")!=-1) jobs.add(new JobPrices(line));
		}
	}
	protected JobChangePriceList(List<String> lines) 
	{
		for(String line : lines)
		{
			if(line.indexOf("PRICE")!=-1) jobs.add(new JobPrices(line));
		}
	}
	public String toString()
	{
		setSelfPrices();
		String ret = "NUM " + jobs.size() + ";\r\n";
		{
			for(int i = 0; i < jobs.size(); i++)
			{
				ret += jobs.get(i).toString();
			}
		}
		return ret;
	}
	public int indexOf(int jobCode)
	{
		//Returns the index of a certain job in the list
		for(int i = 0; i < jobs.size(); i++)
		{
			if(jobCode == jobs.get(i).getJobCode())
			{
				return i;
			}
		}
		return -1;
	}
	private JobPrices getJobPrice(int jobCode)
	{
		return jobs.get(indexOf(jobCode));
	}
	public void setTrainingPrice(int jobCode, int newPrice)
	{
		//sets the prices for a column, effectively the price for any job to become this one
		int indexInList = indexOf(jobCode);
		for(JobPrices j : jobs)
		{
			j.setPrice(indexInList, newPrice);
		}
	}
	public void setChangingPrice(int jobCode, int newPrice)
	{
		//sets the prices for a row, effectively the price for this job to become any other one
		getJobPrice(jobCode).setAllPrices(newPrice);
	}
	public void setPrice(int originalJobCode, int newJobCode, int newPrice)
	{
		//Set the price of one job to become another
		int newIndex = indexOf(newJobCode);
		try
		{
			getJobPrice(originalJobCode).setPrice(newIndex, newPrice);
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println("Index of original job code " + originalJobCode + " was " + indexOf(originalJobCode));
			System.out.println("Index of new job code " + newJobCode + " was " + newIndex);
			e.printStackTrace();
		}
	}
	private void setSelfPrices()
	{
		for(JobPrices j : jobs)
		{
			j.setPrice(indexOf(j.getJobCode()), -1);
		}
	}
	private class JobPrices 
	{
		int jobCode = -1;
		ArrayList<Integer> prices = new ArrayList<Integer>();
		private JobPrices(String line) 
		{
			if(line.indexOf("PRICE ")==-1) return;
			String[] numbers = line.split(",");
			jobCode = Integer.parseInt(numbers[0].substring(6 + numbers[0].indexOf("PRICE ")));
			//Skip First Part
			for(int i = 1; i < numbers.length; i++)
			{
				prices.add(bFM.Utils.strToInt(numbers[i]));
			}
		}
		private void setPrice(int index, int newPrice)
		{
			//Set the Price for a specific job to become this job
			prices.set(index, newPrice);
		}
		private void setAllPrices(int newPrice)
		{
			//Set the Price this job to become any other one
			for(int i = 0; i < prices.size(); i++)
			{
				prices.set(i, newPrice);
			}
		}
		public String toString()
		{
			String ret = "PRICE " + jobCode;
			{
				for(int i = 0; i < prices.size(); i++)
				{
					ret += "," + prices.get(i).toString();
				}
			}
			return ret + ";\r\n";
		}
		private int getJobCode()
		{
			return jobCode;
		}
	}
	public int getAmountOfJobs() 
	{
		return jobs.size();
	}
	public void setFromIndex(int row, int col, int price) 
	{
		jobs.get(row).setPrice(col, price);
	}
}
