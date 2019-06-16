package kr.ac.jejunu.sslab.gongdae.api;

import kr.ac.jejunu.sslab.gongdae.model.Member;
import kr.ac.jejunu.sslab.gongdae.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class MemberController {

    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Member> signup(@RequestBody Member member) {
        userService.saveUser(member);
        return ResponseEntity.ok(member);
    }
}
