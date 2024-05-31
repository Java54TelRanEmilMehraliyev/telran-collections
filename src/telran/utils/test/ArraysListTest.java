package telran.utils.test;

import org.junit.jupiter.api.BeforeEach;

import telran.utils.ArrayList;

public class ArraysListTest extends ListTest {
  @BeforeEach
  @Override
  void setUp() {
	  collection = new ArrayList<Integer>(3);
	  super.setUp();
  }
}