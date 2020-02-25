package com.nanye.sharenote.mapper;

import com.nanye.sharenote.bean.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    //上传note
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert note(openid, title, category, info, nickName, avatarUrl, createdTime, star) values(#{openid}, #{title}, #{category}, #{info}, #{nickName}, #{avatarUrl}, #{createdTime}, #{star})")
    int insertNote(Note note);

    //根据openid查询我的上传
    @Select("select id, title, info, createdTime from note where openid = #{openid} order by id desc")
    List<Note> selectNoteByOpenid(String openid);

    //根据id查询笔记详细内容
    @Select("select nickName, avatarUrl, star, info, pageViews from note where id = #{id}")
    Note selectNoteById(Integer id);

    //查询所有上传的note
    @Select("select id, title, info, createdTime, category from note order by id desc")
    List<Note> selectAllNote();

    //删除我上传的note
    @Delete("delete from note where id = #{id}")
    Integer deleteNoteById(Integer id);

    //根据noteid查询imageUrl
    @Select("select imageUrl from img where noteid = #{id}")
    List<String> selectImgUrlListById(Integer id);

    //根据id浏览量pageViews++
    @Update("update note set pageViews = pageViews + 1 where id = #{id}")
    void updatePageViewsById(Integer id);

    //查询star排行前三甲
    @Select("select id, title, star, createdTime from note order by star desc limit 3 ")
    List<Note> selectNotesByStar();

}
