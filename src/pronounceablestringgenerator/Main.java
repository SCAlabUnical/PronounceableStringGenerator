package pronounceablestringgenerator;

import java.util.Random;

/**
 * Example of main. It generates three pronounceable random string from a random generator
 * @author  Fabrizio Marozzo
 * @since   1.0
 */
public class Main {
	public static void main(String[] args) {
		PronounceableStringGenerator mg = new PronounceableStringGenerator();
		Random ran = new Random(4782);
		System.out.println(mg.generate(ran, 8));
		System.out.println(mg.generate(ran, 8));
		System.out.println(mg.generate(ran, 8));
	}
}
