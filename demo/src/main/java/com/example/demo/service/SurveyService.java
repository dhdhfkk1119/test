package com.example.demo.service;

import com.example.demo.entity.Survey;
import com.example.demo.entity.User;
import com.example.demo.repository.SurveyRepository;
import jakarta.persistence.criteria.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;

    public Survey getViews(Integer idx){
        Optional<Survey> survey = this.surveyRepository.findById(Long.valueOf(idx));
        if(survey.isPresent()){
            Survey survey1 = survey.get();
            survey1.setViews(survey1.getViews() +1);
            this.surveyRepository.save(survey1);
            return survey1;
        }else{
            throw new DataIntegrityViolationException("NOPE");
        }
    }
    public Survey create(
            @NotNull(message = "q 항목을 체크 해주십시오.") Long q,
            @NotNull(message = "w 항목을 체크 해주십시오.") Long w,
            @NotNull(message = "e 항목을 체크 해주십시오.") Long e,
            @NotNull(message = "r 항목을 체크 해주십시오.") Long r,
            @NotNull(message = "a 항목을 체크 해주십시오.") Long a,
            @NotNull(message = "s 항목을 체크 해주십시오.") Long s,
            @NotNull(message = "d 항목을 체크 해주십시오.") Long d,
            @NotNull(message = "f 항목을 체크 해주십시오.") Long f)
    {
        Survey survey = new Survey();
        survey.setQ(q);
        survey.setW(w);
        survey.setE(e);
        survey.setR(r);
        survey.setA(a);
        survey.setS(s);
        survey.setD(d);
        survey.setF(f);
        survey.setCreateAt(LocalDateTime.now());
        return surveyRepository.save(survey);
    }


    public Page<Survey> getList(int page, String kw, String sort) {
        List<Sort.Order> sorts = new ArrayList<>();

        if ("views".equalsIgnoreCase(sort)) {
            sorts.add(Sort.Order.desc("views")); // 조회수 기준으로 내림차순 정렬
        } else {
            sorts.add(Sort.Order.desc("createAt")); // 기본적으로는 최신순으로 정렬
        }

        // 페이지 요청에 대한 설정 (페이지 번호를 0부터 시작하도록 수정)
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        // 검색 조건 설정

        // 정렬된 설문 목록 조회
        return this.surveyRepository.findAll(pageable);
    }


    public List<Survey> findAll() {
        return surveyRepository.findAll();
    }

}
