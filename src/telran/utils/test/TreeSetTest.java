package telran.utils.test;

import org.junit.jupiter.api.BeforeEach;

import telran.utils.TreeSet;


public class TreeSetTest extends SortedSetTest {
	@Override
	@BeforeEach
	void setUp() {
		collection = new TreeSet<>(); 
		super.setUp();
	}
}