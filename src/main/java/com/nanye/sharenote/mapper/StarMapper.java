package com.nanye.sharenote.mapper;

import com.nanye.sharenote.bean.Note;
import org.apache.ibatis.annotations.*;

import javax.validation.constraints.Max;
import java.util.List;

@Mapper
public interface StarMapper {

    //进行收藏
    @Insert("insert into star(openid, noteid) values(#{openid}, #{noteid})")
    void insertStar(String openid,Integer noteid);

    //取消收藏
    @Delete("delete from star where openid = #{openid} and noteid = #{noteid}")
    void deleteStar(String openid,Integer noteid);

    //查询是否已被收藏
    @Select("select id from star where openid = #{openid} and noteid = #{noteid}")
    Integer selectIsStar(String openid,Integer noteid);

    //查询我的收藏
    @Select("select note.id, title, info, createdTime from note INNER JOIN star on note.id = star.noteid where star.openid = #{openid} order by id desc")
    List<Note> selectAllStar(String openid);

    @Update("update note set star = star + 1 where id = #{noteid}")
    void addStar(Integer noteid);

    @Update("update note set star = star - 1 where id = #{noteid}")
    void reduceStar(Integer noteid);
}
