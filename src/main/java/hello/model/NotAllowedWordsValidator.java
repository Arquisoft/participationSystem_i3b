package hello.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class NotAllowedWordsValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return Proposal.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "title", "title.empty");
		ValidationUtils.rejectIfEmpty(e, "content", "content.empty");
		Proposal p = (Proposal) o;

		if (Configuration.getInstance().containNotAllowedWord(p.getContent())) {
			e.rejectValue("content", "notAllowedWord");

		} else if (Configuration.getInstance().containNotAllowedWord(p
				.getTitle())) {
			e.rejectValue("title", "notAllowedWord");
		}

	}

}
