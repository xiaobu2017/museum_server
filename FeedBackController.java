package com.museum.controller;


import com.museum.config.JsonResult;
import com.museum.config.PageResult;
import com.museum.damain.dto.FeedBackQuery;
import com.museum.damain.po.FeedBack;
import com.museum.service.impl.FeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2023-12-19
 */
@RestController
@RequestMapping("/feedBack")
@RequiredArgsConstructor
public class FeedBackController {

    @Resource
    FeedBackService feedBackService;

    @PostMapping("/listFeedBackByUser")
    public JsonResult listFeedBackByUser(@RequestBody FeedBackQuery pageQuery) {
        return JsonResult.result(feedBackService.listFeedBackByUser(pageQuery));
    }

    @PostMapping("/listAllFeedBack")
    public JsonResult listAllFeedBack(@RequestBody FeedBackQuery pageQuery) {
        try {
            PageResult<FeedBack> data = feedBackService.listAllFeedBack(pageQuery);
            return JsonResult.result(data);
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }

    @PostMapping("/delFeedBack")
    public JsonResult delDic(@RequestBody FeedBack feedBack) {
        try {
            feedBackService.delFeedBack(feedBack.getId());
            return JsonResult.result("成功！");
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }
    @PostMapping("/addFeedBack")
    public JsonResult addFeedBack(@RequestBody FeedBack feedBack) {
        try {
            feedBackService.addFeedBack(feedBack);
            return JsonResult.result("成功！");
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }

    @PostMapping("/editFeedBack")
    public JsonResult editFeedBack(@RequestBody FeedBack feedBack) {
        try {
            feedBackService.editFeedBack(feedBack);
            return JsonResult.result("成功！");
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }
}
