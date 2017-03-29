package hello;


import hello.model.Comment;
import hello.model.Configuration;
import hello.model.Proposal;
import hello.model.User;
import hello.repository.DBService;
import hello.repository.DBServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import hello.producers.KafkaProducer;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private final DBService dbService;

    @Autowired
    MainController(DBService dbService) {
        this.dbService = dbService;
    }

    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping(value="selectProposal/{id}", method = RequestMethod.POST)
    public String selectProposal (Model model, @PathVariable String id) {
        model.addAttribute("prop", dbService.findProposalById(id));
        return "redirect:/proposal";
    }

    @RequestMapping(value="upvoteProposal}", method = RequestMethod.POST)
    public String upvoteProposal (Model model) {
        Proposal prop = (Proposal) model.asMap().get("prop");
        prop.upvote();
        dbService.updateProposal(prop);
        return "redirect:/proposal";
    }

    @RequestMapping(value="downvoteProposal/{id}", method = RequestMethod.POST)
    public String downvoteProposal (Model model, @PathVariable String id) {
        Proposal prop = (Proposal) model.asMap().get("prop");
        prop.downvote();
        dbService.updateProposal(prop);
        return "redirect:/proposal";
    }

    @RequestMapping(value="upvoteComment/{id}", method = RequestMethod.POST)
    public String upvoteComment (Model model, @PathVariable String id) {
        Comment com = dbService.findCommentByID(id);
        com.upvote();
        dbService.updateComment(com);
        return "redirect:/proposal";
    }

    @RequestMapping(value="downvoteComment/{id}", method = RequestMethod.POST)
    public String downvoteComment (Model model, @PathVariable String id) {
        Comment com = dbService.findCommentByID(id);
        com.downvote();
        dbService.updateComment(com);
        return "redirect:/proposal";
    }

    @RequestMapping("/")
    public String landing(Model model) {
        model.addAttribute("message", new Message());
        return "index";
    }

    @RequestMapping("/send")
    public String send(Model model, @ModelAttribute Message message) {
        kafkaProducer.send("exampleTopic", message.getMessage());
        return "redirect:/";
    }

    @RequestMapping("/createProposal")
    public String createProposal(Model model) {
        model.addAttribute("proposal", new Proposal());
        dbService.insertProposal((Proposal) model.asMap().get("proposal"));
        model.asMap().remove("proposal");
        return "redirect:/proposal";
    }

    @RequestMapping("/createProposal")
    public String commentProposal(Model model) {
        model.addAttribute("comment", new Comment());
        Proposal prop = (Proposal) model.asMap().get("prop");
        Comment com = (Comment) model.asMap().get("comment");
        dbService.insertComment(com, prop);
        model.asMap().remove("comment");
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