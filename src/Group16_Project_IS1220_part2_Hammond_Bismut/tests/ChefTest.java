package Group16_Project_IS1220_part2_Hammond_Bismut.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Group16_Project_IS1220_part2_Hammond_Bismut.users.Chef;
import Group16_Project_IS1220_part2_Hammond_Bismut.users.Client;

public class ChefTest {

	// Test HashCode()
	@Test
	public void testHashCode_differentObjects() {
		Chef chef = new Chef("Bob","Red","bobred","123456");
		Client client = new Client("Bob","Red","bobred","123456");
		assertNotEquals(client.hashCode(), chef.hashCode());
	}
	@Test
	public void testHashCode_caseSensitiveness() {
		Chef chef1 = new Chef("Bob","Red","bobred","123456");
		Chef chef2 = new Chef("bob","red","BobRed","123456");
		assertNotEquals(chef1.hashCode(), chef2.hashCode());
	}
	@Test
	public void testHashCode_differentChefs() {
		Chef chef1 = new Chef("Bob","Red","bobred","123456");
		Chef chef2 = new Chef("Martin","Dupond","martin59","000000");
		assertNotEquals(chef1.hashCode(), chef2.hashCode());
	}
	@Test
	public void testHashCode_sameChefs() {
		Chef chef1 = new Chef("Bob","Red","bobred","123456");
		Chef chef2 = new Chef("Bob","Red","bobred","123456");
		assertEquals(chef1.hashCode(), chef2.hashCode());
	}	

	// Test Equals()
	@Test
	public void testEquals_nullParameter() {
		Chef chef1 = new Chef("Bob","Red","bobred","123456");
		assertFalse(chef1.equals(null));
	}
	@Test
	public void testEquals_nonChefParameter() {
		Chef chef = new Chef("Bob","Red","bobred","123456");
		Client client = new Client("Bob","Red","bobred","123456");
		assertFalse(chef.equals(client));
	}
	@Test
	public void testEquals_differentChefs() {
		Chef chef1= new Chef("Bob","Red","bobred","123456");
		Chef chef2 = new Chef("Auguste","Gusteau","ratatouille","000000");
		assertFalse(chef1.equals(chef2));
	}
	@Test
	public void testEquals_caseSensitiveness() {
		Chef chef1 = new Chef("Bob","Red","bobred","123456");
		Chef chef2 = new Chef("bob","red","BobRed","123456");
		assertFalse(chef1.equals(chef2));
	}
	@Test
	public void testEquals_sameChefs() {
		Chef chef1 = new Chef("Bob","Red","bobred","123456");
		Chef chef2 = new Chef("Bob","Red","bobred","123456");
		assertTrue(chef1.equals(chef2));
	}

	// Test Chef()
	@Test 
	public void testChefRightParameters() {
	    try {
	      new Chef("1","2","3","4");
	      new Chef("string","string","string","string");
	    } catch (Exception e) {
	      fail(e.getMessage());
	    }
	}
	@Test (expected=NullPointerException.class)
	public void testChefNullParameter() {
		new Chef(null,null,null,null);       
	}
	@Test (expected=IllegalArgumentException.class)
	public void testChefEmptyStringParameter(){
		new Chef("Bob","Red","","123456");     
	}
}
