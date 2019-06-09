package kr.ac.jejunu.sslab.gongdae.api;

import kr.ac.jejunu.sslab.gongdae.model.ReverseAuction;
import kr.ac.jejunu.sslab.gongdae.service.ReverseAuctionService;
import kr.ac.jejunu.sslab.gongdae.vo.ReverseAuctionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reverse")
public class ReverseAuctionController {
    private final ReverseAuctionService reverseAuctionService;

    @GetMapping("/{id}")
    public ReverseAuction getReverseAuctionById(@PathVariable Long id) {
        return reverseAuctionService.getReverseAuctionbyId(id);
    }
}
