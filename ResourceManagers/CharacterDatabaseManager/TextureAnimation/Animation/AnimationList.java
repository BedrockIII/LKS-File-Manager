package ResourceManagers.CharacterDatabaseManager.TextureAnimation.Animation;

import java.util.ArrayList;
import java.util.List;

import bFM.Data;
import bFM.Nameable;

public class AnimationList implements Data, Nameable
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
				if(animation!=null)animation.addLine(line);
				else System.err.println("Error Importing Animation: AnimationList constructor, " + this.getClass());
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