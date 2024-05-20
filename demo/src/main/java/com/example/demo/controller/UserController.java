package com.example.demo.controller;

import com.example.demo.entity.UserForm;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserForm userForm){
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserForm userForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "signup";
        }
        if(!userForm.getPassword1().equals(userForm.getPassword2())){
            bindingResult.rejectValue("password2","passwordInCorrect","2개의 패스워드가 일치하지 않습니다.");
            return "signup";
        }
        try{
            userService.create(userForm.getUserid(),userForm.getUsername(),userForm.getPassword1());
        }catch(DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("signFailed","이미 등록된 사용자입니다.");
            return "signup";
        }catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("signFailed",e.getMessage());
            return "signup";
        }
        return "redirect:/";
    }
}
