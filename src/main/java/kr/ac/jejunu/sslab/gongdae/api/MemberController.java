package kr.ac.jejunu.sslab.gongdae.api;

import kr.ac.jejunu.sslab.gongdae.model.Company;
import kr.ac.jejunu.sslab.gongdae.model.Member;
import kr.ac.jejunu.sslab.gongdae.payload.MemberPayLoad;
import kr.ac.jejunu.sslab.gongdae.service.CompanyService;
import kr.ac.jejunu.sslab.gongdae.service.GongdaeUserDetailsService;
import kr.ac.jejunu.sslab.gongdae.service.UserService;
import kr.ac.jejunu.sslab.gongdae.vo.SessionIdVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class MemberController {

    private final UserService userService;
    private final CompanyService companyService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Member> signup(@RequestBody MemberPayLoad memberPayLoad) {
        Member member = userService.saveUser(memberPayLoad.getMember());
        // if Sign Company Up
        if(memberPayLoad.getMember().getType() == 1) {
            companyService.saveCompany(Company.builder().member(member).description(memberPayLoad.getCompanyDescription()).build());
        }
        return ResponseEntity.ok(member);
    }

    @GetMapping("/id")
    public ResponseEntity<SessionIdVO> getCurrentSessionId() {
        return ResponseEntity.ok(SessionIdVO.builder()
                .id(userService.getCurrentSessionId()).build());
    }
}
