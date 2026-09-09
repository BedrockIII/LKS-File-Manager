package bFM;

public interface Nameable
{
	//Used for every file type and subfile type
	//Not implemented for most...
	boolean equals(String name);

	void setName(String name);

	String getName();

}