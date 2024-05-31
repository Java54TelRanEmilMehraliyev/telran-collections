package telran.utils.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.utils.Set;

public abstract class SetTest extends CollectionTest {
	Set<Integer> set;
@Override
void setUp() {
	super.setUp();
	set = (Set<Integer>)collection;
	System.out.println(set);
}
@Test
void getTest() {
    Integer result = set.get(1);
    if (result != null) {
        assertEquals(1, result.intValue());
    }
    assertNull(set.get(1000000));
}
@Override
@Test
void addEqualedTest(){
    System.out.println("Before add: " + set);
    assertTrue(set.add(numbers[0]));
    System.out.println("After add: " + set);
}
}