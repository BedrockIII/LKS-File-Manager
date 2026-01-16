package LZ10Manager;


public class LZ10Manager {

	    public class LZ10 : ICompressionAlgorithm, ILzSettings
	    {
	       static readonly LzProperties _lz = new(0x1000, 18, 3);

	        /// <inheritdoc/>
	        public bool LookAhead { get; set; } = true;

	        /// <inheritdoc/>
	        public bool IsMatch(Stream stream, ReadOnlySpan<char> extension = default)
	            => stream.Position + 0x8 < stream.Length && stream.ReadByte() == 0x10 && (stream.ReadUInt24() != 0 || stream.ReadUInt32() != 0) && (stream.ReadUInt8() & 0x80) == 0;

	        /// <inheritdoc/>
	            
	            
	            
	        public int toUInt24(byte[] input, int pos)
	        {
	        	int ret = 0;
	        	for(int i = 0; i<3; i++)
	        	{
	        		ret += input[pos+0];
	        		ret *= 256;
	        	}
	        	return ret
	        }
	        public int toUInt32(byte[] input, int pos)
	        {
	        	int ret = 0;
	        	for(int i = 0; i<4; i++)
	        	{
	        		ret += input[pos+0];
	        		ret *= 256;
	        	}
	        	return ret;
	        }   
	            
	        public void Decompress(byte[] input, int pos, byte[] output)
	        {
	            pos += 1;
	            int uncompressedSize = toUInt24(input, pos);
	            if (uncompressedSize == 0) uncompressedSize = toUInt32();
	            DecompressHeaderless(input, output, uncompressedSize);
	        }

	        /// <inheritdoc/>
	        
	        
	        
	        
	        public void Compress(ReadOnlySpan<byte> source, Stream destination, CompressionLevel level = CompressionLevel.Optimal)
	        {
	            if (source.Length <= 0xFFFFFF)
	            {
	                destination.Write(0x10 | (source.Length << 8));
	            }
	            else
	            {
	                destination.Write(0x10);
	                destination.Write(source.Length);
	            }

	            CompressHeaderless(source, destination, LookAhead, level);
	        }



	        
	        
	        public static void DecompressHeaderless(Stream source, Stream destination, int decomLength)
	        {
	            long endPosition = destination.Position + decomLength;
	            destination.SetLength(endPosition);
	            using LzWindows buffer = new(destination, _lz.WindowsSize);
	            FlagReader flag = new(source, Endian.Big);

	            while (destination.Position + buffer.Position < endPosition)
	            {
	                if (flag.Readbit()) // Compressed
	                {
	                    byte b1 = source.ReadUInt8();
	                    byte b2 = source.ReadUInt8();
	                    int distance = ((b1 & 0xf) << 8 | b2) + 1;
	                    int length = (b1 >> 4) + 3;
	                    buffer.BackCopy(distance, length);
	                }
	                else // Not compressed
	                {
	                    buffer.WriteByte(source.ReadUInt8());
	                }
	            }

	            if (destination.Position + buffer.Position > endPosition)
	            {
	                throw new DecompressedSizeException(decomLength, destination.Position + buffer.Position - (endPosition - decomLength));
	            }
	        }


	        		
	        		
	        		
	        		
	        public static void CompressHeaderless(ReadOnlySpan<byte> source, Stream destination, bool lookAhead = true, CompressionLevel level = CompressionLevel.Optimal)
	        {
	            int sourcePointer = 0x0;
	            LzMatchFinder dictionary = new(_lz, lookAhead, level);
	            using FlagWriter flag = new(destination, Endian.Big);

	            while (sourcePointer < source.Length)
	            {
	                // Search for a match
	                if (dictionary.TryToFindMatch(source, sourcePointer, out LzMatch match))
	                {
	                    flag.Buffer.Write((ushort)((match.Length - 3) << 12 | ((match.Distance - 1) & 0xFFF)), Endian.Big);
	                    sourcePointer += match.Length;
	                    flag.WriteBit(true);
	                }
	                else
	                {
	                    flag.Buffer.WriteByte(source[sourcePointer++]);
	                    flag.WriteBit(false);
	                }
	            }
	        }
	    }
	}
}
