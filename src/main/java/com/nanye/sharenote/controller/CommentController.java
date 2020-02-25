package com.nanye.sharenote.controller;

import com.nanye.sharenote.bean.Comment;
import com.nanye.sharenote.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentMapper commentMapper;

    //发表评论
    @ResponseBody
    @GetMapping("/commentSubmit")
    public int submitComment(Comment comment){
        return commentMapper.insertComment(comment);
    }

    //根据noteid查询该条笔记所有评论
    @ResponseBody
    @GetMapping("/getCommentsById")
    public List<Comment> submitComment(Integer noteid){
        return commentMapper.selectCommentsById(noteid);
    }
}
