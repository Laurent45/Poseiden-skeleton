package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
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
public class RuleNameController {
    
    private final ICrudOperations<RuleName> ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        model.addAttribute("allRuleName", ruleNameService.getAllModels());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "ruleName/add";
        }
        ruleNameService.saveModel(ruleName);
        redirectAttributes.addFlashAttribute("savedOk", "ok");
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model,
                                 RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("ruleName", ruleNameService.getModel(id));
        } catch (IdRequestUnknown e) {
            redirectAttributes.addFlashAttribute("idUnknown", "unknown");
            return "redirect:/ruleName/list";
        }
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateBid(@PathVariable("id") Integer id,
                            @Valid RuleName ruleName,
                            BindingResult result, RedirectAttributes redirectAttributes) {
        ruleName.setId(id);
        if (result.hasErrors()) {
            return "ruleName/update";
        }
        try {
            ruleNameService.updateModel(id, ruleName);
        } catch (IdRequestUnknown e) {
            redirectAttributes.addFlashAttribute("idUnknown", "unknown");
        }
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id,
                            RedirectAttributes redirectAttributes) {
        ruleNameService.deleteModel(id);
        redirectAttributes.addFlashAttribute("delete", "ok");
        return "redirect:/ruleName/list";
    }
}
