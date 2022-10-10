package com.zty.community.controller;

import com.zty.community.dto.PaginationDTO;
import com.zty.community.service.PagiNationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @BelongsProject: community
 * @BelongsPackage: com.zty.community.controller
 * @Author: zhangty
 * @CreateTime: 2022-09-03  23:01
 * @Description: TODO
 * @Version: 1.0
 */
@Controller

public class IndexController {
    @Autowired
    private PagiNationService pagiNationService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size){

        PaginationDTO paginationDTO = pagiNationService.getPaginationDTO(-1L, page, size);
        model.addAttribute("paginationDTO", paginationDTO);
        return "index";
    }
}
