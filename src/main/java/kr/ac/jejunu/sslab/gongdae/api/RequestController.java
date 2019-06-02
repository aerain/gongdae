package kr.ac.jejunu.sslab.gongdae.api;

import kr.ac.jejunu.sslab.gongdae.service.FileUploadService;
import kr.ac.jejunu.sslab.gongdae.dao.RequestDetailRepository;
import kr.ac.jejunu.sslab.gongdae.dao.RequestRepository;
import kr.ac.jejunu.sslab.gongdae.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api/request-auction")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @PostMapping(consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void requestAction(
//            @RequestBody String string
        @RequestParam("title") String title,
        @RequestParam("place") String place,
        @RequestParam("vrImgUrl") MultipartFile vrImgUrl,
        @RequestParam("requestList") String requestList
        ) throws IOException {
        requestService.saveRequest(title, place, vrImgUrl, requestList);
    }
}