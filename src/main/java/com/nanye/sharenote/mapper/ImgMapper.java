package com.nanye.sharenote.mapper;

import com.nanye.sharenote.bean.Img;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImgMapper {

    @Insert("insert into img(imageUrl, noteid) values(#{imageUrl}, #{noteid})")
    public void insertImg(Img img);
}
