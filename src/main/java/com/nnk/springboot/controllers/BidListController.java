package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.IdRequestUnknown;
import com.nnk.springboot.service.IBidListService;
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
public class BidListController {

    private final IBidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        model.addAttribute("allBids", bidListService.getAllBids());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bidList) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bidList, BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.saveBid(bidList);
        redirectAttributes.addFlashAttribute("savedOk", "ok");
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model,
                                 RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("bidList", bidListService.getBid(id));
        } catch (IdRequestUnknown e) {
            redirectAttributes.addFlashAttribute("idUnknown", "unknown");
            return "redirect:/bidList/list";
        }
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result) {
        bidList.setBidListId(id);
        if (result.hasErrors()) {
            return "bidList/update";
        }
        bidListService.saveBid(bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id,
                            RedirectAttributes redirectAttributes) {
        bidListService.deleteBid(id);
        redirectAttributes.addFlashAttribute("delete", "ok");
        return "redirect:/bidList/list";
    }
}
