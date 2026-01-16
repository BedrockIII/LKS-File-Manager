package mapDBManager;

public class SubDManager 
{
	int code = -1;
	int num0 = 1;
	int num1 = 5;
	int num2 = 1;
	public SubDManager(String[] line) 
	{
		code = Integer.valueOf(line[0]).intValue();
		num0 = Integer.valueOf(line[1]).intValue();
		num1 = Integer.valueOf(line[2]).intValue();
		num2 = Integer.valueOf(line[3]).intValue();
	}
	public String toString()
	{
		return "SUBD " + code + "," + num0 + "," + num1 + ","+ num2 +";\r\n";
	}
}
