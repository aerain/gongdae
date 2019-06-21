package kr.ac.jejunu.sslab.gongdae.api;

import kr.ac.jejunu.sslab.gongdae.model.ReverseAuction;
import kr.ac.jejunu.sslab.gongdae.payload.RequestReversePayLoad;
import kr.ac.jejunu.sslab.gongdae.service.ReverseAuctionService;
import kr.ac.jejunu.sslab.gongdae.service.UserService;
import kr.ac.jejunu.sslab.gongdae.vo.ReverseAuctionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reverse")
public class ReverseAuctionController {
    private final ReverseAuctionService reverseAuctionService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ReverseAuction> getReverseAuctionById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reverseAuctionService.getReverseAuctionbyId(id));
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")

    // for company
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity requestReverse(@RequestBody RequestReversePayLoad requestReversePayLoad) {
        try {
            reverseAuctionService.saveReverse(requestReversePayLoad.getRequestId(), requestReversePayLoad.getEstimateList());
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
