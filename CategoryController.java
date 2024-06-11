package com.wzz.controller;

import com.wzz.config.JsonResult;
import com.wzz.dto.CategorysDtoObj;
import com.wzz.entity.Category;
import com.wzz.service.impl.CategoryServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categoryServer")
public class CategoryController {

    @Resource
    private CategoryServiceImpl service;

    // 添加分类
    @PostMapping("/addCategory")
    public JsonResult addCategory(CategorysDtoObj categorysDtoObj) {
        try{
            Category category = new Category();
            if(categorysDtoObj.getFile() !=null) {
                String path = FileController.getSavePath(categorysDtoObj.getFile());
                category.setCategory_img(path);
            }
            category.setCategory_name(categorysDtoObj.getCategory_name());
            service.addCategory(category);
            return JsonResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.failResult(e.getMessage());
        }
    }

    @PostMapping("/deleteCategory")
    public JsonResult deleteCategory(@RequestBody Category category) {
        service.deleteCategory(category.getCategory_id());
        return JsonResult.success();
    }

    @PostMapping("/editCategory")
    @ApiOperation("修改分类")
    public JsonResult editCategory(CategorysDtoObj categorysDtoObj) {
        try{
            Category category = service.getById(categorysDtoObj.getCategory_id());
            if(categorysDtoObj.getFile() !=null) {
                String path = FileController.getSavePath(categorysDtoObj.getFile());
                category.setCategory_img(path);
            }
            category.setCategory_name(categorysDtoObj.getCategory_name());
            service.updateById(category);
            return JsonResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.failResult(e.getMessage());
        }
    }

    @GetMapping("/getCategoryImgPath")
    @ApiOperation("修改分类")
    public JsonResult getCategoryImgPath(Integer categoryId) {
        return JsonResult.result(service.getCategoryImgPath(categoryId));
    }
}
