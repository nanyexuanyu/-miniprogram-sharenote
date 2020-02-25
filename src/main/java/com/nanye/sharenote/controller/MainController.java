package com.nanye.sharenote.controller;

import com.nanye.sharenote.bean.Note;
import com.nanye.sharenote.bean.WeiXin;
import com.nanye.sharenote.mapper.NoteMapper;
import com.nanye.sharenote.utils.HttpRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class MainController {

    @Autowired
    NoteMapper noteMapper;

    //获取openid
    @GetMapping("/getOpenid")
    @ResponseBody
    public Map decodeUserInfo(String code) {

        Map map = new HashMap();

        System.out.println(code);
        /////////////////////////////////////////////////////////////////////////自己定义的code

        //String code = "061Lg1Gj08x4Uo1kwUEj05BhGj0Lg1Gq";

        // 登录凭证不能为空
        if (code == null || code.length() == 0) {

            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }

        // 小程序唯一标识 (在微信小程序管理后台获取)
        String wxspAppid = WeiXin.APPID;
        // 小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = WeiXin.APP_SECRECT;
        // 授权（必填）
        String grant_type = WeiXin.AUTHORIZATION_CODE;

        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        // 请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type="
                + grant_type;
        // 发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);


        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            // 解析相应内容（转换成json对象）
            JSONObject json = new JSONObject(sr);
            // 获取会话密钥（session_key）
            String session_key = json.get("session_key").toString();
            // 用户的唯一标识（openid）
            String openid = (String) json.get("openid").toString();



            map.put( "session_key",session_key);
            map.put( "openId",openid );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    //上传笔记
    @ResponseBody
    @GetMapping("/submit")
    public int submitNote(Note note){
        System.out.println("submit成功");
        System.out.println(note);
        noteMapper.insertNote(note);
        System.out.println(note.getId());

        return note.getId();
    }

    //查询我上传的笔记
    @ResponseBody
    @GetMapping("/myupload")
    public List<Note> myUpload(String openid){
        System.out.println(openid);
        List<Note> list = noteMapper.selectNoteByOpenid(openid);
        System.out.println(list);
        return list;
    }

    //根据id查询笔记，并增加浏览数量
    @ResponseBody
    @GetMapping("/getNoteById")
    public Note getNoteById(Integer id){
        Note note = noteMapper.selectNoteById(id);
        /*浏览量pageViews++*/
        noteMapper.updatePageViewsById(id);
        return note;
    }

    //查询所有上传的笔记
    @ResponseBody
    @GetMapping("/allupload")
    public List<Note> allUpload(){
        List<Note> list = noteMapper.selectAllNote();
        System.out.println(list);
        return list;
    }

    //删除我上传的笔记
    @ResponseBody
    @GetMapping("/deleteNote")
    public Integer deleteNote(Integer id){
        System.out.println(id);
        return noteMapper.deleteNoteById(id);

    }

    //根据笔记id查询图片url
    @ResponseBody
    @GetMapping("/getImgUrlListById")
    public List<String> getImgUrlListById(Integer id){
        List<String> list = noteMapper.selectImgUrlListById(id);
        System.out.println(list);
        return list;
    }

    //查询笔记收藏前三甲
    @ResponseBody
    @GetMapping("/rankNotes")
    public List<Note> rankNotes(){
        List<Note> list = noteMapper.selectNotesByStar();
        return list;
    }

}