package com.example.learningspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Noel on 1/8/17.
 */
@Controller
public class IssueController {

    @Autowired
    private IssueManager issueManager;

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("issues", issueManager.findOpenIssues());
        return "index";
    }
}
