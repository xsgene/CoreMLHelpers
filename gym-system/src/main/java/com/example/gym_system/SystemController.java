package com.example.gym_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SystemController {

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("gyms", gymRepository.findAll());
        return "index";
    }

    @PostMapping("/gym/add")
    public String addGym(@RequestParam String name, @RequestParam String address) {
        Gym gym = new Gym(name, address);
        gymRepository.save(gym);
        return "redirect:/";
    }

    @GetMapping("/trainer/{id}")
    public String trainerDetails(@PathVariable Long id, Model model) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trainer Id:" + id));
        model.addAttribute("trainer", trainer);
        return "trainer";
    }

    @PostMapping("/trainer/add")
    public String addTrainer(@RequestParam String name, @RequestParam String specialty, @RequestParam Long gymId) {
        Gym gym = gymRepository.findById(gymId).orElseThrow(() -> new IllegalArgumentException("Invalid gym Id:" + gymId));
        Trainer trainer = new Trainer(name, specialty, gym);
        trainerRepository.save(trainer);
        return "redirect:/";
    }

    @PostMapping("/review/add")
    public String addReview(@RequestParam String reviewerName, @RequestParam int rating, @RequestParam String comment, @RequestParam Long trainerId) {
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow(() -> new IllegalArgumentException("Invalid trainer Id:" + trainerId));
        Review review = new Review(reviewerName, rating, comment, trainer);
        reviewRepository.save(review);
        return "redirect:/trainer/" + trainerId;
    }
}
