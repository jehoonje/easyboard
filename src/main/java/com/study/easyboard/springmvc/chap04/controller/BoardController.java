package com.study.easyboard.springmvc.chap04.controller;

import com.study.easyboard.springmvc.chap04.common.PageMaker;
import com.study.easyboard.springmvc.chap04.common.Search;
import com.study.easyboard.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.easyboard.springmvc.chap04.dto.BoardListResponseDto;
import com.study.easyboard.springmvc.chap04.dto.BoardWriteRequestDto;
import com.study.easyboard.springmvc.chap04.entity.Board;
import com.study.easyboard.springmvc.chap05.dto.response.LoginUserInfoDto;
import com.study.easyboard.springmvc.chap04.service.BoardService;
import com.study.easyboard.springmvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;



@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

//    private final BoardRepository repository;
    private final BoardService bs;


    @GetMapping("/list")
    public String list(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/members/sign-in";
        }

        String account = principal.getName();
        return "redirect:/board/" + account;
    }

    @GetMapping("/{account}")
    public String viewUserBoard(@PathVariable String account, @ModelAttribute("s") Search page, Model model) {
        Board board = bs.findBoardByAccount(account);
        if (board == null) {
            // 보드가 없으면 에러 페이지로 리디렉트
            throw new ResourceNotFoundException("Board not found for account: " + account);
        }

        List<BoardListResponseDto> bList = bs.findList(page, account);
        PageMaker maker = new PageMaker(page, bs.getCount(page, account));

        model.addAttribute("bList", bList);
        model.addAttribute("maker", maker);
        model.addAttribute("board", board);

        return "board/list";
    }
//
//    // 1. 목록 조회 요청 (/board/list : GET)
//    @GetMapping("/list")
//    public String list(@ModelAttribute("s") Search page, Model model) {
//        System.out.println("/board/list GET");
//
//        // 서비스에게 조회 요청 위임
//        List<BoardListResponseDto> bList = bs.findList(page);
//        // 페이지 정보를 생성하여 JSP에게 전송
//        PageMaker maker = new PageMaker(page, bs.getCount(page));
//
//        // 3. JSP파일에 해당 목록데이터를 보냄
//        model.addAttribute("bList", bList);
//        model.addAttribute("maker", maker);
////        model.addAttribute("s", page);
//
//        return "board/list";
//    }


    // 2. 게시글 쓰기 양식 화면 열기 요청 (/board/write : GET)
    @GetMapping("/write")
    public String write() {
        System.out.println("/board/write GET! ");
        return "board/write";
    }

    // 3. 게시글 등록 요청 (/board/write : POST)
    // -> 목록조회 요청 리다이렉션
    @PostMapping("/write")
    public String write(BoardWriteRequestDto dto, HttpSession session) {
        System.out.println("/board/write POST! ");

        // 1. 브라우저가 전달한 게시글 내용 읽기
        System.out.println("dto = " + dto);

        bs.insert(dto, session);

        return "redirect:/board/list";
    }

    // 4. 게시글 삭제 요청 (/board/delete : GET)
    // -> 목록조회 요청 리다이렉션
    @GetMapping("/delete")
    public String delete(int bno) {
        System.out.println("/board/delete GET");

        bs.remove(bno);

        return "redirect:/board/list";
    }

    // 5. 게시글 상세 조회 요청 (/board/detail : GET)
    @GetMapping("/detail")
    public String detail(int bno,
                         Model model,
                         HttpServletRequest request) {
        System.out.println("/board/detail GET");

        // 1. 상세조회하고 싶은 글번호를 읽기
        System.out.println("bno = " + bno);

        // 2. 데이터베이스로부터 해당 글번호 데이터 조회하기
        BoardDetailResponseDto dto = bs.detail(bno);

        // 3. JSP파일에 조회한 데이터 보내기
        model.addAttribute("bbb", dto);

        // 4. 요청 헤더를 파싱하여 이전 페이지의 주소를 얻어냄
        String ref = request.getHeader("Referer");
        model.addAttribute("ref", ref);

        return "board/detail";
    }



}
