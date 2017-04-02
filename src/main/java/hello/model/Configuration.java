package hello.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.repository.DBServiceImpl;

/**
 * @author Oriol The council gives the config directly to the system admin (?)
 */
public class Configuration {
	private static Configuration instance;
	private List<String> categories;
	private int expirationDateDays;
	private HashSet<String> notAllowedWords;
	private int minimumSupportVotes;

	private Configuration() {
		this.categories = new ArrayList<>();
		this.categories.add("Cat1");
		this.categories.add("Cat2");
		this.categories.add("Cat3");
		this.expirationDateDays = 30;
		this.notAllowedWords = new HashSet<>();
		this.notAllowedWords.add("fuck");
		this.notAllowedWords.add("ex2");
		this.notAllowedWords.add("ex3");
		this.minimumSupportVotes = 10;
	}

	public static Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}

	public void addCategory(String category) {
		categories.add(category);
	}

	public void deleteCategory(String category) {
		categories.remove(category);
	}

	public void addNotAllowedWord(String word) {
		notAllowedWords.add(word.toLowerCase());
	}

	public void deleteNotAllowedWord(String word) {
		notAllowedWords.remove(word);
	}

	public boolean containNotAllowedWord(String text) {
		String[] words = text.split("\\W+");

		for (String w : words) {
			if (notAllowedWords.contains(w.toLowerCase())) {
				return true;
			}
		}
		return false;

	}

	public List<String> getCategories() {
		return categories;
	}

	public int getExpirationDays() {
		return expirationDateDays;
	}

	public HashSet<String> getNotAllowedWords() {
		return notAllowedWords;
	}

	public int getMinimumSupportVotes() {
		return minimumSupportVotes;
	}

	public void setMinimumSupportVotes(int minimumSupportVotes) {
		this.minimumSupportVotes = minimumSupportVotes;
	}

	@Override
	public String toString() {
		return "Configuration{" + ", categories=" + categories
				+ ", expirationDateDays=" + expirationDateDays
				+ ", notAllowedWords=" + notAllowedWords
				+ ", minimumSupportVotes=" + minimumSupportVotes + '}';
	}
}
