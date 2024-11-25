package com.museum.controller;


import com.museum.config.JsonResult;
import com.museum.config.PageResult;
import com.museum.damain.dto.CollectionQuery;
import com.museum.damain.po.MsCollection;
import com.museum.damain.query.PageQuery;
import com.museum.service.impl.CollectionService;
import com.museum.service.impl.ReserveService;
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
@RequestMapping("/collect")
@RequiredArgsConstructor
public class CollectionController {

    @Resource
    CollectionService collectionService;

    @Resource
    ReserveService reserveService;

    @PostMapping("/getdata")
    public JsonResult getdata(@RequestBody CollectionQuery pageQuery) {
        PageResult<MsCollection> users = collectionService.listMsCollection(pageQuery);
        return JsonResult.result(users);
    }
    @PostMapping("/getdataTop")
    public JsonResult getdataTop(@RequestBody PageQuery pageQuery) {
        String menuNm = pageQuery.getMenuName();
        if(menuNm == null) {
            pageQuery.setMenuName("热门展览");
            menuNm = "热门展览";
        }
        PageResult pageResult = null;
        switch (menuNm){
            case "热门展览":
            case "近期展览":
                pageResult = collectionService.listMsCollectionTop(pageQuery);
                break;
            case "近期解说":
            case "热门解说":
                pageResult = reserveService.listTop(pageQuery);
                break;
        }
        return JsonResult.result(pageResult);
    }

    @PostMapping("/addColl")
    public JsonResult register(@RequestBody MsCollection msCollection) {
        try {
            collectionService.addColl(msCollection);
            return JsonResult.result("成功！");
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }

    @PostMapping("/editCollInfo")
    public JsonResult editUserInfo(@RequestBody MsCollection msCollection) {
        try {
            collectionService.editColl(msCollection);
            return JsonResult.result("成功！");
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }
    @PostMapping("/delColl")
    public JsonResult delColl(@RequestBody MsCollection msCollection) {
        try {
            if(msCollection.getId() == null) {
                throw new Exception("ID不允许为空！！！");
            }
            collectionService.delColl(msCollection.getId());
            return JsonResult.result("成功！");
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }
}
