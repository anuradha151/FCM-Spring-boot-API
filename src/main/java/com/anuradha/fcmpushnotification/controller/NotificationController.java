package com.anuradha.fcmpushnotification.controller;

import com.anuradha.fcmpushnotification.dto.NotificationDTO;
import com.anuradha.fcmpushnotification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.TimeZone;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

//    public static void main(String[] args) {
//
//        Calendar cal = Calendar.getInstance();
//        long milliDiff = cal.get(Calendar.ZONE_OFFSET);
//        // Got local offset, now loop through available timezone id(s).
//        String[] ids = TimeZone.getAvailableIDs();
//        String name = null;
//        for (String id : ids) {
//            TimeZone tz = TimeZone.getTimeZone(id);
//            if (tz.getRawOffset() == milliDiff) {
//                // Found a match.
//                name = id;
//                break;
//            }
//        }
//        System.out.println(cal.getTime());
//
//    }

    @PostMapping("/to-topic")
    public ResponseEntity<?> sendToTopic(@RequestBody NotificationDTO notificationDTO) {
        System.out.println("date and time : " + notificationDTO.getDate());
        try {
            return notificationService.sendToTopic(notificationDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Message sending failed..!", HttpStatus.BAD_REQUEST);
        }
    }

}
