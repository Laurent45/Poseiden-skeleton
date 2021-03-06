package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exception.IdRequestUnknown;
import com.nnk.springboot.service.ICrudOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RatingController {

    private final ICrudOperations<Rating> ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        model.addAttribute("allRatings", ratingService.getAllModels());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result,
                           Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "rating/add";
        }
        ratingService.saveModel(rating);
        redirectAttributes.addFlashAttribute("savedOk", "ok");
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model,
     RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("rating", ratingService.getModel(id));
        } catch (IdRequestUnknown e) {
            redirectAttributes.addFlashAttribute("idUnknown", "unknown");
            return "redirect:/rating/list";
        }
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, RedirectAttributes redirectAttributes) {
        rating.setId(id);
        if (result.hasErrors()) {
            return "rating/update";
        }
        try {
            ratingService.updateModel(id, rating);
        } catch (IdRequestUnknown e) {
            redirectAttributes.addFlashAttribute("idUnknown", "unknown");
        }
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id,
                               RedirectAttributes redirectAttributes) {
        ratingService.deleteModel(id);
        redirectAttributes.addFlashAttribute("delete", "ok");
        return "redirect:/rating/list";
    }
}
