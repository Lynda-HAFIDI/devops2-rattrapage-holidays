package com.devops2.rattrapage.holiday;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolidayController {

    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/api/holidays/next")
    public HolidayResponse getNextHoliday() {
        return holidayService.getNextFrenchHoliday();
    }
}