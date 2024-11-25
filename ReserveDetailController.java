package com.museum.controller;


import com.museum.config.JsonResult;
import com.museum.config.PageResult;
import com.museum.damain.dto.ReserveQuery;
import com.museum.damain.po.MsReserveDetial;
import com.museum.service.impl.ReserveDetailService;
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
@RequestMapping("/reserveDetail")
@RequiredArgsConstructor
public class ReserveDetailController {

    @Resource
    ReserveDetailService reserveDetailService;

    @PostMapping("/listDetailReserve")
    public JsonResult listDetailReserve(@RequestBody ReserveQuery pageQuery) {
        PageResult<MsReserveDetial> result = reserveDetailService.listMsReserveDetail(pageQuery);
        return JsonResult.result(result);
    }

    @PostMapping("/addDetail")
    public JsonResult addDetail(@RequestBody MsReserveDetial detial) {
        try {
            reserveDetailService.addDetail(detial);
            return JsonResult.result("成功！");
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.failResult(e.getMessage());
        }
    }

    @PostMapping("/editDetail")
    public JsonResult editDetail(@RequestBody MsReserveDetial detial) {
        try {
            reserveDetailService.editDetail(detial);
            return JsonResult.result("成功！");
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.failResult(e.getMessage());
        }
    }

    @PostMapping("/delDetail")
    public JsonResult delDetail(@RequestBody MsReserveDetial msReserve) {
        try {
            reserveDetailService.delDetail(msReserve.getId());
            return JsonResult.result("成功！");
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.failResult(e.getMessage());
        }
    }
}
