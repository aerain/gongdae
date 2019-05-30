package kr.ac.jejunu.sslab.gongdae.api;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import kr.ac.jejunu.sslab.gongdae.FileUploadService;
import kr.ac.jejunu.sslab.gongdae.Request;
import kr.ac.jejunu.sslab.gongdae.dao.RequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class RequestController {

    private final FileUploadService fileUploadService;
    private final RequestDao requestDao;

    @Autowired
    public RequestController(FileUploadService fileUploadService, RequestDao requestDao) {
        this.fileUploadService = fileUploadService;
        this.requestDao = requestDao;
    }

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
    public void requestAction(
        @RequestParam String title,
        @RequestParam String place,
        @RequestParam MultipartFile vrImgUrl,
        HttpServletRequest request
        ) throws IOException {

        String imagePath = fileUploadService.uploadFile(vrImgUrl);
        Request req = Request.builder().title(title).place(place).vrImgPath(imagePath).build();
        requestDao.add();
    }
}