package com.devops2.rattrapage.holiday;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Service
public class HolidayService {

    public HolidayResponse getNextFrenchHoliday() {
        int year = LocalDate.now().getYear();
        String url = "https://date.nager.at/api/v3/PublicHolidays/" + year + "/FR";

        RestTemplate restTemplate = new RestTemplate();
        List<Map<String, Object>> holidays = restTemplate.getForObject(url, List.class);

        return buildNextHolidayResponse(holidays, LocalDate.now(), year);
    }

    HolidayResponse buildNextHolidayResponse(List<Map<String, Object>> holidays, LocalDate today, int year) {
        if (holidays == null || holidays.isEmpty()) {
            return new HolidayResponse(
                    "FR",
                    year,
                    "Aucun jour férié trouvé",
                    "",
                    -1
            );
        }

        for (Map<String, Object> holiday : holidays) {
            LocalDate holidayDate = LocalDate.parse((String) holiday.get("date"));

            if (!holidayDate.isBefore(today)) {
                String name = (String) holiday.get("localName");
                long daysRemaining = ChronoUnit.DAYS.between(today, holidayDate);

                return new HolidayResponse(
                        "FR",
                        year,
                        name,
                        holidayDate.toString(),
                        daysRemaining
                );
            }
        }

        return new HolidayResponse(
                "FR",
                year,
                "Aucun jour férié trouvé",
                "",
                -1
        );
    }
}