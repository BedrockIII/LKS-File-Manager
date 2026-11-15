package ResourceManagers.CharacterDatabaseManager.TextureAnimation;

import PCKGManager.PCKGManager;
import ResourceManagers.CharacterDatabaseManager.TextureAnimation.Animation.AnimationList;
import ResourceManagers.CharacterDatabaseManager.TextureAnimation.Pattern.PatternList;
import bFM.OpenedFile;
import bFM.Utils;

public class TextureAnimationList implements OpenedFile
{
	AnimationList animations;
	PatternList patterns;
	public TextureAnimationList(byte[] data)
	{
		PCKGManager pack = new PCKGManager(data);
		animations = new AnimationList(pack.getFile("t00_anm.txt "));
		patterns = new PatternList(pack.getFile("t00_ptn.txt "));
	}
	public TextureAnimationList() 
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
	public AnimationList getAnimations() 
	{
		return animations;
	}
	public PatternList getPatterns() 
	{
		return patterns;
	}
}
