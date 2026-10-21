package SystemDataManagers.MenuDB.Unused.Development;

import java.nio.ByteBuffer;

import bFM.Data;

public class Development implements Data
	{
		String icon = "Unused";
		String image = "X8_Y8";
		int buildingCode;//2byte
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public int getBuildingCode() {
			return buildingCode;
		}
		public void setBuildingCode(int buildingCode) {
			this.buildingCode = buildingCode;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getxPos() {
			return xPos;
		}
		public void setxPos(int xPos) {
			this.xPos = xPos;
		}
		public int getyPos() {
			return yPos;
		}
		public void setyPos(int yPos) {
			this.yPos = yPos;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		int id;//2byte
		int xPos;//2byte
		int yPos;//2byte
		public Development(String icon, String image, int buildingCode, int id, int xPos, int yPos)
		{
			this.icon = icon;
			this.image = image;
			this.buildingCode = buildingCode;
			this.id = id;
			this.xPos = xPos;
			this.yPos = yPos;
		}
		public Development() 
		{
			// Use Defaults
		}
		protected byte[] getPosBytes()
		{
			if(id == -1) return new byte[0];
			ByteBuffer ret = ByteBuffer.allocate(6);
			ret.putShort((short)id);
			ret.putShort((short)xPos);
			ret.putShort((short)yPos);
			return ret.array();
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
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		public String getIcon()
		{
			return icon;
		}
	}