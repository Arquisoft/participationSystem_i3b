package hello;

import static org.junit.Assert.*;
import hello.model.Comment;
import hello.model.Proposal;
import hello.model.User;

import org.junit.Test;

public class ModelTests {

	@Test
	public void testComment() {
		Comment c1 = new Comment(null, "test");
		c1.setId("testId");
		assertEquals("testId", c1.getId());
		c1.setIdProposal("propId");
		c1.setUser(new User());
		c1.getUser().setName("tuser");
		assertTrue(c1.toString().contains("tuser"));
		assertTrue(c1.toString().contains("testId"));
	}
	
	@Test
	public void testProposal() {
		Proposal p1 = new Proposal(null, "cat1", "title", "content");
		p1.setId("testId");
		assertEquals("testId", p1.getId());
		p1.setAccepted(true);
		assertTrue(p1.isAccepted());
		User a = new User();
		a.setName("tuser");
		p1.setAuthor(a);
		assertTrue(p1.getAuthor().equals(a));
		assertTrue(p1.toString().contains("tuser"));
		assertTrue(p1.toString().contains("cat1"));
	}
	
	@Test
	public void testUser() {
		User c1 = new User();
		c1.setAdmin(true);
		assertNull(c1.getAge());
		assertNull(c1.getId());
	}

}
