package com.nanye.sharenote.controller;

import com.nanye.sharenote.bean.Note;
import com.nanye.sharenote.bean.Star;
import com.nanye.sharenote.mapper.StarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class StarController {

    @Autowired
    StarMapper starMapper;

    //根据isstar进行收藏或取消收藏
    @ResponseBody
    @GetMapping("/starNote")
    public String starNote(String openid,Integer noteid, Boolean isstar){
        if(isstar){
            starMapper.insertStar(openid, noteid);
            starMapper.addStar(noteid);
            return "收藏成功";
        }
        else{
            starMapper.deleteStar(openid, noteid);
            starMapper.reduceStar(noteid);
            return "取消收藏成功";
        }
    }

    //根据isstar进行收藏或取消收藏
    @ResponseBody
    @GetMapping("/getIsStar")
    public Boolean isStar(String openid,Integer noteid){
        if(starMapper.selectIsStar(openid, noteid) != null)
            return true;
        else
            return false;
    }

    //查询我收藏的笔记
    @ResponseBody
    @GetMapping("/myStar")
    public List<Note> myStar(String openid){
        List<Note> list = starMapper.selectAllStar(openid);
        return list;
    }
}
