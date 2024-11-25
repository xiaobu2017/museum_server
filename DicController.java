package com.museum.controller;


import com.museum.config.JsonResult;
import com.museum.damain.po.MsDic;
import com.museum.damain.query.PageQuery;
import com.museum.service.impl.DicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2023-12-19
 */
@RestController
@RequestMapping("/dic")
@RequiredArgsConstructor
public class DicController {

    @Resource
    DicService dicService;

    @PostMapping("/listDicTyp")
    public JsonResult listDicTyp(@RequestBody PageQuery pageQuery) {
        return JsonResult.result(dicService.listDicTyp());
    }

    @PostMapping("/listDicByTyp")
    public JsonResult listDicByTyp(@RequestBody MsDic dic) {
        try {
            List<MsDic> dics = dicService.listDicByTyp(dic.getDicTyp());
            return JsonResult.result(dics);
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }

    @PostMapping("/delDic")
    public JsonResult delDic(@RequestBody MsDic dic) {
        try {
            dicService.delDic(dic.getId());
            return JsonResult.result("成功！");
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }
    @PostMapping("/addDic")
    public JsonResult addDic(@RequestBody MsDic dic) {
        try {
            dicService.addDic(dic);
            return JsonResult.result("成功！");
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }
}
