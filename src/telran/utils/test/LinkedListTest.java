package telran.utils.test;

import org.junit.jupiter.api.BeforeEach;

import telran.utils.LinkedList;

public class LinkedListTest extends ListTest {
	@BeforeEach
	  @Override
	  void setUp() {
		  collection = new LinkedList<Integer>();
		  super.setUp();
	  }
}