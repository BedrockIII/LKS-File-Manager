package LZ10Convertor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

class LZ10Compressor 
{
	 public static byte[] compress(byte[] input) {
	        int pos = 0;
	        int size = input.length;

	        ByteArrayOutputStream out = new ByteArrayOutputStream();

	        // --- 1. Write LZ10 header ---
	        out.write(0x10);             // LZ10 marker
	        out.write(size & 0xFF);      // file size, little-endian
	        out.write((size >> 8) & 0xFF);
	        out.write((size >> 16) & 0xFF);

	        // --- 2. Compression loop ---
	        while (pos < input.length) {
	            int flagByte = 0;
	            ByteArrayOutputStream blockData = new ByteArrayOutputStream();

	            // Process up to 8 blocks per flag byte
	            for (int i = 0; i < 8 && pos < input.length; i++) {
	                // Find the best match at current position
	                Match match = findBestMatch(input, pos);

	                if (match.length >= 3) {
	                    // Mark as compressed
	                    flagByte |= (1 << (7 - i));

	                    // Encode length and offset
	                    int lengthField = match.length - 3;
	                    int dispField = ((lengthField & 0xF) << 12) | ((match.offset-1) & 0xFFF);

	                    blockData.write((dispField >> 8) & 0xFF);
	                    blockData.write(dispField & 0xFF);

	                    pos += match.length; // advance by match length
	                } else {
	                    // No match, write raw byte
	                    blockData.write(input[pos] & 0xFF);
	                    pos++;
	                }
	            }

	            // Write flag byte first, then the block data
	            out.write(flagByte & 0xFF);
	            try {
					out.write(blockData.toByteArray());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }

	        return out.toByteArray();
	    }

	    // Simple placeholder; replace with your actual match-finding logic
	    private static Match findBestMatch(byte[] input, int pos) {
	        // Find the longest previous match up to 0xFFF bytes back
	        int maxLength = 0;
	        int bestOffset = 0;

	        int startSearch = Math.max(0, pos - 0xFFF);

	        for (int j = startSearch; j < pos; j++) {
	            int length = 0;
	            while (length < 18 && pos + length < input.length
	                    && input[j + length] == input[pos + length]) {
	                length++;
	            }
	            if (length > maxLength) {
	                maxLength = length;
	                bestOffset = pos - j;
	            }
	        }

	        return new Match(maxLength, bestOffset);
	    }

	    private static class Match {
	        int length;
	        int offset;

	        Match(int length, int offset) {
	            this.length = length;
	            this.offset = offset;
	        }
	    }
}
