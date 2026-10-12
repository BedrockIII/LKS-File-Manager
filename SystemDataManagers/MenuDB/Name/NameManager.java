package SystemDataManagers.MenuDB.Name;

import PCKGManager.PCKGManager;
import bFM.OpenedFile;

public class NameManager implements OpenedFile
{
	public static final String FileName = "Name.bin";
	NameList AutoFirstName;
	NameList AutoLastName;
	NameList AutoNGName;
	Language Japanese;
	Language English;
	Language French;
	Language German;
	Language Italian;
	Language Spanish;
	public NameManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager file = new PCKGManager(data);
		AutoFirstName = new NameList(file.getFile("AutoFirstNmae"));
		AutoLastName = new NameList(file.getFile("AutoLastNmae"));
		AutoNGName = new NameList(file.getFile("NGList"));
		Japanese = new Language(file.getFile("JapanInput0"), 
				file.getFile("JapanInput1"), 
				file.getFile("JapanInput2"));
		English = new Language(file.getFile("EnglishInput0"), 
				file.getFile("EnglishInput1"), 
				file.getFile("EnglishInput2"));
		French = new Language(file.getFile("FrenchInput0"), 
				file.getFile("FrenchInput1"), 
				file.getFile("FrenchInput2"));
		German = new Language(file.getFile("GermanInput0"), 
				file.getFile("GermanInput1"), 
				file.getFile("GermanInput2"));
		Italian = new Language(file.getFile("ItarianInput0"), 
				file.getFile("ItarianInput1"), 
				file.getFile("ItarianInput2"));
		Spanish = new Language(file.getFile("SpanishInput0"), 
				file.getFile("SpanishInput1"), 
				file.getFile("SpanishInput2"));
	}
	public boolean equals(String name) 
	{
		System.err.println("equals() should not be called on type " + this.getClass());
		return name.equals(FileName);
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return FileName;
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		PCKGManager ret = new PCKGManager();
		ret.addFile("AutoFirstNmae", AutoFirstName.toBytes());
		ret.addFile("AutoLastNmae", AutoLastName.toBytes());
		ret.addFile("NGList", AutoNGName.toBytes());
		ret.addFile("JapaneseInput0", Japanese.Capitals.toBytes());
		ret.addFile("JapaneseInput1", Japanese.Lowercase.toBytes());
		ret.addFile("JapaneseInput2", Japanese.Special.toBytes());
		ret.addFile("EnglishInput0", English.Capitals.toBytes());
		ret.addFile("EnglishInput1", English.Lowercase.toBytes());
		ret.addFile("EnglishInput2", English.Special.toBytes());
		ret.addFile("FrenchInput0", French.Capitals.toBytes());
		ret.addFile("FrenchInput1", French.Lowercase.toBytes());
		ret.addFile("FrenchInput2", French.Special.toBytes());
		ret.addFile("GermanInput0", German.Capitals.toBytes());
		ret.addFile("GermanInput1", German.Lowercase.toBytes());
		ret.addFile("GermanInput2", German.Special.toBytes());
		ret.addFile("ItarianInput0", Italian.Capitals.toBytes());
		ret.addFile("ItarianInput1", Italian.Lowercase.toBytes());
		ret.addFile("ItarianInput2", Italian.Special.toBytes());
		ret.addFile("SpanishInput0", Spanish.Capitals.toBytes());
		ret.addFile("SpanishInput1", Spanish.Lowercase.toBytes());
		ret.addFile("SpanishInput2", Spanish.Special.toBytes());
		return ret.getFile();
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	public NameList getFirstNames()
	{
		return AutoFirstName;
	}
	public NameList getLastNames()
	{
		return AutoLastName;
	}
	public NameList getNGName()
	{
		return AutoNGName;
	}
	public Language getJapanese()
	{
		return Japanese;
	}
	public Language getEnglish()
	{
		return English;
	}
	public Language getFrench()
	{
		return French;
	}
	public Language getGerman()
	{
		return German;
	}
	public Language getItalian()
	{
		return Italian;
	}
	public Language getSpanish()
	{
		return Spanish;
	}
}
