package hello;


import hello.model.Configuration;
import hello.model.Proposal;
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
    public String deleteUser (Model model, @PathVariable String id) {
        model.addAttribute("prop", dbService.findProposalById(id));
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

}