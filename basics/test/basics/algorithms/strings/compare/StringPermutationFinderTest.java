package basics.algorithms.strings.compare;

import org.junit.jupiter.api.Test;

class StringPermutationFinderTest {

	@Test
	void isPermutationOf_returnFalse() {
		StringPermutationFinder.isPermutationOf("".toCharArray(), "".toCharArray());
	}

}
