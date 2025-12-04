package com.example.flow_work.controller;

import com.example.flow_work.entity.Extension;
import com.example.flow_work.service.ExtensionService;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExtensionController {

    private final ExtensionService service;

    @GetMapping("/")
    public String index(
            @RequestParam(defaultValue = "id") String sort,
            Model model,
            @ModelAttribute("msg") String msg,
            @ModelAttribute("err") String err) {

        model.addAttribute("msg", msg);
        model.addAttribute("err", err);

        List<Extension> customs = service.getCustomSorted(sort);

        model.addAttribute("fixedList", service.getFixed());
        model.addAttribute("customList", customs);
        model.addAttribute("sort", sort);
        model.addAttribute("maxLength", 20);
        model.addAttribute("remainingCustom", 200 - customs.size());

        return "index";
    }

    @PostMapping("/fixed/{id}/toggle")
    public String toggleFixed(@PathVariable Long id,
                              @RequestParam(name="blocked", defaultValue="false") boolean blocked,
                              RedirectAttributes ra) {
        try {
            service.toggleFixed(id, blocked);
            ra.addFlashAttribute("msg", "저장되었습니다.");
        } catch (Exception e) {
            ra.addFlashAttribute("err", e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/custom/add")
    public String addCustom(@RequestParam("ext") @Size(max=20) String ext, RedirectAttributes ra) {
        try {
            service.addCustom(ext);
            ra.addFlashAttribute("msg", "추가되었습니다.");
        } catch (Exception e) {
            ra.addFlashAttribute("err", e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/custom/{id}/delete")
    public String deleteCustom(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.deleteCustom(id);
            ra.addFlashAttribute("msg", "삭제되었습니다.");
        } catch (Exception e) {
            ra.addFlashAttribute("err", e.getMessage());
        }
        return "redirect:/";
    }
}
