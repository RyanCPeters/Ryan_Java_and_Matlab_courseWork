import java.util.HashMap;

/**This test file createsa library that uses some of the ideas for letter tiles that
 *  we've tossed around for the scrable game. By creating dictionaries of the tiles, then
 *  implementing that tile dictionary in the creation of word dictionaries I think I've managed
 *  to test most of the put and contains method use conditions.
 *
 *  further development should see testing for the .remove(key), .remove(key, value) and
 *  .replace(key) methods.
 * @author Ryan Peters
 * @date 3/19/2017
 */
public class TestHashDict {
	static HashDict<Character, ScrabTile> tileDict;
	static HashDict<String, ScrabWord> wordDict;

	public static void main(String[] args) {
		tileDict = new HashDict<>(31);


		/* a string that will be split into an array of strings and each word will have
		 * its "scrabble" score calculated and that score will be the "value" in each HashEntry
		 * while the word itself is the key*/
		String demoString = "To sit in solemn silence in a dull, dark dock, " +
				"In a pestilential prison, with a life-long lock, " +
				"Awaiting the sensation of a short, sharp shock," +
				"From a cheap and chippy chopper on a big black block! " +
				"To sit in solemn silence in a dull, dark dock, " +
				"In a pestilential prison, with a life-long lock," +
				"Awaiting the sensation of a short, sharp shock, " +
				"From a cheap and chippy chopper on a big black block! " +
				"A dull, dark dock, a life-long lock, " +
				"A short, sharp shock, a big black block! " +
				"To sit in solemn silence in a pestilential prison, " +
				"And awaiting the sensation " +
				"From a cheap and chippy chopper on a big black block! ";

		String[] strArraySpaceSplit = demoString.trim().split(" ");
		wordDict = new HashDict<>(strArraySpaceSplit.length / 2);
		setUpTileDict();
		for (String elem : strArraySpaceSplit) {
			if (!elem.equals("")) {
				setUpWordDict(elem.trim());
			}
		}
//		System.out.println("the contents of strArraySpaceSplit are:\n");
//		for (String s : strArraySpaceSplit) System.out.println(s);
		ScrabWord testObj1 = new ScrabWord("Tosit");
		ScrabWord testObj2 = new ScrabWord("tosit");
		ScrabWord testObj3 = new ScrabWord("chippy");
		ScrabWord testObj4 = new ScrabWord("Chippy");
		System.out.println(
				"Test 1: wordDict.toString()\n" + wordDict.toString());
		System.out.println();
		System.out.println(
				"Test 2: wordDict.containsKey(Tosit) -> " + wordDict.containsKey("Tosit"));
		System.out.println("\t\t\t\t\tExpected: false");
		System.out.println();
		System.out.println(
				"Test 3: wordDict.containsKey(tosit) -> " + wordDict.containsKey("tosit"));
		System.out.println("\t\t\t\t\tExpected: false");
		System.out.println();
		System.out.println(
				"Test 4: wordDict.containsKey(chippy) -> " + wordDict.containsKey("chippy"));
		System.out.println("\t\t\t\t\tExpected: true");
		System.out.println();
		System.out.println(
				"Test 5: wordDict.containsKey(Chippy) -> " + wordDict.containsKey("Chippy"));
		System.out.println("\t\t\t\t\tExpected: false");
		System.out.println();
		System.out.println(
				"Test 6: wordDict.size() -> " + wordDict.size());
		// earlier versions were really wrong here, I wasn't thinking when I made this expectation.
		System.out.println("\t\t\t\t\tExpected: 30");
		System.out.println();
		System.out.println(
				"Test 7: isEmpty() -> " + wordDict.isEmpty());
		System.out.println("\t\t\t\t\tExpected: false");
		System.out.println();
		System.out.println(
				"Test 8: wordDict.containsValue(Tosit) -> " + wordDict.containsValue(testObj1));
		System.out.println("\t\t\t\t\tExpected: false");
		System.out.println();
		System.out.println(
				"Test 9: wordDict.containsValue(tosit) -> " + wordDict.containsValue(testObj2));
		System.out.println("\t\t\t\t\tExpected: false");
		System.out.println();
		System.out.println(
				"Test 10: wordDict.containsValue(chippy) -> " + wordDict.containsValue(testObj3));
		System.out.println("\t\t\t\t\tExpected: true");
		System.out.println();
		System.out.println(
				"Test 11: wordDict.containsValue(Chippy) -> " + wordDict.containsValue(testObj4));
		System.out.println("\t\t\t\t\tExpected: false");
		System.out.println();
		System.out.println(
				"Test 12: wordDict.get(chippy) -> " + wordDict.get("chippy"));
		System.out.println("\t\t\t\t\tExpected: chippy");
		System.out.println();
		System.out.println(
				"Test 13: wordDict.remove(chippy) -> " + wordDict.remove("chippy"));
		System.out.println("\t\t\t\t\tExpected: chippy (that's testObj3)");
		System.out.println();
		System.out.println(
				"Test 13.5: wordDict.put(testObj3) -> " + wordDict.put(testObj3.actualWord, testObj3));
		System.out.println("\t\t\t\t\tExpected: null");
		System.out.println();
		System.out.println(
				"Test 14: wordDict.remove(chippy, testObj3) -> " + wordDict.remove("chippy", testObj3));
		System.out.println("\t\t\t\t\tExpected: true");
		System.out.println();
		System.out.println(
				"Test 15: wordDict.clear() -> should be nothing");
		wordDict.clear();
		System.out.println(wordDict);
		//ToDo: more test conditions for the remove methods and the replace method.
	}


