package lifeSimManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class lifeSimConverter {

	public static void main(String[] args) 
	{
		try {
			converter test = new converter(Files.readAllLines(Paths.get("D:\\LKS Debug!!!!1\\ROMs\\Extracted\\lifesim\\dikstra.pac\\Dikstra10084.cfg")));
			ArrayList<String> a = test.toOBJ();
			String b = "";
			for(int i = 0; i<a.size(); i++)
			{
				b += a.get(i) + "\n";
			}
			Files.write(Paths.get("C:\\Users\\notgonnadoxmyselfnothanks\\OneDrive\\Documents\\BigACastle.obj"),b.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
