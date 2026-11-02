package ResourceManagers.CharacterDatabaseManager.JobPrice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ResourceManagers.CharacterDatabaseManager.CharacterDataBaseManager;
import bFM.OpenedFile;
import bFM.Utils;

public class JobChangePriceList implements OpenedFile
{
	public CharacterDataBaseManager parent = null;
	ArrayList<Job> jobs = new ArrayList<Job>();
	public JobChangePriceList(byte[] file, CharacterDataBaseManager parent) 
	{
		this.parent = parent;
		String data = Utils.decodeBytesToString(file);
		String[] lines = data.split("\n");
		//Skip First Line
		initializeFromLines(Arrays.asList(lines));
	}
	public JobChangePriceList(List<String> lines, CharacterDataBaseManager parent) 
	{
		this.parent = parent;
		initializeFromLines(lines);
	}
	protected JobChangePriceList(CharacterDataBaseManager parent) 
	{
		this.parent = parent;
	}
	private void initializeFromLines(List<String> lines)
	{
		//Initialize From Lines
		for(String line : lines)
		{
			if(line.indexOf("PRICE")!=-1) jobs.add(new Job(this, Utils.formatInt(line.split(",")[0])));
		}
		for(String line : lines)
		{
			if(line.indexOf("PRICE")!=-1)
			{
				//Do this seperately so all jobs have been constructed
				getJob(Utils.formatInt(line.split(",")[0])).addPrices(line);
			}
		}
	}
	Job getJob(int code)
	{
		//Get Job with the matching job code
		for(Job job : jobs)
		{
			if(job.jobCode == code)
			{
				return job;
			}
		}
		throw new IllegalArgumentException("Job Code " + code + " not found!");
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
		return "JobChangePrice.cfg";
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	public ArrayList<Job> getJobs()
	{
		return jobs;
	}
	public void registerJob(Job newJob)
	{
		// Add New Job to list and add prices to all children
		jobs.add(newJob);
		for(Job job : jobs)
		{
			job.registerJob(newJob);
		}
	}
	public void unregisterJob(Job oldJob)
	{
		// Delete Job
		for(Job job : jobs)
		{
			job.unregisterJob(oldJob);
		}
		jobs.remove(oldJob);
	}
}
