package pl.agh.loc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Rafal on 2016-03-15.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(method= RequestMethod.GET)
    public String springMvcTest(Model model){
        model.addAttribute("info", "LEAGUE OF COMPARERS!!!");
        return "glowny";
    }
}
//@TODO ZADANIE 9 !!
