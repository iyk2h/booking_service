package com.example.session_login.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.session_login.domain.login.LoginService;
import com.example.session_login.domain.member.Member;
import com.example.session_login.web.SessionConstants;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute @Validated LoginForm loginForm, BindingResult bindingResult, @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginform";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        
        if(loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공 처리
        HttpSession session = request.getSession();
        session.setAttribute(SessionConstants.LOGIN_MEMBER, loginMember);
        
        //로그인 성공 처리
        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
