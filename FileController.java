package com.museum.controller;


import com.museum.config.JsonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2023-12-19
 */
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final static String FILE_UPLOAD_PATH = "D:\\upload\\";
    @PostMapping("/uploadFile")
    public JsonResult getdata(@RequestParam("file") MultipartFile[] file) {
        if(file == null || file.length<1) {
            JsonResult.failResult("文件上传失败");
        }
        if (file[0].isEmpty()){
            JsonResult.failResult("文件上传失败");
        }
        String fileName = file[0].getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        try {
            //保存文件
            byte[] bytes = file[0].getBytes();
            Path path = Paths.get(FILE_UPLOAD_PATH+newFileName);
            Files.write(path,bytes);
            return JsonResult.result(tempName);
        }catch (IOException e){
            e.printStackTrace();
        }
        return JsonResult.failResult("文件上传失败");
    }

    @GetMapping("/getPic")
    public void getPic(String name, HttpServletResponse response) {
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_UPLOAD_PATH + name);
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            outputStream.flush();
            outputStream.close();
            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
