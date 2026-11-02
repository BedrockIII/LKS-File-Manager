package ResourceManagers.CharacterDatabaseManager.JobPrice;

import java.util.ArrayList;

import bFM.Data;
import bFM.Utils;

public class Job implements Data
	{
		/**
		 * 
		 */
		private final JobChangePriceList parent;
		int jobCode = -1;
		ArrayList<Price> prices = new ArrayList<Price>();
		Job(JobChangePriceList jobChangePriceList, int code) 
		{
			//Create From Int
			this.parent = jobChangePriceList;
			jobCode = code;
		}
		void addPrices(String line)
		{
			//Add Prices from CSV
			if(line.indexOf("PRICE ")==-1) return;
			String[] numbers = line.split(",");
			
			for(int i = 1; i < numbers.length; i++)
			{
				prices.add(new Price(parent.jobs.get(i - 1), Utils.strToInt(numbers[i])));
			}
		}
		public Price getPrice(int code)
		{
			Job j = parent.getJob(code);
			for(Price price : prices)
			{
				if(price.job == j)
				{
					return price;
				}
			}
			throw new IllegalArgumentException("Job Code " + code + " not found!");
		}
		public Job(JobChangePriceList parent, int jobCode, int jobCount) 
		{
			//Create a blank job
			this.parent = parent;
			this.jobCode = jobCode;
			for(int i = 0; i < jobCount; i++)
			{
				prices.add(new Price(parent.jobs.get(i - 1), -1));
			}
		}
		public void setPrice(int jobCode, int newPrice)
		{
			//Set the Price for a specific job to become this job
			//Legacy
			getPrice(jobCode).price = newPrice;
		}
		void setAllPrices(int newPrice)
		{
			//Set the Price this job to become any other one
			for(Price price : prices)
			{
				price.price = newPrice;
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
		public String getName() 
		{
			return this.parent.parent.getNameByCode(jobCode) + " (" + jobCode + ")";
		}
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		public void removeJob(int index) 
{
			prices.remove(index);
		}
		public void setJobCode(int code) 
		{
			jobCode = code;
		}
		void addPrice(int jobCode, int newPrice) 
		{
			prices.add(new Price(parent.getJob(jobCode), newPrice));
		}
		public void registerJob(Job newJob)
		{
			prices.add(new Price(newJob, -1));
		}
		public void unregisterJob(Job oldJob)
		{
			for(int i = 0; i < prices.size(); i++)
			{
				if(prices.get(i).job.equals(oldJob))
				{
					prices.remove(i);
					i--;
				}
			}
		}
		public ArrayList<Price> getPrices()
		{
			return prices;
		}
	}