package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
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
public class TradeController {
    
    private final ICrudOperations<Trade> tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        model.addAttribute("allTrade", tradeService.getAllModels());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addRuleForm(Trade trade) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "trade/add";
        }
        tradeService.saveModel(trade);
        redirectAttributes.addFlashAttribute("savedOk", "ok");
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model,
                                 RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("trade", tradeService.getModel(id));
        } catch (IdRequestUnknown e) {
            redirectAttributes.addFlashAttribute("idUnknown", "unknown");
            return "redirect:/trade/list";
        }
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateBid(@PathVariable("id") Integer id,
                            @Valid Trade trade,
                            BindingResult result, RedirectAttributes redirectAttributes) {
        trade.setId(id);
        if (result.hasErrors()) {
            return "trade/update";
        }
        try {
            tradeService.updateModel(id, trade);
        } catch (IdRequestUnknown e) {
            redirectAttributes.addFlashAttribute("idUnknown", "unknown");
        }
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id,
                            RedirectAttributes redirectAttributes) {
        tradeService.deleteModel(id);
        redirectAttributes.addFlashAttribute("delete", "ok");
        return "redirect:/trade/list";
    }
}
