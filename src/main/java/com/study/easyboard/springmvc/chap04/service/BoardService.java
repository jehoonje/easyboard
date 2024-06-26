package com.study.easyboard.springmvc.chap04.service;

import com.study.easyboard.springmvc.chap04.common.Search;
import com.study.easyboard.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.easyboard.springmvc.chap04.dto.BoardFindAllDto;
import com.study.easyboard.springmvc.chap04.dto.BoardListResponseDto;
import com.study.easyboard.springmvc.chap04.dto.BoardWriteRequestDto;
import com.study.easyboard.springmvc.chap04.entity.Board;
import com.study.easyboard.springmvc.chap04.mapper.BoardMapper;
import com.study.easyboard.springmvc.chap05.mapper.ReplyMapper;
import com.study.easyboard.springmvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardMapper boardMapper;
    private final ReplyMapper replyMapper;

    public void createBoardForUser(String account) {
        boardMapper.createBoardForUser(account);
    }

    public Board findBoardByAccount(String account) {
        return boardMapper.findBoardByAccount(account);
    }


    // 목록 조회 요청 중간처리
    public List<BoardListResponseDto> findList(Search page, String account) {

        List<BoardFindAllDto> boardList = boardMapper.findAllByAccount(page, account);

        // 조회해온 게시물 리스트에서 각 게시물들의 조회수를 확인하여
        // 조회수가 5이상인 게시물에 특정 마킹
        List<BoardListResponseDto> dtoList = boardList.stream()
                .map(b -> new BoardListResponseDto(b))
                .collect(Collectors.toList());

        return dtoList;
    }

//    // 목록 조회 요청 중간처리
//    public List<BoardListResponseDto> findList(Search page) {
//
//        List<BoardFindAllDto> boardList = boardMapper.findAll(page);
//
//        // 조회해온 게시물 리스트에서 각 게시물들의 조회수를 확인하여
//        // 조회수가 5이상인 게시물에 특정 마킹
//        List<BoardListResponseDto> dtoList = boardList.stream()
//                .map(b -> new BoardListResponseDto(b))
//                .collect(Collectors.toList());
//
//        return dtoList;
//    }

    // 등록 요청 중간처리
    public boolean insert(BoardWriteRequestDto dto, HttpSession session) {
        Board b = dto.toEntity();
        // 계정명을 엔터티에 추가 - 세션에서 계정명 가져오기
        b.setAccount(LoginUtil.getLoggedInUserAccount(session));

        return boardMapper.save(b);
    }

    // 삭제 요청 중간처리
    public boolean remove(int boardNo) {
        return boardMapper.delete(boardNo);
    }

    // 상세 조회 요청 중간처리
    public BoardDetailResponseDto detail(int bno) {
        Board b = boardMapper.findOne(bno);
        if (b != null) boardMapper.upViewCount(bno);


        BoardDetailResponseDto responseDto = new BoardDetailResponseDto(b);

        return responseDto;
    }

    // 특정 계정의 총 게시물 수 조회
    public int getCount(Search search, String account) {
        return boardMapper.count(search, account);
    }

    public List<Board> findByWriter(String writer) {
        return boardMapper.findByWriter(writer);
    }
}
