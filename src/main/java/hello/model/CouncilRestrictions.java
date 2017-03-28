package hello.model;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Oriol
 *
 */
public class CouncilRestrictions {
	private List<String> categories;
	private Date expirationDate;
	private List<String> notAllowedWords;
	private int minimumSupportVotes;

	public CouncilRestrictions(List<String> categories, Date expirationDate,
                               List<String> notAllowedWords, int votes) {
		this.categories = categories;
		this.expirationDate = expirationDate;
		this.notAllowedWords = notAllowedWords;
		this.minimumSupportVotes = votes;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public void setActiveDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void setNotAllowedWords(List<String> notAllowedWords) {
		this.notAllowedWords = notAllowedWords;
	}

	public List<String> getCategories() {
		return categories;
	}

	public Date getActiveDate() {
		return expirationDate;
	}

	public List<String> getNotAllowedWords() {
		return notAllowedWords;
	}

	public int getMinimumSupportVotes() {
		return minimumSupportVotes;
	}

	public void setMinimumSupportVotes(int minimumSupportVotes) {
		this.minimumSupportVotes = minimumSupportVotes;
	}
}
