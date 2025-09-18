package za.co.simplitate.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.simplitate.springsecurity.dto.NoticeTO;
import za.co.simplitate.springsecurity.entities.Notice;
import za.co.simplitate.springsecurity.repositories.NoticeRepository;
import za.co.simplitate.springsecurity.util.GenericMapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class NoticesController {

    private final NoticeRepository noticeRepository;

    @GetMapping("/notices")
    public ResponseEntity<List<NoticeTO>> getNotices() {
        List<Notice> notices = noticeRepository.findAllActiveNotices();
        if (notices != null) {
            List<NoticeTO> noticeTOList =  notices.stream().map(GenericMapper::toNoticeTO).toList();
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(noticeTOList);
        } else {
            return null;
        }
    }

}
