package com.ga.jpatest.elasticsearch.web;

import com.ga.jpatest.elasticsearch.dto.KnowDto;
import com.ga.jpatest.elasticsearch.dto.KnowCreateForm;
import com.ga.jpatest.elasticsearch.dto.KnowSearchForm;
import com.ga.jpatest.elasticsearch.dto.KnowUpdateForm;
import com.ga.jpatest.elasticsearch.service.KnowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/know")
@Slf4j
@RequiredArgsConstructor
public class KnowController {
    private final KnowService knowService;
    @GetMapping
    public String know(Model model){
        model.addAttribute("form", new KnowCreateForm());
        return "knowCreateForm";
    }
    @GetMapping("/{id}/edit")
    public String knowUpdateForm(@PathVariable Long id,Model model){
        KnowDto knowDto = knowService.findById(id);
        model.addAttribute("form", new KnowUpdateForm(knowDto.getId(),knowDto.getTitle(),knowDto.getContent()));
        return "knowUpdateForm";
    }

    @PostMapping("/{id}/edit")
    public String knowUpdate(@ModelAttribute("form") KnowUpdateForm knowUpdateForm) {
        return "redirect:/";
    }

    @PostMapping("/new")
    public String knowCreate(@ModelAttribute("form") KnowCreateForm knowCreateForm){
        Long knowId = knowService.save(knowCreateForm);
        log.info("knowId = {}", knowId);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String search(@ModelAttribute("form") KnowSearchForm knowSearchForm, Model model) {
        log.info("title {}", knowSearchForm.getTitle());
        List<KnowDto> knows = knowService.searchByTitle(knowSearchForm.getTitle());
        model.addAttribute("knows", knows);
        return "knowList";
    }
    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "hello";
    }
}
