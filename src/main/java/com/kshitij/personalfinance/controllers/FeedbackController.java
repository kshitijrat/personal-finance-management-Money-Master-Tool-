package com.kshitij.personalfinance.controllers;

import org.hibernate.boot.query.FetchDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.theme.FixedThemeResolver;

import com.kshitij.personalfinance.entities.Feedback;
import com.kshitij.personalfinance.repo.FeedbackRepo;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackRepo repo;

    

    @PostMapping("/feedback")
    public String submitFeedback(@RequestParam("name") String name,
                                 @RequestParam("email") String email,
                                 @RequestParam("message") String message,
                                 Model model) {
        // Process the feedback (save it to the database, send an email, etc.)
        // For now, let's just print the feedback to the console
        Feedback feedback = new Feedback();
        feedback.setEmail(email);
        feedback.setName(name);
        feedback.setMessage(message);
        repo.save(feedback);
System.out.println(name);
System.out.println(email);
System.out.println(message);
        // Add a success message to display on the page
        model.addAttribute("successMessage", "Feedback submitted successfully!");

        return "redirect:/dashboard"; // Redirect to home page after submitting feedback
    }

}
