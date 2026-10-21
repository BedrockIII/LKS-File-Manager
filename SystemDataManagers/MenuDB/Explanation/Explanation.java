package SystemDataManagers.MenuDB.Explanation;

import bFM.Data;
import bFM.Utils;

public class Explanation implements Data
	{
		String name = "New Tutorial";
		String text = "New Tutorial";
		String image1 = "";
		String image2 = "";
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		int num4 = 0;
		int num5 = 0;
		public Explanation(String name, String text, String image1, String image2, int num1, int num2, int num3, int num4, int num5)
		{
			this.name = name;
			this.text = text;
			this.image1 = image1;
			this.image2 = image2;
			this.num1 = num1;
			this.num2 = num2;
			this.num3 = num3;
			this.num4 = num4;
			this.num5 = num5;
		}
		public Explanation(String line)
		{
			this.name = Utils.formatString(line);
		}
		public Explanation() 
		{
			// Use Defaults
		}
		public void addLine(String line)
		{
			if(line.indexOf("<<Description>>") != -1)
			{
				text = Utils.formatString(line);
			}
			else if(line.indexOf("<<Image 1>>") != -1)
			{
				image1 = Utils.formatString(line);
			}
			else if(line.indexOf("<<Image 2>>") != -1)
			{
				image2 = Utils.formatString(line);
			}
			else if(line.indexOf("<<Num 1>>") != -1)
			{
				num1 = Utils.formatInt(line);
			}
			else if(line.indexOf("<<Num 2>>") != -1)
			{
				num2 = Utils.formatInt(line);
			}
			else if(line.indexOf("<<Num 3>>") != -1)
			{
				num3 = Utils.formatInt(line);
			}
			else if(line.indexOf("<<Num 4>>") != -1)
			{
				num4 = Utils.formatInt(line);
			}
			else if(line.indexOf("<<Num 5>>") != -1)
			{
				num5 = Utils.formatInt(line);
			}
		}
		public String toString()
		{
			String ret = "<<Humming Entry Name>> \"" + Utils.toFormatedString(name) + "\"\n";
			ret += "\t<<Description>> \"" + Utils.toFormatedString(text) + "\"\n";
			ret += "\t<<Image 1>> \"" + Utils.toFormatedString(image1) + "\"\n";
			ret += "\t<<Image 2>> \"" + Utils.toFormatedString(image2) + "\"\n";
			ret += "\t<<Num 1>> " + num1 + "\n";
			ret += "\t<<Num 2>> " + num2 + "\n";
			ret += "\t<<Num 3>> " + num3 + "\n";
			ret += "\t<<Num 4>> " + num4 + "\n";
			ret += "\t<<Num 5>> " + num5 + "\n";
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
			throw new UnsupportedOperationException("toBytes() should not be called on type " + this.getClass());
		}
		public void setName(String name) 
		{
			this.name = Utils.formatStringChars(name);
		}
		public String getName() 
		{
			return Utils.toFormatedString(name);
		}
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		public void setText(String text) 
		{
			this.text = Utils.formatStringChars(text);
		}
		public String getText() 
		{
			return Utils.toFormatedString(text);
		}
		public void setImage1(String image) 
		{
			this.image1 = Utils.formatStringChars(image);
		}
		public void setImage2(String image) 
		{
			this.image2 = Utils.formatStringChars(image);
		}
		public String getImage1() 
		{
			return Utils.toFormatedString(image1);
		}
		public String getImage2() 
		{
			return Utils.toFormatedString(image2);
		}
		public int getNum1() {
			return num1;
		}
		public void setNum1(int num1) {
			this.num1 = num1;
		}
		public int getNum2() {
			return num2;
		}
		public void setNum2(int num2) {
			this.num2 = num2;
		}
		public int getNum3() {
			return num3;
		}
		public void setNum3(int num3) {
			this.num3 = num3;
		}
		public int getNum4() {
			return num4;
		}
		public void setNum4(int num4) {
			this.num4 = num4;
		}
		public int getNum5() {
			return num5;
		}
		public void setNum5(int num5) {
			this.num5 = num5;
		}
	}