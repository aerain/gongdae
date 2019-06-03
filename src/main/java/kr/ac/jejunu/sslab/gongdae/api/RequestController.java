package kr.ac.jejunu.sslab.gongdae.api;

import kr.ac.jejunu.sslab.gongdae.dao.UserRepository;
import kr.ac.jejunu.sslab.gongdae.model.Request;
import kr.ac.jejunu.sslab.gongdae.service.FileUploadService;
import kr.ac.jejunu.sslab.gongdae.dao.RequestDetailRepository;
import kr.ac.jejunu.sslab.gongdae.dao.RequestRepository;
import kr.ac.jejunu.sslab.gongdae.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

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
    public Map<String, Object> getRequestList() {
        Long userId = 1L;
        return new HashMap<>() {{
            put("data", requestService.getRequestListByUserId(userId));
        }};
    }

    @GetMapping("/{id}")
    public Map<String, Object> getRequestById(@PathVariable Long id) {
        Request request = requestService.getRequestById(id);
        return new HashMap<>() {{
            put("data", request);
        }};
    }
}