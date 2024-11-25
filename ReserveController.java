package com.museum.controller;


import com.museum.config.JsonResult;
import com.museum.config.PageResult;
import com.museum.damain.dto.ReserveQuery;
import com.museum.damain.po.MsCollection;
import com.museum.damain.po.MsReserve;
import com.museum.damain.query.PageQuery;
import com.museum.service.impl.CollectionService;
import com.museum.service.impl.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2023-12-19
 */
@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReserveController {

    @Resource
    ReserveService reserveService;

    @PostMapping("/listMsReserve")
    public JsonResult listMsReserve(@RequestBody ReserveQuery pageQuery) {
        PageResult<MsReserve> result = reserveService.listMsReserve(pageQuery);
        return JsonResult.result(result);
    }

    @PostMapping("/addMsReserve")
    public JsonResult addMsReserve(@RequestBody MsReserve msReserve) {
        try {
            reserveService.addMsReserve(msReserve);
            return JsonResult.result("成功！");
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.failResult(e.getMessage());
        }
    }

    @PostMapping("/editMsReserve")
    public JsonResult editMsReserve(@RequestBody MsReserve msReserve) {
        try {
            reserveService.editMsReserve(msReserve);
            return JsonResult.result("成功！");
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }

    @PostMapping("/delMsReserve")
    public JsonResult delMsReserve(@RequestBody MsReserve msReserve) {
        try {
            reserveService.delMsReserve(msReserve.getId());
            return JsonResult.result("成功！");
        }catch (Exception e){
            return JsonResult.failResult(e.getMessage());
        }
    }
}
