package kr.ac.jejunu.sslab.gongdae;

import kr.ac.jejunu.sslab.gongdae.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ReactController {

    private final UserService userService;

    @GetMapping(value={"/user/**", "/login", "/company/**", "/signup"})
    public String react() {
        return "index";
    }
    @GetMapping
    public String home() {
        return Optional.ofNullable(userService.getCurrentUser()).map(user -> {
            switch(user.getType()) {
                case 0: return "redirect:/user";
                case 1: return "redirect:/company";
                default: return "index";
            }
        }).orElse("index");
    }

}
