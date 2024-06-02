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
}
@Test
void getTest() {
    Integer result = set.get(1);
    if (result != null) {
        assertEquals(1, result.intValue());
    } else {
        fail("Expected 1, but got null");
    }
    assertNull(set.get(1000));
}
@Override
@Test
void addEqualedTest(){
	assertFalse(set.add(numbers[0]));
}
}