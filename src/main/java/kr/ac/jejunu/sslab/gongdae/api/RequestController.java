package kr.ac.jejunu.sslab.gongdae.api;

import kr.ac.jejunu.sslab.gongdae.model.Member;
import kr.ac.jejunu.sslab.gongdae.model.Request;
import kr.ac.jejunu.sslab.gongdae.model.RequestDetail;
import kr.ac.jejunu.sslab.gongdae.model.ReverseAuction;
import kr.ac.jejunu.sslab.gongdae.payload.ConfirmPayLoad;
import kr.ac.jejunu.sslab.gongdae.service.RequestService;
import kr.ac.jejunu.sslab.gongdae.service.ReverseAuctionService;
import kr.ac.jejunu.sslab.gongdae.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/request")
@RequiredArgsConstructor
public class RequestController {
    private final UserService userService;
    private final RequestService requestService;
    private final ReverseAuctionService reverseAuctionService;

    @PostMapping(consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity requestAction(
        @RequestParam("title") String title,
        @RequestParam("place") String place,
        @RequestParam("vrImgUrl") MultipartFile vrImgUrl,
        @RequestParam("requestList") String requestList
        ) throws IOException {
        requestService.saveRequest(title, place, vrImgUrl, requestList);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Request>> getRequestList() {
        Member member = userService.getCurrentUser();
        switch (member.getType()) {
            case 0:
                return ResponseEntity.ok(requestService.getRequestListByUserId(member.getId(), false));
            case 1:
                return ResponseEntity.ok(requestService.getRequestList(false));
            default:
                return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/submit")
    public ResponseEntity<List<Request>> getSubmittedList() {
        if(userService.getCurrentUser().getType() == 1)
            return ResponseEntity.ok(requestService.getRequestSubmitList(false));
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/done")
    public ResponseEntity<List<Request>> getRequestDoneList() {
        if(userService.getCurrentUser().getType() == 1)
            return ResponseEntity.ok(requestService.getRequestSubmitList(true));
        else
            return ResponseEntity.ok(requestService.getRequestListByUserId(userService.getCurrentUser().getId(),  true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(requestService.getRequestById(id));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path="/confirm", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity confirmRequest(@RequestBody ConfirmPayLoad confirmPayLoad) {
        requestService.confirmRequest(confirmPayLoad);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<List<RequestDetail>> findRequestDetailListByRequestId(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.getRequestDetailListByRequestId(id));
    }

    @GetMapping("/{id}/reverse")
    public ResponseEntity<List<ReverseAuction>> getReverseAuctionList(@PathVariable("id") Long requestId) {
        return ResponseEntity.ok(reverseAuctionService.getListByRequestId(requestId));
    }
}