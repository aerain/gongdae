package kr.ac.jejunu.sslab.gongdae;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"/", "/request/**", "/list/**", "/reverse/**"})
public class HomeController {
    @GetMapping
    public String home() {
        return "index";
    }
}
