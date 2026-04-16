package LZ10Convertor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class LZ10Manager 
{
	public static byte[] compress(byte[] toData)
	{
		//byte[] data = LZ10Compressor.compress(toData);
		////data = Arrays.copyOf(data, data.length-4);
		//byte[] header = new byte[4];
		//header[0] = 0x10;
		//byte[] size = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(toData.length).array();
		//header[1] = size[0];
		//header[2] = size[1];
		//header[3] = size[2];
		return LZ10Compressor.compress(toData);
	}
	public static byte[] decompress(byte[] data)
	{
		return LZ10Decompressor.decompress(data);
	}
	public static byte[] decompress(String path)
	{
		try {
			return decompress(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) 
		{
			System.out.println("Could not read file");
			return null;
		}
	}
}
