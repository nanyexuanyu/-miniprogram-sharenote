package com.nanye.sharenote.mapper;

import com.nanye.sharenote.bean.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert note(openid, title, category, info, nickName, avatarUrl, createdTime) values(#{openid}, #{title}, #{category}, #{info}, #{nickName}, #{avatarUrl}, #{createdTime})")
    public int insertNote(Note note);

    @Select("select id, title, info, createdTime from note where openid = #{openid}")
    public List<Note> selectNoteByOpenid(String openid);

}
