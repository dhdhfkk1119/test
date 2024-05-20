package com.example.demo.controller;

import com.example.demo.entity.Survey;
import com.example.demo.entity.SurveyForm;
import com.example.demo.repository.SurveyRepository;
import com.example.demo.service.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SurveyController {
    private final SurveyService surveyService;

    @GetMapping("/survey")
    public String surveyForm(SurveyForm surveyForm, Model model,
                             @RequestParam(value = "sort", defaultValue = "createAt") String sort,
                             @RequestParam(value = "kw", defaultValue = "") String kw,
                             @RequestParam(value="page", defaultValue="0") int page) {
        Page<Survey> surveyList = this.surveyService.getList(page, kw, sort);
        model.addAttribute("surveyList", surveyList.getContent());
        model.addAttribute("paging", surveyList);
        model.addAttribute("sort", sort);
        model.addAttribute("kw", kw);
        return "survey";
    }



    private int calculateTotalScore(List<Survey> surveyList) {
        int totalScore = 0;
        for (Survey survey : surveyList) {
            totalScore += survey.getQ() +
                    survey.getW() +
                    survey.getE() +
                    survey.getR() +
                    survey.getA() +
                    survey.getS() +
                    survey.getD() +
                    survey.getF();
        }
        return totalScore;
    }


    @PostMapping("/survey")
    public String submitSurvey(@Valid SurveyForm surveyForm, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "survey"; // 폼 템플릿 이름으로 변경해야 합니다.
        }
        surveyService.create(surveyForm.getQ(), surveyForm.getW(), surveyForm.getE(), surveyForm.getR(),
                surveyForm.getA(), surveyForm.getS(), surveyForm.getD(), surveyForm.getF());
        return "redirect:/";
    }

    @GetMapping("/survey/{id}")
    public String getSurvey(@PathVariable Integer id, Model model) {
        Survey survey = surveyService.getViews(id);
        model.addAttribute("survey", survey);
        return "surveyDetail"; // 상세 페이지를 보여주기 위한 뷰 이름
    }

}
