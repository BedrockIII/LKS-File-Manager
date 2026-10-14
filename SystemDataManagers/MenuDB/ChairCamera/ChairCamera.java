package SystemDataManagers.MenuDB.ChairCamera;

import java.nio.ByteBuffer;

import bFM.Data;

public class ChairCamera implements Data
	{
		int num1 = -1;
		int num2 = -1;
		int num3 = -1;
		int num4 = -1;
		int num5 = -1;
		int num6 = -1;
		int num7 = -1;
		int num8 = -1;
		int num9 = -1;
		int num10 = -1;
		int num11 = -1;
		int num12 = -1;
		int num13 = -1;
		public ChairCamera(ByteBuffer data)
		{
			num1 = data.getInt();
			num2 = data.getInt();
			num3 = data.getInt();
			num4 = data.getInt();
			num5 = data.getInt();
			num6 = data.getInt();
			num7 = data.getInt();
			num8 = data.getInt();
			num9 = data.getInt();
			num10 = data.getInt();
			num11 = data.getInt();
			num12 = data.getInt();
			num13 = data.getInt();
		}
		public ChairCamera() 
		{
			// Use Defaults
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
			ByteBuffer ret = ByteBuffer.allocate(52);
			ret.putInt(num1);
			ret.putInt(num2);
			ret.putInt(num3);
			ret.putInt(num4);
			ret.putInt(num5);
			ret.putInt(num6);
			ret.putInt(num7);
			ret.putInt(num8);
			ret.putInt(num9);
			ret.putInt(num10);
			ret.putInt(num11);
			ret.putInt(num12);
			ret.putInt(num1);
			return ret.array();
		}
		public int getSize() 
		{
			return 52;
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
		public int getNum6() {
			return num6;
		}
		public void setNum6(int num6) {
			this.num6 = num6;
		}
		public int getNum7() {
			return num7;
		}
		public void setNum7(int num7) {
			this.num7 = num7;
		}
		public int getNum8() {
			return num8;
		}
		public void setNum8(int num8) {
			this.num8 = num8;
		}
		public int getNum9() {
			return num9;
		}
		public void setNum9(int num9) {
			this.num9 = num9;
		}
		public int getNum10() {
			return num10;
		}
		public void setNum10(int num10) {
			this.num10 = num10;
		}
		public int getNum11() {
			return num11;
		}
		public void setNum11(int num11) {
			this.num11 = num11;
		}
		public int getNum12() {
			return num12;
		}
		public void setNum12(int num12) {
			this.num12 = num12;
		}
		public int getNum13() {
			return num13;
		}
		public void setNum13(int num13) {
			this.num13 = num13;
		}
	}