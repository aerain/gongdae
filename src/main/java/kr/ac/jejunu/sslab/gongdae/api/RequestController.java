package kr.ac.jejunu.sslab.gongdae.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class RequestController {
    @GetMapping("/request-auction")
    public Map<String, Object> test() {
        Map<String, Object> result = new HashMap<>();
        result.put("test", "get");
        return result;
    }
    @PostMapping(
        path="/request-auction", 
        consumes= {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public Map<String, Object> requestAction(
        @RequestParam String title, 
        @RequestParam String place,
        @RequestParam MultipartFile vrImgUrl
        ) {

        Map<String, Object> result = new HashMap<>();
        result.put("data", "hi!");
        return result;
    }
}