package com.study.easyboard.springmvc.chap04.entity;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private int postId;
    private int boardNo;  // 외래 키로 참조할 컬럼
    private String title;
    private String content;
    private String writer;
    private int viewCount;
    private LocalDateTime regDateTime;
}
