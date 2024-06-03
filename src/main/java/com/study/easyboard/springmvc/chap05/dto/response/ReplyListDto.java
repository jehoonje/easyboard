package com.study.easyboard.springmvc.chap05.dto.response;

import com.study.easyboard.springmvc.chap04.common.PageMaker;
import lombok.*;

import java.util.List;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
/*
    {
        "replies": [
            {}, {}, {}
        ]
    }
 */
public class ReplyListDto {

    /*
        [
            {}, {}, {}
        ]
     */
    private PageMaker pageInfo;
    private List<ReplyDetailDto> replies;
}
