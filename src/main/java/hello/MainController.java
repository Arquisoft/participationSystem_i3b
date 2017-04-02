package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import hello.model.Comment;
import hello.model.Configuration;
import hello.model.CreateComment;
import hello.model.CreateProposal;
import hello.model.CreateUser;
import hello.model.NotAllowedWordsValidator;
import hello.model.Proposal;
import hello.model.User;
import hello.producers.KafkaProducer;
import hello.repository.DBServiceImpl;
import hello.services.RegistrationService;

@Controller
public class MainController {

	@Autowired
	private DBServiceImpl dbService;

	@Autowired
	private RegistrationService registration;

	@Autowired
	private KafkaProducer kafkaProducer;

	@RequestMapping(value = "selectProposal/{id}", method = RequestMethod.GET)
	public String selectProposal(Model model, @PathVariable String id) {
		model.addAttribute("prop", dbService.findProposalById(id));
		model.addAttribute("createComment", new CreateComment());
		return "proposal";
	}

	@RequestMapping(value = "/userHome")
	public String userHome(Model model) {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (user.isAdmin()) {
			return "adminHome";
		}
		model.addAttribute("createProposal", new CreateProposal());

		return "userHome";
	}

	@RequestMapping(value = "/index")
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value = "/upvoteProposal/{id}", method = RequestMethod.GET)
	public String upvoteProposal(Model model, @PathVariable("id") String id) {
		Proposal prop = dbService.findProposalById(id);
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (prop != null) {
			prop.upvote(user.getName());
			dbService.updateProposal(prop);
		}
		kafkaProducer.send("upvotedProposal", "upvoted proposal");
		return "redirect:/selectProposal/" + id;

	}

	@RequestMapping(value = "/downvoteProposal/{id}", method = RequestMethod.GET)
	public String downvoteProposal(Model model, @PathVariable("id") String id) {
		Proposal prop = dbService.findProposalById(id);
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (prop != null) {
			prop.downvote(user.getName());
			dbService.updateProposal(prop);
		}

		kafkaProducer.send("downvotedProposal", "downvoted proposal");

		return "redirect:/selectProposal/" + id;
	}

	@RequestMapping(value = "/upvoteComment/{proposalId}/{id}")
	public String upvoteComment(Model model,
			@PathVariable("proposalId") String proposalId,
			@PathVariable("id") String id) {
		Comment com = dbService.findCommentByID(proposalId, id);
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (com != null) {
			com.upvote(user.getName());
			dbService.updateComment(proposalId, com);
		}
		kafkaProducer.send("upvotedComment", "upvoted comment");
		return "redirect:/selectProposal/" + proposalId;
	}

	@RequestMapping(value = "/downvoteComment/{proposalId}/{id}")
	public String downvoteComment(Model model,
			@PathVariable("proposalId") String proposalId,
			@PathVariable("id") String id) {
		Comment com = dbService.findCommentByID(proposalId, id);
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (com != null) {
			com.downvote(user.getName());
			dbService.updateComment(proposalId, com);
		}
		kafkaProducer.send("downvotedComment", "downvoted comment");
		return "redirect:/selectProposal/" + proposalId;
	}

	@RequestMapping("/")
	public ModelAndView landing(Model model) {
		return new ModelAndView("redirect:" + "/userHome");
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("createUser", new CreateUser());
		return "login";
	}

	@RequestMapping("/register")
	public String register(Model model, @ModelAttribute CreateUser createUser) {
		registration.registrate(createUser);
		return "login";
	}

	@RequestMapping("/createProposal")
	public String createProposal(Model model,
			@ModelAttribute CreateProposal createProposal,
			BindingResult result) {

		Proposal proposal = new Proposal();
		proposal.setTitle(createProposal.getTitle());
		proposal.setContent(createProposal.getContent());
		proposal.setCategory(createProposal.getCategory());

		NotAllowedWordsValidator validator = new NotAllowedWordsValidator();
		validator.validate(proposal, result);

		if (result.hasErrors()) {

		} else {
			dbService.insertProposal(proposal);
			kafkaProducer.send("createdProposal", "created proposal");

		}
		return "redirect:/userHome";
	}

	@RequestMapping("/deleteProposal/{id}")
	public String deleteProposal(Model model, @PathVariable("id") String id) {
		dbService.deleteProposalById(id);
		return "redirect:/userHome";
	}

	@RequestMapping("/createComment/{id}")
	public String commentProposal(Model model, @PathVariable("id") String id,
			@ModelAttribute CreateComment createComment) {
		Comment comment = new Comment();
		comment.setContent(createComment.getContent());
		comment.setIdProposal(id);
		Proposal prop = dbService.findProposalById(id);
		dbService.insertComment(comment, prop);
		kafkaProducer.send("createdComment", "created comment");
		return "redirect:/selectProposal/" + id;
	}

	@ModelAttribute("proposals")
	public List<Proposal> proposals() {
		return dbService.findAllProposals();
	}

	@ModelAttribute("categories")
	public List<String> categories() {
		return Configuration.getInstance().getCategories();
	}

	@ModelAttribute("notAllowedWords")
	public List<String> notAllowedWords() {
		return new ArrayList<String>(Configuration.getInstance().getNotAllowedWords());
	}

	@ModelAttribute("author")
	public User author() {
		return new User("ASW", 20);
	}

}