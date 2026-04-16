package LZ10Convertor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class LZ10Decompressor 
{
	protected static byte[] decompress(byte[] data)
	{
		
		//Ported from ndspy
		
		if(data[0]!= 0x10)
		{
			System.out.println("This isn't the right type of file");
			return data;
		}
		ByteBuffer data2 = ByteBuffer.wrap(data);
		data2.order(ByteOrder.LITTLE_ENDIAN);
		int dataLength = data2.getInt(0)>>>8;
		
		
		byte[] ret = new byte[dataLength];
		int inPos = 4;
		int outPos = 0;
		boolean first = true;
		while(dataLength>0)
		{
			int d = data[inPos] & 0xFF;
			inPos++;
			
			if(d != 0)
			{
				for(int i = 0; i < 8; i++)
				{
					if((d & 0x80) != 0)
					{

						int b1 = data[inPos]& 0xFF;
						int b2 = data[inPos + 1] & 0xFF;
						inPos += 2;
						
						int length = (b1 >> 4) + 3;
						int offset = ((b1 & 0xF) << 8) | b2;;
						int windowOffset = outPos - offset - 1;
						
						for(int j = 0; j < length; j++)
						{
							ret[outPos++] = ret[windowOffset++];
							dataLength--;
							
							if(dataLength == 0)
							{
								return ret;
							}
						}
					}
					else
					{
						ret[outPos++] = data[inPos++];
						dataLength--;
						
						if (dataLength == 0)
						{
							return ret;
						}
					}
					
					d <<= 1;
				}
			}
			else
			{
				for(int i = 0; i < 8; i++)
				{
					ret[outPos++] = data[inPos++];
					dataLength--;
					
					if (dataLength == 0)
					{
						return ret;
					}
				}
			}
		}
		
		return ret;
	}
}
