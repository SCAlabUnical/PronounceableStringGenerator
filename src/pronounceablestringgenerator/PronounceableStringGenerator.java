package pronounceablestringgenerator;

import java.awt.Label;
import java.awt.Panel;
import java.util.Random;

/**
 * A Java class that allows to generate pronounceable random strings.
 * @author  Fabrizio Marozzo
 * @since   1.0
 */
public class PronounceableStringGenerator {
	
	private final static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	private static GpwData data = new GpwData();
	private static Random ran;
	
	
	/***
	 * This method generate a pronounceable random String of length 'sLength'
	 * @param sLength length of the output string
	 * @return a pronounceable random string
	 */
	public String generate (int sLength) {
		int c1, c2, c3;
		 long sum = 0;
		 int nchar;
		 long ranno;
		 double pik;
		 StringBuffer password;
		 ran = new Random(); // new random source seeded by clock

		 // Pick a random starting point.
		   password = new StringBuffer(sLength);
		   pik = ran.nextDouble(); // random number [0,1]
		   ranno = (long)(pik * data.getSigma()); // weight by sum of frequencies
		   sum = 0;
		   for (c1=0; c1 < 26; c1++) {
			for (c2=0; c2 < 26; c2++) {
			  for (c3=0; c3 < 26; c3++) {
			    sum += data.get(c1, c2, c3);
			    if (sum > ranno) {
			      password.append(alphabet.charAt(c1));
			      password.append(alphabet.charAt(c2));
			      password.append(alphabet.charAt(c3));
			      c1 = 26; // Found start. Break all 3 loops.
			      c2 = 26;
			      c3 = 26;
			    } // if sum
			  } // for c3
			} // for c2
		   } // for c1

		   // Now do a random walk.
		   nchar = 3;
		   while (nchar < sLength) {
			c1 = alphabet.indexOf(password.charAt(nchar-2));
			c2 = alphabet.indexOf(password.charAt(nchar-1));
			sum = 0;
			for (c3=0; c3 < 26; c3++)
			  sum += data.get(c1, c2, c3);
			if (sum == 0) {
			  break;	// exit while loop
			}
			pik = ran.nextDouble();
			ranno = (long)(pik * sum);
			sum = 0;
			for (c3=0; c3 < 26; c3++) {
			  sum += data.get(c1, c2, c3);
			  if (sum > ranno) {
			    password.append(alphabet.charAt(c3));
			    c3 = 26; // break for loop
			  } // if sum
			} // for c3
			nchar ++;
		   } // while nchar
		   return password.toString(); // Password generated
		} // generate()
	
	/***
	 * This method generate a pronounceable random String of length 'sLength'
	 * @param r a Random generator
	 * @param sLength length of the output string
	 * @return a pronounceable random string
	 */
	public String generate(Random r, int sLength) {
		ran = r;
		return generate(sLength);
	} // generate()
}
