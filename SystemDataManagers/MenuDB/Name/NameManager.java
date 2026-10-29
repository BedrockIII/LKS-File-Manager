package SystemDataManagers.MenuDB.Name;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import CommonFiles.PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Settings;

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
		AutoFirstName = new NameList(file.getFile("AutoFirstNmae"), Settings.getCharset());
		AutoLastName = new NameList(file.getFile("AutoLastNmae"), Settings.getCharset());
		AutoNGName = new NameList(file.getFile("NGlist"), Charset.forName("Shift-JIS"));
		Japanese = new Language(file.getFile("JapanInput0"), 
				file.getFile("JapanInput1"), 
				file.getFile("JapanInput2"), Charset.forName("Shift-JIS"));
		English = new Language(file.getFile("EnglishInput0"), 
				file.getFile("EnglishInput1"), 
				file.getFile("EnglishInput2"), StandardCharsets.UTF_8);
		French = new Language(file.getFile("FrenchInput0"), 
				file.getFile("FrenchInput1"), 
				file.getFile("FrenchInput2"), StandardCharsets.UTF_8);
		German = new Language(file.getFile("GermanInput0"), 
				file.getFile("GermanInput1"), 
				file.getFile("GermanInput2"), StandardCharsets.UTF_8);
		Italian = new Language(file.getFile("ItarianInput0"), 
				file.getFile("ItarianInput1"), 
				file.getFile("ItarianInput2"), StandardCharsets.UTF_8);
		Spanish = new Language(file.getFile("SpanishInput0"), 
				file.getFile("SpanishInput1"), 
				file.getFile("SpanishInput2"), StandardCharsets.UTF_8);
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
		ret.addFile("NGlist", AutoNGName.toBytes());
		ret.addFile("JapanInput0", Japanese.Capitals.toBytes());
		ret.addFile("JapanInput1", Japanese.Lowercase.toBytes());
		ret.addFile("JapanInput2", Japanese.Special.toBytes());
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
		return toBytes().length;
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
