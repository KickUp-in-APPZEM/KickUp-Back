package appjem.alarm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class UpdateAlarmRequest {
    private Long id;
    private String title;
    private LocalTime time;
}
