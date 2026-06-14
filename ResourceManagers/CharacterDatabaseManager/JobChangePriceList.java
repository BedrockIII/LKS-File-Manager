package ResourceManagers.CharacterDatabaseManager;

import java.util.ArrayList;
import java.util.List;

import ResourceManagers.CharacterDatabaseManager.indBinList.ind;
import bFM.Data;
import bFM.GenericFile;

public class JobChangePriceList extends GenericFile
{
	private CharacterDataBaseManager parent = null;
	private ArrayList<JobPrices> jobs = new ArrayList<JobPrices>();
	protected JobChangePriceList(byte[] file, CharacterDataBaseManager parent) 
	{
		this.parent = parent;
		name = "Job Change Price List";
		String data = new String(file);
		String[] lines = data.split("\n");
		//Skip First Line
		for(String line : lines)
		{
			if(line.indexOf("PRICE")!=-1) jobs.add(new JobPrices(line, this));
		}
	}
	protected JobChangePriceList(List<String> lines, CharacterDataBaseManager parent) 
	{
		this.parent = parent;
		name = "Job Change Price List";
		for(String line : lines)
		{
			if(line.indexOf("PRICE")!=-1) jobs.add(new JobPrices(line, this));
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
	public class JobPrices implements Data
	{
		JobChangePriceList parentList;
		ind index;
		int jobCode = -1;
		ArrayList<Integer> prices = new ArrayList<Integer>();
		private JobPrices(String line, JobChangePriceList parent2) 
		{
			parentList = parent2;
			
			if(line.indexOf("PRICE ")==-1) return;
			String[] numbers = line.split(",");
			jobCode = Integer.parseInt(numbers[0].substring(6 + numbers[0].indexOf("PRICE ")));
			index = parent.getCharacterIndex(jobCode);
			//Skip First Part
			for(int i = 1; i < numbers.length; i++)
			{
				prices.add(bFM.Utils.strToInt(numbers[i]));
			}
		}
		public JobPrices(int jobCode, int jobCount, JobChangePriceList parent) 
		{
			parentList = parent;
			index = parentList.parent.getCharacterIndex(jobCode);
			this.jobCode = jobCode;
			prices = new ArrayList<Integer>();
			for(int i = 0; i < jobCount; i++)
			{
				prices.add(-1);
			}
		}
		public void setPrice(int index, int newPrice)
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
		public int getPriceAmount()
		{
			return prices.size();
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
		public int getJobCode()
		{
			return jobCode;
		}
		public boolean equals(String name) 
		{
			throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
		}
		public void setData(byte[] data) 
		{
			throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
		}
		public byte[] toBytes() 
		{
			throw new UnsupportedOperationException("getData() should not be called on type " + this.getClass());
		}
		public void setName(String name) 
		{
			throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
		}
		public String getName() 
		{
			return parent.getNameByCode(jobCode) + " (" + jobCode + ")";
		}
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		public String getNameByIndex(int index)
		{
			return parentList.getNameByIndex(index);
		}
		public int getPrice(int index) 
		{
			return prices.get(index);
		}
		public void removeJob(int index) 
{
			prices.remove(index);
		}
		public void setCode(int code) 
		{
			jobCode = code;
			index.setJobCode(code);
		}
		public void addPrice(int newPrice) 
		{
			prices.add(newPrice);
		}
		public void updateCode()
		{
			jobCode = index.getJobCode();
		}
	}
	public int getAmountOfJobs() 
	{
		return jobs.size();
	}
	public String getNameByIndex(int index) 
	{
		return jobs.get(index).getName();
	}
	public void setFromIndex(int row, int col, int price) 
	{
		jobs.get(row).setPrice(col, price);
	}
	public ArrayList<JobPrices> getObjects() 
	{
		return jobs;
	}
	public void removePrice(JobPrices file) 
	{
		int index = indexOf(file.jobCode);
		for(JobPrices job : jobs)
		{
			job.removeJob(index);
		}
		jobs.remove(index);
	}
	public void addPrice(int newPrice) 
	{
		for(JobPrices job : jobs)
		{
			job.addPrice(newPrice);
		}
	}
	public void addJob(int jobCode) 
	{
		jobs.add(new JobPrices(jobCode, jobs.size()+1, this));
	}
	public JobPrices getLastObject() 
	{
		return jobs.get(jobs.size()-1);
	}
}
