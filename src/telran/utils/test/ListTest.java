package telran.utils.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.utils.List;

public abstract class ListTest extends CollectionTest {
   List<Integer> list;
   @BeforeEach
   @Override
   void setUp() {
	   list = (List<Integer>)collection;
	   super.setUp();
   }
      
   @Test
   public void testAddAndGet() {
	   list.add(1);
	   list.add(2);
	   list.add(3);
	   list.add(4);
	   assertEquals(1,list.get(0));
   }
   @Test
   public void testRemove() {
	   list.add(1);
	   list.remove(0);
	   assertEquals(0,list.size());
   }
   @Test
   public void testIndexOf() {
	   list.add(1);
	    assertEquals(0, list.indexOf(1));
	    assertEquals(-1, list.indexOf(2));
	}
   @Test
   public void testLastIndexOf() {
	   list.add(1);
	   list.add(2);
	   list.add(3);
	  assertEquals(1, list.lastIndexOf(2));
   }
   @Test
   public void testIterator() {
       list.add(1);
       list.add(2);
       Iterator<Integer> iterator = list.iterator();
       assertTrue(iterator.hasNext());
       assertEquals(1, iterator.next());
       assertTrue(iterator.hasNext());
       assertEquals(2, iterator.next());
       assertFalse(iterator.hasNext());
   }
}