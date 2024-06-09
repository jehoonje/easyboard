package com.study.easyboard.springmvc.chap05.api;

import com.study.easyboard.springmvc.chap04.common.Search;
import com.study.easyboard.springmvc.chap04.entity.Board;
import com.study.easyboard.springmvc.chap04.service.BoardService;
import com.study.easyboard.springmvc.chap05.dto.request.LoginDto;
import com.study.easyboard.springmvc.chap05.dto.request.SignUpDto;
import com.study.easyboard.springmvc.chap05.dto.response.LoginUserInfoDto;
import com.study.easyboard.springmvc.chap05.entity.Member;
import com.study.easyboard.springmvc.chap05.service.LoginResult;
import com.study.easyboard.springmvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BoardService boardService;

    // 회원가입 양식 열기
    @GetMapping("/sign-up")
    public void signUp() {
        log.info("/members/sign-up GET : forwarding to sign-up.jsp");
        // return "members/sign-up";
    }

    // 회원가입 요청 처리
    @PostMapping("/sign-up")
    public String signUp(@Validated SignUpDto dto) {
        log.info("/members/sign-up POST ");
        log.debug("parameter: {}", dto);

        boolean flag = memberService.join(dto);

        if (flag) {
            // 계정에 대한 Board 페이지 생성
            boardService.createBoardForUser(dto.getAccount());
            return "redirect:/members/sign-in";
        } else {
            return "redirect:/members/sign-up";
        }
    }

    // 아이디, 이메일 중복검사 비동기 요청 처리
    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<?> check(String type, String keyword) {
        boolean flag = memberService.checkIdentifier(type, keyword);
        log.debug("{} 중복체크 결과? {}", type, flag);
        return ResponseEntity.ok().body(flag);
    }

    // 로그인 양식 열기
    @GetMapping("/sign-in")
    public String signIn(HttpSession session, @RequestParam(required = false) String redirect) {
        session.setAttribute("redirect", redirect);
        log.info("/members/sign-in GET : forwarding to sign-in.jsp");
        return "members/sign-in";
    }

    // 로그인 요청 처리
    @PostMapping("/sign-in")
    public String signIn(LoginDto dto, RedirectAttributes ra, HttpServletRequest request) {
        log.info("/members/sign-in POST");
        log.debug("parameter: {}", dto);

        // 세션 얻기
        HttpSession session = request.getSession();

        LoginResult result = memberService.authenticate(dto, session);

        ra.addFlashAttribute("result", result);

        if (result == LoginResult.SUCCESS) {
            // 혹시 세션에 리다이렉트 URL이 있다면
            String redirect = (String) session.getAttribute("redirect");
            if (redirect != null) {
                session.removeAttribute("redirect");
                return "redirect:" + redirect;
            }
            return "redirect:/"; // 로그인 성공시
        }

        return "redirect:/members/sign-in";
    }

    @GetMapping("/sign-out")
    public String signOut(HttpSession session) {
        // 세션에서 로그인 기록 삭제
        session.removeAttribute("login");
        // 세션을 초기화 (reset)
        session.invalidate();
        // 홈으로 보내기
        return "redirect:/";
    }

    @GetMapping("/discover")
    public String discover(Model model, String account, String nickName) {
        List<LoginUserInfoDto> uList = memberService.findAll(account, nickName);
        model.addAttribute("users", uList);
        return "members/discover";
    }

    @GetMapping("/{account}")
    public String memberPage(@PathVariable String account, Model model) {
        // 회원 정보 조회
        Member member = memberService.findByAccount(account);
        if (member == null) {
            return "redirect:/members/sign-up"; // 회원이 존재하지 않을 경우 회원 가입 페이지로 리다이렉트
        }

        // 회원의 보드 목록 조회
        List<Board> boards = boardService.findByWriter(account);

        model.addAttribute("member", member);
        model.addAttribute("boards", boards);
        return "member/index";
    }
}
