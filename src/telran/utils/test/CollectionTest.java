package telran.utils.test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.utils.ArrayList;
import telran.utils.Collection;

public abstract class CollectionTest {
	protected Collection<Integer> collection;
	Integer[] numbers = {-20, 10, 1, 100, -5};
	
	@BeforeEach
	void setUp() {
		collection = new ArrayList<>();
		for(Integer num: numbers) {
			collection.add(num);
		}
	}
	@Test
	void iteratorTest() {
		runTest(numbers);
	}
	protected void runTest(Integer[] expected) {
		Integer [] actual = collection.stream().toArray(Integer[]::new);
		assertArrayEquals(expected, actual);
	};
	
	@Test
	void addTest() {
		assertTrue(collection.add(200));
		assertTrue(collection.contains(200));
	}
	
	@Test
	void removeTest() {
		assertTrue(collection.remove(100));
		assertFalse(collection.contains(100));
		Integer[] expected = {-20, 10, 1,-5};
		runTest(expected);
	}
	
	@Test
	void containsTest() {
		assertTrue(collection.contains(100));
		assertFalse(collection.contains(200));
	}
	
	@Test
	void sizeTest() {
		assertEquals(5, collection.size());
		collection.add(200);
		assertEquals(6, collection.size());
	}
	
	
}