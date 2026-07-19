package ResourceManagers.CharacterDatabaseManager;

import java.util.ArrayList;
import java.util.List;
import PCKGManager.PCKGManager;
import bFM.Data;
import bFM.OpenedFile;
import bFM.Utils;

public class TextAnimationList implements OpenedFile
{
	AnimationList animations;
	PatternList patterns;
	public TextAnimationList(byte[] data)
	{
		PCKGManager pack = new PCKGManager(data);
		animations = new AnimationList(pack.getFile("t00_anm.txt "));
		patterns = new PatternList(pack.getFile("t00_ptn.txt "));
	}
	public TextAnimationList() 
	{
		animations = new AnimationList();
		patterns = new PatternList();
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
		PCKGManager pack = new PCKGManager("texanim.bin");
		pack.addFile("t00_anm.txt ", Utils.encodeStringToBytes(animations.toString()));
		pack.addFile("t00_ptn.txt ", Utils.encodeStringToBytes(patterns.toString()));
		return pack.toBytes();
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return "Character Face Animation";
	}
	public int getSize() 
	{
		return 0;
	}
	public class AnimationList implements Data
	{
		ArrayList<Animation> animations = new ArrayList<Animation>();
		public AnimationList(byte[] data) 
		{
			List<String> lines = bFM.Utils.bytesToStrs(data);
			Animation animation = null;
			for(String line : lines)
			{
				if(line.indexOf("NAME") != -1)
				{
					animation = new Animation(line);
					animations.add(animation);
				}
				else if(line.indexOf("ANIMCOUNT") != -1)
				{
					//Ignore
				}
				else
				{
					animation.addLine(line);
				}
			}
		}
		public AnimationList() 
		{
			animations = new ArrayList<Animation>();
		}
		public String toString()
		{
			String ret = "ANIMCOUNT " + animations.size() + "\r\n";
			for(Animation animation : animations)
			{
				ret += animation.toString();
			}
			ret += "END\r\n";
			return ret;
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
			throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
		}
		public void setName(String name) 
		{
			throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
		}
		public String getName() 
		{
			return "Animation Data";
		}
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		public ArrayList<Animation> getAnimations() 
		{
			return animations;
		}
	}
	public class Animation implements Data
	{
		String name = "";
		Part part = null;
		public Animation(String line) 
		{
			name = line.substring(line.indexOf(' ') + 1);
		}
		public void addLine(String line) 
		{
			if(line.indexOf("PARTS") != -1)
			{
				part = new Part(line.substring(line.lastIndexOf(' ') + 1));
			}
			else if(line.indexOf("PATTERN ") != -1)
			{
				part.addPattern(line);
			}
		}
		public String toString()
		{
			String ret = "NAME " + name + "\r\n";
			ret += part.toString();
			return ret;
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
			throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
		}
		public void setName(String name) 
		{
			this.name = name;
		}
		public String getName() 
		{
			return name;
		}
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		public Part getPart() 
		{
			return part;
		}
	}
	public class Part implements Data
	{
		String name = "Part";
		ArrayList<AnimationPattern> patterns = new ArrayList<AnimationPattern>();
		public Part(String name) 
		{
			this.name = name;
		}
		public void addPattern(String Pattern) 
		{
			if(Pattern.indexOf("LOOP") != -1) return;
			patterns.add(new AnimationPattern(Pattern));
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
			throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
		}
		public void setName(String name) 
		{
			this.name = name;
		}
		public String getName() 
		{
			return name;
		}
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		public String toString()
		{
			String ret = "  PARTS " + name + "\r\n";
			ret += "  PATTERN_NUM " + (patterns.size() + 1) + "\r\n";
			for(AnimationPattern pattern : patterns)
			{//4 Spaces
				ret += "    " + pattern.toString();
			}
			ret += "    PATTERN LOOP  \r\n";
			return ret;
		}
		public ArrayList<AnimationPattern> getPatterns() 
		{
			return patterns;
		}
	}
	public class AnimationPattern implements Data
	{
		String name = "Ptn";
		int num1 = 0;
		int num2 = 0;
		private AnimationPattern(String data)
		{
			String[] vals = data.substring(data.indexOf("N ") + 2).split(" ");
			name = vals[0];
			num1 = Integer.parseInt(vals[1]);
			num2 = Integer.parseInt(vals[2]);
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
			throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
		}
		public void setName(String name) 
		{
			this.name = name;
		}
		public String getName() 
		{
			return name;
		}
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		public String toString()
		{
			return "PATTERN " + name + " " + num1 + " " + num2 + "\r\n";
		}
		public int getNum1() 
		{
			return num1;
		}
		public void setNum1(int num1)
		{
			this.num1 = num1;
		}

