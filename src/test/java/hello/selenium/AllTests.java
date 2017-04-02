package hello.selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RegisterTest.class, CreateProposalTest.class,
		CommentProposalTest.class, VoteProposalTest.class })
public class AllTests {

}
