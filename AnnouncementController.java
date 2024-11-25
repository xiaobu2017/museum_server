package com.museum.controller;


import com.museum.config.JsonResult;
import com.museum.damain.po.MsAnnouncement;
import com.museum.damain.query.PageQuery;
import com.museum.service.impl.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器（公告controller）
 * </p>
 *
 * @since 2023-12-19
 */
@RestController
@RequestMapping("/announcement")
@RequiredArgsConstructor
public class AnnouncementController {

    @Resource
    AnnouncementService announcementService;

    @PostMapping("/listMsAnnouncement")
    public JsonResult listDicTyp(@RequestBody PageQuery pageQuery) {
        return JsonResult.result(announcementService.listMsAnnouncement(pageQuery));
    }

    @PostMapping("/listMsAnnouncementTop")
    public JsonResult listMsAnnouncementTop(@RequestBody PageQuery pageQuery) {
        try {
            return JsonResult.result(announcementService.listMsAnnouncementTop(pageQuery));
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }

    @PostMapping("/addMsAnnouncement")
    public JsonResult delDic(@RequestBody MsAnnouncement msAnnouncement) {
        try {
            announcementService.addMsAnnouncement(msAnnouncement);
            return JsonResult.result("成功！");
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }

    @PostMapping("/editMsAnnouncement")
    public JsonResult editMsAnnouncement(@RequestBody MsAnnouncement msAnnouncement) {
        try {
            announcementService.editMsAnnouncement(msAnnouncement);
            return JsonResult.result("成功！");
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }

    @PostMapping("/delMsAnnouncement")
    public JsonResult delMsAnnouncement(@RequestBody MsAnnouncement msAnnouncement) {
        try {
            announcementService.delMsAnnouncement(msAnnouncement.getId());
            return JsonResult.result("成功！");
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }
}