		public int getNum2() 
		{
			return num2;
		}
		public void setNum2(int num2)
		{
			this.num2 = num2;
		}
	}
	public class PatternList implements Data
	{
		ArrayList<PatternPart> Patterns = new ArrayList<PatternPart>();
		public PatternList(byte[] data) 
		{
			List<String> lines = bFM.Utils.bytesToStrs(data);
			PatternPart Pattern = null;
			for(String line : lines)
			{
				if(line.indexOf("NAME") != -1)
				{
					Pattern = new PatternPart(line);
					Patterns.add(Pattern);
				}
				else if(line.indexOf("PARTSCOUNT") != -1)
				{
					//Ignore
				}
				else
				{
					Pattern.addLine(line);
				}
			}
		}
		public PatternList() 
		{
			Patterns = new ArrayList<PatternPart>();
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
			throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
		}
		public void setName(String name) 
		{
			throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
		}
		public String getName() 
		{
			return "Pattern Data";
		}
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		public String toString()
		{
			int partsCount = 0;
			for(PatternPart p : Patterns)
			{
				partsCount += p.getPartsCount();
			}
			String ret = "PARTSCOUNT " + partsCount + "\r\n";
			for(PatternPart p : Patterns)
			{
				ret += p.toString();
			}
			ret += "END\r\n";
			return ret;
		}
		public ArrayList<PatternPart> getPatterns() 
		{
			return Patterns;
		}
	}
	public class PatternPart implements Data
	{
		ArrayList<Part2> Parts = new ArrayList<Part2>();
		Part2 lastPart;
		String name;
		public PatternPart(String line) 
		{
			name = line.substring(line.indexOf(' ') + 1);
		}
		public void addLine(String line) 
		{
			if(line.indexOf("PARTS") != -1)
			{
				lastPart = new Part2(line);
				Parts.add(lastPart);
			}
			else
			{
				lastPart.addLine(line);
			}
		}
		public String toString()
		{
			String ret = "NAME " + name + "\r\n";
			for(Part2 part : Parts)
			{
				ret += part.toString();
			}
			return ret;
		}
		public int getPartsCount() 
		{
			return Parts.size();
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
			throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
		}
		public void setName(String name) 
		{
			this.name = name;
		}
		public String getName() 
		{
			return name;
		}
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		public ArrayList<Part2> getParts() 
		{
			return Parts;
		}
	}
	public class Part2 implements Data
	{
		String name = "Part";
		Material mat;
		ArrayList<Pattern> patterns = new ArrayList<Pattern>();
		public Part2(String line) 
		{
			name = line.substring(line.indexOf("S ") + 2);
		}
		public void addLine(String line) 
		{
			if(line.indexOf("MATERIAL ") != -1)
			{
				mat = new Material(line);
			}
			else if(line.indexOf("WIDTH ") != -1)
			{
				mat.addLine(line);
			}
			else if(line.indexOf("HEIGHT ") != -1)
			{
				mat.addLine(line);
			}
			else if(line.indexOf("PATTERN ") != -1)
			{
				patterns.add(new Pattern(line));
			}
		}
		public String toString()
		{
			String ret = "  PARTS " + name + "\r\n";
			ret += mat.toString();
			ret += "    PATTERN_NUM " + patterns.size() + "\r\n";
			for(Pattern p : patterns)
			{
				ret += p.toString();
			}
			return ret;
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
			throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
		}
		public void setName(String name) 
		{
			this.name = name;
		}
		public String getName() 
		{
			return name;
		}
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		public Material getMaterial() 
		{
			return mat;
		}
		public ArrayList<Pattern> getPatterns() 
		{
			return patterns;
		}
	}
	public class Material implements Data
	{
		String name = "Material";
		int width = -1;
		int height = -1;
		public Material(String line) 
		{
			name = line.substring(line.indexOf("L ") + 2);
		}
		public void addLine(String line) 
		{
			if(line.indexOf("WIDTH ") != -1)
			{
				width = bFM.Utils.strToInt(line);
			}
			else if(line.indexOf("HEIGHT ") != -1)
			{
				height = bFM.Utils.strToInt(line);
			}
		}
		public String toString()
		{
			String ret = "   MATERIAL " + name + "\r\n";
			ret += "   WIDTH     " + width + "\r\n";
			ret += "   HEIGHT    " + height + "\r\n";
			return ret;
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
			throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
		}
		public void setName(String name) 
		{
			this.name = name;
		}
		public String getName() 
		{
			return name;
		}
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		public int getWidth() 
		{
			return width;
		}
		public void setWidth(int width)
		{
			this.width = width;
		}

		public int getHeight() 
		{
			return height;
		}
		public void setHeight(int height)
		{
			this.height = height;
		}
	}
	public class Pattern implements Data
	{
		int index;
		String name = "Pattern";
		int num1;
		int num2;
		public Pattern(String line) 
		{
			String[] data = line.substring(line.indexOf("N ") + 2).split(" ");
			index = bFM.Utils.strToInt(data[0]);
			name = data[1];
			num1 = bFM.Utils.strToInt(data[2]);
			num2 = bFM.Utils.strToInt(data[3]);
		}
		public String toString()
		{
			return "      PATTERN " + index + " " + name + " " + num1 + " " + num2 + "\r\n";
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
			throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
		}
		public void setName(String name) 
		{
			this.name = name;
		}
		public String getName() 
		{
			return name;
		}
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		public int getIndex() 
		{
			return index;
		}
		public void setIndex(int index)
		{
			this.index = index;
		}
		public int getNum1() 
		{
			return num1;
		}
		public void setNum1(int num1)
		{
			this.num1 = num1;
		}

		public int getNum2() 
		{
			return num2;
		}
		public void setNum2(int num2)
		{
			this.num2 = num2;
		}
	}
	public AnimationList getAnimations() 
	{
		return animations;
	}
	public PatternList getPatterns() 
	{
		return patterns;
	}
}
