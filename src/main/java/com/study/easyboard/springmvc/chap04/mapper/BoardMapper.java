package com.study.easyboard.springmvc.chap04.mapper;

import com.study.easyboard.springmvc.chap04.common.Search;
import com.study.easyboard.springmvc.chap04.dto.BoardFindAllDto;
import com.study.easyboard.springmvc.chap04.entity.Board;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 게시물 목록 조회
    List<BoardFindAllDto> findAll(Search page);

    // 게시물 상세 조회
    Board findOne(int boardNo);

//    // 게시물 등록
//    boolean save(Board board);

    // 게시물 삭제
    boolean delete(int boardNo);

    // 조회수 상승
    void upViewCount(int boardNo);

    void createBoardForUser(@Param("account") String account);
    Board findBoardByAccount(@Param("account") String account);

    // 총 게시물 수 조회
    int count(Search search, String account);

    @Select("SELECT * FROM tbl_board WHERE writer = #{writer}")
    List<Board> findByWriter(@Param("writer") String writer);

    @Insert("INSERT INTO tbl_board (account, title, reg_date_time) VALUES (#{account}, #{title}, NOW())")
    int save(Board board);

    @Select("SELECT * FROM tbl_board WHERE account = #{account}")
    Board findByAccount(@Param("account") String account);

    List<BoardFindAllDto> findAllByAccount(Search page, String account);
}
