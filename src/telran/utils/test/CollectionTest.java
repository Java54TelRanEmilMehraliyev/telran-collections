package telran.utils.test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.utils.Collection;

public abstract class CollectionTest {
	protected Collection<Integer> collection;
	Integer[] numbers = {-20, 10, 1, 100, -5};
	
	@BeforeEach
	void setUp() {
		for(Integer num: numbers) {
			collection.add(num);
		}
	}
	
	@Test
	void addTest() {
		assertTrue(collection.add(200));
		assertTrue(collection.contains(200));
	}
	
	@Test
	void removeTest() {
		assertTrue(collection.remove(100));
		assertFalse(collection.contains(100));
	}
	
	@Test
	void containsTest() {
		assertTrue(collection.contains(-20));
		assertFalse(collection.contains(200));
	}
	
	@Test
	void sizeTest() {
		assertEquals(5, collection.size());
		collection.add(200);
		assertEquals(6, collection.size());
	}
	
	protected void runTest(Integer[] expected) {
		Integer [] actual = collection.stream().toArray(Integer[]::new);
		assertArrayEquals(expected, actual);
	};
}