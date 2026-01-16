package LZConverter;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JavaDSDecmp {

	public static int[] Decompress(HexInputStream his, String outputName) throws IOException {
		switch(his.readU8()){
		default: return Decompress10LZ(his, outputName);
		}
	}
	
	private static int getLength(HexInputStream his) throws IOException {
		int length = 0;
		for(int i = 0; i < 3; i++)
			length = length | (his.readU8() << (i * 8));
		if(length == 0) // 0 length? then length is next 4 bytes
			length = his.readlS32();
		return length;
	}
	
	
	private static int[] Decompress10LZ(HexInputStream his, String outputName) throws IOException {
		int[] outData = new int[getLength(his)];
		int curr_size = 0;
		int flags;
		boolean flag;
		int disp, n, b, cdest;
		
		while (curr_size < outData.length)
        {
            try { flags = his.readU8(); }
            catch (EOFException ex) { break; }
            for (int i = 0; i < 8; i++)
            {
                flag = (flags & (0x80 >> i)) > 0;
                if (flag)
                {
                    disp = 0;
                    try { b = his.readU8(); }
                    catch (EOFException ex) { throw new InvalidFileException("Incomplete data"); }
                    n = b >> 4;
                    disp = (b & 0x0F) << 8;
                    try { disp |= his.readU8(); }
                    catch (EOFException ex) { throw new InvalidFileException("Incomplete data"); }
                    n += 3;
                    cdest = curr_size;
                    if (disp > curr_size)
                        throw new InvalidFileException("Cannot go back more than already written");
                    for (int j = 0; j < n; j++)
                    	outData[curr_size++] = outData[cdest - disp - 1 + j];
                    
                    if (curr_size > outData.length)
                        break;
                }
                else
                {
                    try { b = his.readU8(); }
                    catch (EOFException ex) { break;}// throw new Exception("Incomplete data"); }
                    try { outData[curr_size++] = b; }
                    catch (ArrayIndexOutOfBoundsException ex) { if (b == 0) break; }

                    if (curr_size > outData.length)
                        break;
                }
            }

        }
		ByteBuffer byteBuffer = ByteBuffer.allocate(outData.length * 4);        
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(outData);
		byte[] array = byteBuffer.array();
        byte[] arr2 = new byte[array.length/4];
        for(int i = 1; i<arr2.length; i++)
        {
        	arr2[i-1] = array[i*4-1];
        }
		Files.write(Paths.get(outputName), arr2);
        return outData;
	}
}
