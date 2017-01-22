package io.kemper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoyerMooreTest {
	
	
	private BoyerMoore boyerMoore;
	
	@Before
	public void beforeSearch() {
		boyerMoore = new BoyerMoore("AAAGTT");
	}
	
	@Test
	public void testSearchSuccess() {
		assertEquals(0, boyerMoore.search("AAAGTT"));
		assertEquals(1, boyerMoore.search("CAAAGTT"));
		assertEquals(0, boyerMoore.search("AAAGTTC"));
		assertEquals(5, boyerMoore.search("AAAGTAAAGTTAAAGT"));		
	}
	
	@Test
	public void testSearchFailure() {
		assertEquals(-1, boyerMoore.search("AAAGTC"));
		assertEquals(-1, boyerMoore.search("CAAGTT"));
		assertEquals(-1, boyerMoore.search("AAACTT"));
		assertEquals(-1, boyerMoore.search("AACGTT"));
	}
	
}
