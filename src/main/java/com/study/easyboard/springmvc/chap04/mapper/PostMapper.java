package com.study.easyboard.springmvc.chap04.mapper;

import com.study.easyboard.springmvc.chap04.entity.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {

    @Insert("INSERT INTO tbl_post (board_no, title, content, writer, view_count, reg_date_time) VALUES (#{boardNo}, #{title}, #{content}, #{writer}, #{viewCount}, NOW())")
    void save(Post post);

    @Select("SELECT * FROM tbl_post WHERE board_no = #{boardNo}")
    List<Post> findByBoardNo(@Param("boardNo") int boardNo);
}
