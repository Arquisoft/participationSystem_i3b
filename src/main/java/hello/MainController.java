package hello;


import hello.model.*;
import hello.repository.DBService;
import hello.repository.DBServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import hello.producers.KafkaProducer;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private DBServiceImpl dbService;

    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping(value="selectProposal/{id}", method = RequestMethod.POST)
    public String selectProposal (Model model, @PathVariable String id) {
        model.addAttribute("prop", dbService.findProposalById(id));
        return "redirect:/proposal";
    }

    @RequestMapping(value="/userHome")
    public String userHome(Model model) {
        model.addAttribute("createProposal", new CreateProposal());
        //model.addAttribute("proposals", new ArrayList<Proposal>());
        return "userHome";
    }

    @RequestMapping(value="/index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value="upvoteProposal", method = RequestMethod.POST)
    public String upvoteProposal (Model model) {
        Proposal prop = (Proposal) model.asMap().get("prop");
        prop.upvote();
        dbService.updateProposal(prop);
        //kafkaProducer.send("upvoted Proposal", "test");
        return "redirect:/proposal";
    }

    @RequestMapping(value="downvoteProposal/{id}", method = RequestMethod.POST)
    public String downvoteProposal (Model model, @PathVariable String id) {
        Proposal prop = (Proposal) model.asMap().get("prop");
        prop.downvote();
        dbService.updateProposal(prop);
        //kafkaProducer.send("downvoted Proposal", "test");
        return "redirect:/proposal";
    }

    @RequestMapping(value="upvoteComment/{id}", method = RequestMethod.POST)
    public String upvoteComment (Model model, @PathVariable String id) {
        Comment com = dbService.findCommentByID(id);
        com.upvote();
        dbService.updateComment(com);
        //kafkaProducer.send("upvoted Comment", "test");
        return "redirect:/proposal";
    }

    @RequestMapping(value="downvoteComment/{id}", method = RequestMethod.POST)
    public String downvoteComment (Model model, @PathVariable String id) {
        Comment com = dbService.findCommentByID(id);
        com.downvote();
        dbService.updateComment(com);
        //kafkaProducer.send("downvoted Comment", "test");
        return "redirect:/proposal";
    }

    @RequestMapping("/")
    public ModelAndView landing(Model model) {
        return new ModelAndView("redirect:" + "/userHome");
    }

    @RequestMapping("/createProposal")
    public String createProposal(Model model, @ModelAttribute CreateProposal createProposal) {
        Proposal proposal = new Proposal();
        proposal.setTitle(createProposal.getTitle());
        proposal.setContent(createProposal.getContent());
        proposal.setCategory(createProposal.getCategory());
        dbService.insertProposal(proposal);
        //kafkaProducer.send("new Proposal", "test");
        return "redirect:/userHome";
    }

    @RequestMapping("/createComment")
    public String commentProposal(Model model, @ModelAttribute CreateComment createComment) {
        Comment comment = new Comment();
        comment.setContent("createComment");
        Proposal prop = (Proposal) model.asMap().get("prop");
        dbService.insertComment(comment, prop);
        //kafkaProducer.send("new Comment", "test");
        return "redirect:/proposal";
    }

    @ModelAttribute("proposals")
    public List<Proposal> proposals() {
        return dbService.findAllProposals();
    }

    @ModelAttribute("categories")
    public List<String> categories() {
        return Configuration.getInstance().getCategories();
    }

    @ModelAttribute("author")
    public User author() {
        return new User("ASW", 20);
    }

}