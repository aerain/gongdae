package kr.ac.jejunu.sslab.gongdae;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    @GetMapping(value={"/", "/user/**", "/login"})
    public String home() {
        return "index";
    }
    @PostMapping("/signup")
    public String signup() { return "index"; }

}
