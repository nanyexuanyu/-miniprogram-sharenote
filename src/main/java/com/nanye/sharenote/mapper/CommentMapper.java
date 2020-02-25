package com.nanye.sharenote.mapper;

import com.nanye.sharenote.bean.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment(content,createdTime,noteid,openid,nickName,avatarUrl) values(#{content},#{createdTime},#{noteid},#{openid},#{nickName},#{avatarUrl})")
    int insertComment(Comment comment);

    @Select("select content,createdTime,nickName,avatarUrl from comment where noteid = #{noteid} order by id desc")
    List<Comment> selectCommentsById(Integer noteid);
}