	private static void setUpTileDict() {
		Character[] lettersChar = {
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
				's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
				'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '?', '!', '-', ',', '.'};
		Integer[] lettersCounts = {
				9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2,
				6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1,
				9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2,
				6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1, 2, 0, 0, 0, 0};
		Integer[] lettersValue = {
				1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1,
				1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10,
				1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1,
				1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10, 0, 0, 0, 0, 0};
		ScrabTile tileIntoDict;
		for (int i = 0; i < lettersChar.length; i++) {
			tileIntoDict = new ScrabTile(
					lettersCounts[i], lettersValue[i], lettersChar[i]);

			tileDict.put(tileIntoDict.AtoZ, tileIntoDict);
		}
	}

	private static void setUpWordDict(String word) {
		ScrabWord wordToScrab = new ScrabWord(word);
		wordDict.put(wordToScrab.actualWord, wordToScrab);
	}


	private static class ScrabTile {
		Integer frequency;
		Integer pointVal;
		Character AtoZ;

		ScrabTile(Integer frequency, Integer pointVal, Character AtoZ) {
			this.frequency = frequency;
			this.pointVal = pointVal;
			this.AtoZ = AtoZ;
		}

		/**
		 * Returns a hash code value for the object. This method is
		 * supported for the benefit of hash tables such as those provided by
		 * {@link HashMap}.
		 * <p>
		 * The general contract of {@code hashCode} is:
		 * <ul>
		 * <li>Whenever it is invoked on the same object more than once during
		 * an execution of a Java application, the {@code hashCode} method
		 * must consistently return the same integer, provided no information
		 * used in {@code equals} comparisons on the object is modified.
		 * This integer need not remain consistent from one execution of an
		 * application to another execution of the same application.
		 * <li>If two objects are equal according to the {@code equals(Object)}
		 * method, then calling the {@code hashCode} method on each of
		 * the two objects must produce the same integer result.
		 * <li>It is <em>not</em> required that if two objects are unequal
		 * according to the {@link Object#equals(Object)}
		 * method, then calling the {@code hashCode} method on each of the
		 * two objects must produce distinct integer results.  However, the
		 * programmer should be aware that producing distinct integer results
		 * for unequal objects may improve the performance of hash tables.
		 * </ul>
		 * <p>
		 * As much as is reasonably practical, the hashCode method defined by
		 * class {@code Object} does return distinct integers for distinct
		 * objects. (This is typically implemented by converting the internal
		 * address of the object into an integer, but this implementation
		 * technique is not required by the
		 * Java&trade; programming language.)
		 *
		 * @return a hash code value for this object.
		 * @see Object#equals(Object)
		 * @see System#identityHashCode
		 */
		@Override
		public int hashCode() {
			return AtoZ.hashCode();
		}

		/**
		 * Indicates whether some other object is "equal to" this one.
		 * <p>
		 * The {@code equals} method implements an equivalence relation
		 * on non-null object references:
		 * <ul>
		 * <li>It is <i>reflexive</i>: for any non-null reference value
		 * {@code x}, {@code x.equals(x)} should return
		 * {@code true}.
		 * <li>It is <i>symmetric</i>: for any non-null reference values
		 * {@code x} and {@code y}, {@code x.equals(y)}
		 * should return {@code true} if and only if
		 * {@code y.equals(x)} returns {@code true}.
		 * <li>It is <i>transitive</i>: for any non-null reference values
		 * {@code x}, {@code y}, and {@code z}, if
		 * {@code x.equals(y)} returns {@code true} and
		 * {@code y.equals(z)} returns {@code true}, then
		 * {@code x.equals(z)} should return {@code true}.
		 * <li>It is <i>consistent</i>: for any non-null reference values
		 * {@code x} and {@code y}, multiple invocations of
		 * {@code x.equals(y)} consistently return {@code true}
		 * or consistently return {@code false}, provided no
		 * information used in {@code equals} comparisons on the
		 * objects is modified.
		 * <li>For any non-null reference value {@code x},
		 * {@code x.equals(null)} should return {@code false}.
		 * </ul>
		 * <p>
		 * The {@code equals} method for class {@code Object} implements
		 * the most discriminating possible equivalence relation on objects;
		 * that is, for any non-null reference values {@code x} and
		 * {@code y}, this method returns {@code true} if and only
		 * if {@code x} and {@code y} refer to the same object
		 * ({@code x == y} has the value {@code true}).
		 * <p>
		 * Note that it is generally necessary to override the {@code hashCode}
		 * method whenever this method is overridden, so as to maintain the
		 * general contract for the {@code hashCode} method, which states
		 * that equal objects must have equal hash codes.
		 *
		 * @param obj the reference object with which to compare.
		 * @return {@code true} if this object is the same as the obj
		 * argument; {@code false} otherwise.
		 * @see #hashCode()
		 * @see HashMap
		 */
		@Override
		public boolean equals(Object obj) {
			return ((obj instanceof Character)) && AtoZ.equals(obj);
		}
	}

	private static class ScrabWord {
		ScrabTile[] theLetters;
		Integer wordScore;
		String actualWord;

		ScrabWord(String word) {
			actualWord = "";
			for (Character c : word.toCharArray()) {
				if (tileDict.containsKey(c)) {
					actualWord += c;
				}
			}
			theLetters = new ScrabTile[actualWord.length()];
			wordScore = 0;
			for (int j = 0; j < actualWord.length(); j++) {
				ScrabTile tmp = tileDict.get(actualWord.charAt(j));
				theLetters[j] = tmp;
				wordScore += theLetters[j].pointVal;
			}
		}

		/**
		 * Returns a string representation of the object. In general, the
		 * {@code toString} method returns a string that
		 * "textually represents" this object. The result should
		 * be a concise but informative representation that is easy for a
		 * person to read.
		 * It is recommended that all subclasses override this method.
		 * <p>
		 * The {@code toString} method for class {@code Object}
		 * returns a string consisting of the name of the class of which the
		 * object is an instance, the at-sign character `{@code @}', and
		 * the unsigned hexadecimal representation of the hash code of the
		 * object. In other words, this method returns a string equal to the
		 * value of:
		 * <blockquote>
		 * <pre>
		 * getClass().getName() + '@' + Integer.toHexString(hashCode())
		 * </pre></blockquote>
		 *
		 * @return a string representation of the object.
		 */
		@Override
		public String toString() {
//			return actualWord + " " + wordScore;
			return actualWord;
		}

		/**
		 * Returns a hash code value for the object. This method is
		 * supported for the benefit of hash tables such as those provided by
		 * {@link HashMap}.
		 * <p>
		 * The general contract of {@code hashCode} is:
		 * <ul>
		 * <li>Whenever it is invoked on the same object more than once during
		 * an execution of a Java application, the {@code hashCode} method
		 * must consistently return the same integer, provided no information
		 * used in {@code equals} comparisons on the object is modified.
		 * This integer need not remain consistent from one execution of an
		 * application to another execution of the same application.
		 * <li>If two objects are equal according to the {@code equals(Object)}
		 * method, then calling the {@code hashCode} method on each of
		 * the two objects must produce the same integer result.
		 * <li>It is <em>not</em> required that if two objects are unequal
		 * according to the {@link Object#equals(Object)}
		 * method, then calling the {@code hashCode} method on each of the
		 * two objects must produce distinct integer results.  However, the
		 * programmer should be aware that producing distinct integer results
		 * for unequal objects may improve the performance of hash tables.
		 * </ul>
		 * <p>
		 * As much as is reasonably practical, the hashCode method defined by
		 * class {@code Object} does return distinct integers for distinct
		 * objects. (This is typically implemented by converting the internal
		 * address of the object into an integer, but this implementation
		 * technique is not required by the
		 * Java&trade; programming language.)
		 *
		 * @return a hash code value for this object.
		 * @see Object#equals(Object)
		 * @see System#identityHashCode
		 */
		@Override
		public int hashCode() {
			return actualWord.hashCode();
		}

		/**
		 * Indicates whether some other object is "equal to" this one.
		 * <p>
		 * The {@code equals} method implements an equivalence relation
		 * on non-null object references:
		 * <ul>
		 * <li>It is <i>reflexive</i>: for any non-null reference value
		 * {@code x}, {@code x.equals(x)} should return
		 * {@code true}.
		 * <li>It is <i>symmetric</i>: for any non-null reference values
		 * {@code x} and {@code y}, {@code x.equals(y)}
		 * should return {@code true} if and only if
		 * {@code y.equals(x)} returns {@code true}.
		 * <li>It is <i>transitive</i>: for any non-null reference values
		 * {@code x}, {@code y}, and {@code z}, if
		 * {@code x.equals(y)} returns {@code true} and
		 * {@code y.equals(z)} returns {@code true}, then
		 * {@code x.equals(z)} should return {@code true}.
		 * <li>It is <i>consistent</i>: for any non-null reference values
		 * {@code x} and {@code y}, multiple invocations of
		 * {@code x.equals(y)} consistently return {@code true}
		 * or consistently return {@code false}, provided no
		 * information used in {@code equals} comparisons on the
		 * objects is modified.
		 * <li>For any non-null reference value {@code x},
		 * {@code x.equals(null)} should return {@code false}.
		 * </ul>
		 * <p>
		 * The {@code equals} method for class {@code Object} implements
		 * the most discriminating possible equivalence relation on objects;
		 * that is, for any non-null reference values {@code x} and
		 * {@code y}, this method returns {@code true} if and only
		 * if {@code x} and {@code y} refer to the same object
		 * ({@code x == y} has the value {@code true}).
		 * <p>
		 * Note that it is generally necessary to override the {@code hashCode}
		 * method whenever this method is overridden, so as to maintain the
		 * general contract for the {@code hashCode} method, which states
		 * that equal objects must have equal hash codes.
		 *
		 * @param obj the reference object with which to compare.
		 * @return {@code true} if this object is the same as the obj
		 * argument; {@code false} otherwise.
		 * @see #hashCode()
		 * @see HashMap
		 */
		@Override
		public boolean equals(Object obj) {

			if (obj instanceof ScrabWord) {
				if (actualWord.equals(((ScrabWord) obj).actualWord)) ;
				return true;
			}
			return obj instanceof String && actualWord == obj;
		}
	}
}
