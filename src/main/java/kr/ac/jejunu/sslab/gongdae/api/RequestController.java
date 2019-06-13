package kr.ac.jejunu.sslab.gongdae.api;

import kr.ac.jejunu.sslab.gongdae.model.Estimate;
import kr.ac.jejunu.sslab.gongdae.model.Request;
import kr.ac.jejunu.sslab.gongdae.model.RequestDetail;
import kr.ac.jejunu.sslab.gongdae.model.ReverseAuction;
import kr.ac.jejunu.sslab.gongdae.payload.ConfirmPayLoad;
import kr.ac.jejunu.sslab.gongdae.service.RequestService;
import kr.ac.jejunu.sslab.gongdae.service.ReverseAuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;
    private final ReverseAuctionService reverseAuctionService;

    @PostMapping(consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void requestAction(
        @RequestParam("title") String title,
        @RequestParam("place") String place,
        @RequestParam("vrImgUrl") MultipartFile vrImgUrl,
        @RequestParam("requestList") String requestList
        ) throws IOException {
        requestService.saveRequest(title, place, vrImgUrl, requestList);

    }

    @GetMapping
    public List<Request> getRequestList() {
        Long userId = 1L;
        return requestService.getRequestListByUserId(userId);
    }

    @GetMapping("/{id}")
    public Request getRequestById(@PathVariable Long id) {
        return requestService.getRequestById(id);
    }

    @PostMapping(path="/confirm", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void confirmRequest(@RequestBody ConfirmPayLoad confirmPayLoad) {
        requestService.confirmRequest(confirmPayLoad);
    }

    @GetMapping("/{id}/detail")
    public List<RequestDetail> findRequestDetailListByRequestId(@PathVariable Long id) {
        return requestService.getRequestDetailListByRequestId(id);
    }

    @GetMapping("/{id}/reverse")
    public List<ReverseAuction> getReverseAuctionList(@PathVariable("id") Long requestId) {
        return reverseAuctionService.getListByRequestId(requestId);
    }
}