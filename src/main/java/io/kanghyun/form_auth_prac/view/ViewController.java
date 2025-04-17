package io.kanghyun.form_auth_prac.view;

import io.kanghyun.form_auth_prac.app.MemberService;
import io.kanghyun.form_auth_prac.dto.SignUpForm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "sign_up";
    }

    @PostMapping("/signup")
    public String doSignup(SignUpForm signUpForm) {
        memberService.save(signUpForm);
        return "redirect:/";
    }

    @GetMapping("/signin")
    public String showSignInForm() {
        return "sign_in";
    }
}
