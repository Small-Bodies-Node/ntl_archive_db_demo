/*
 * Copyright (C) 2012 TopCoder Inc., All Rights Reserved.
 */
package gov.nasa.pds.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    /**
     * Creates an instance of HomeController.
     */
    public HomeController() {
        // Empty
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/browse")
    public String browse() throws PDSControllerException {
        return "browsing";
    }

    @RequestMapping("/about")
    public String about() throws PDSControllerException {
        return "about";
    }

    @RequestMapping("/data")
    public String data() throws PDSControllerException {
        return "data";
    }

    @RequestMapping("/developer")
    public String developer() throws PDSControllerException {
        return "developer";
    }

    @RequestMapping("/related")
    public String related() throws PDSControllerException {
        return "related";
    }
   
    @RequestMapping("/contact")
    public String contact() throws PDSControllerException {
        return "contact";
    }

}
