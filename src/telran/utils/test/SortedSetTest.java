package telran.utils.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.utils.SortedSet;


abstract class SortedSetTest extends SetTest {

	SortedSet<Integer> set;
	@Override
	void setUp() {
		super.setUp();
		set = (SortedSet<Integer>)collection;
	}
	@Override
	protected void runTest(Integer[] expected) {
		//since iterating of SortedSet is done at sorted order (according to a Comparator)
		//expected array should be sorted
		Integer[]actual = collection.stream().toArray(Integer[]::new);
		Arrays.sort(expected);
		assertArrayEquals(expected, actual);
	}
	@Test
	void firstTest() {
		assertEquals(-20, set.first());
	}
	@Test
	void lastTest() {
		assertEquals(100, set.last());
	}
	@Test
	void floorTest() {
		assertEquals(10, set.floor(10));
		assertEquals(10, set.floor(11));
		assertNull(set.floor(-25));
		assertEquals(100, set.floor(150));
	}
	@Test
	void ceilingTest() {
		assertEquals(10, set.ceilling(10));
		assertEquals(100, set.ceilling(11));
		assertNull(set.ceilling(150));
		assertEquals(-20, set.ceilling(-25));
	}

}