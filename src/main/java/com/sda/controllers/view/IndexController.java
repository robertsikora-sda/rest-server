package com.sda.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Homepage controller.
 */
@Controller
class IndexController {

    @RequestMapping("/")
    String index() {
        return "index";
    }

}
