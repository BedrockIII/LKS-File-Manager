package ResourceManagers.CharacterDatabaseManager.JobPrice;

public class Price
{
	Job job;
	int price;
	public Price(Job job, int price)
	{
		this.job = job;
		this.price = price;
	}
	public int getPrice()
	{
		return price;
	}
	public Job getJob()
	{
		return job;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
}
