package com.nanye.sharenote.controller;

import com.nanye.sharenote.bean.Img;
import com.nanye.sharenote.mapper.ImgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
public class FileController {

    @Autowired
    ImgMapper imgMapper;

    /**
     * 定义图片存放地址
     */
    private String pathurl = "47.100.10.51:8080/";

    /**
     * 上传图片
     * @param file 图片文件
     * @return 上传状态或图片名称
     */
    @PostMapping("/upload")
    public String getFile(@RequestParam("file") MultipartFile file, String noteid) {
        Img img = new Img();
        System.out.println("开始上传图片");
        System.out.println("noteid " + noteid);

        if (file.isEmpty()) {
            return "图片上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "C:\\Users\\nanye\\Desktop\\test\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            System.out.println("图片上传成功");
            img.setImageUrl(pathurl + fileName);
            img.setNoteid(noteid);
            imgMapper.insertImg(img);
            return fileName;
        } catch (IOException e) {
            System.out.println("图片上传失败");
            System.out.println(e.getMessage());
        }
        return "上传失败！";
    }
    /**
     * 根据图片名称返回本地图片
     * @param name 图片名称
     * @return 返回本地图片
     */
    @GetMapping(value = "/pic", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPicture(@RequestParam("name") String name) {
        System.out.println("请求图片");
        String filePath = "C:\\Users\\96589\\Pictures\\";
        File file = new File(filePath + name);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            return bytes;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;

    }

}