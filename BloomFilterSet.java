import java.util.BitSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BloomFilterSet implements Set {

	/*
	 * the best way to improve this data structure was to alter m, the number of bits contained by the encapsulated bit set
	 * I currently have it set to what I found to be the optimal number of bits after several iterations of testing
	 */	
	static int m = 65001;
	BitSet data;
	
	public BloomFilterSet() {
		data = new BitSet(m);
	}
	
	@Override
	public void add(String element) {
		BitSet mask = new BitSet(m);
		
		mask.set(h1(element)%m);
		mask.set(h2(element)%m);
		mask.set(h3(element)%m);
		// this marks a call to h4 when it was being considered as an option
		//mask.set(h4(element)%m);
		data.or(mask);
		
	}

	@Override
	public boolean contains(String element) {
		BitSet mask = new BitSet(m);
		BitSet tempData = new BitSet(m);
		
		tempData.set(0, m, true);
		tempData.xor(data);
		
		mask.set(h1(element)%m);
		mask.set(h2(element)%m);
		mask.set(h3(element)%m);
		// this marks the call to h4 when it was being considered as an option
		//mask.set(h4(element)%m);
		
		if (mask.intersects(tempData))
			return false;
		
		return true;
	}
	
	private int h1(String element) {
		int hashValue = 0;
		char[] charArray = element.toCharArray();
		
		for (int i = 0; i < charArray.length; i++) {
			hashValue += (((charArray[i] * 2029 * i << i) / 3137) << i) * 199 * i;
		}
		
		if (hashValue < 0)
			hashValue *= -1;
		
		return hashValue;
	}
	
	private int h2(String element) {
		int hashValue = element.hashCode();
		if (hashValue < 0)
			hashValue *= -1;
		return hashValue;
	}
	
	private int h3(String element) {
		int hashValue = 0;
		try {
			MessageDigest hasher = MessageDigest.getInstance("MD5");
			hasher.update(element.getBytes());
			byte[] digestedBytes = hasher.digest();
			for (int i = 0; i < digestedBytes.length; i++) {
				if (digestedBytes[i] < 0)
					hashValue += ~digestedBytes[i] << i;
				else
					hashValue += digestedBytes[i] << i;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashValue;
	}
	
	
	/*
	 * This is  fourth hash code function used when attempting to optimize the bloom filter set.
	 * It didn't really do much in terms of speeding up the data structure.
	 * The way it works out, each hash function adds about 2000 nanoseconds to the runtime.
	 * For that reason, I chose not to keep using the fourth hash function
	 */
	/*
	private int h4(String element) {
		int hashValue = 0;
		try {
			MessageDigest hasher = MessageDigest.getInstance("MD2");
			hasher.update(element.getBytes());
			byte[] digestedBytes = hasher.digest();
			for (int i = 0; i < digestedBytes.length; i++) {
				if (digestedBytes[i] < 0)
					hashValue += ~digestedBytes[i] << i;
				else
					hashValue += digestedBytes[i] << i;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashValue;
	}
	*/

}
