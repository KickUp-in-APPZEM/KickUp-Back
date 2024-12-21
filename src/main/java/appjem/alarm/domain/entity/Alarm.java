package appjem.alarm.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@NoArgsConstructor
@Getter
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    private boolean active = true;

    private String title;


    @Builder
    public Alarm(LocalTime time, String title) {
        this.time = time;
        this.title = title;
    }

    public void changeActive() {
        this.active = !this.active;
    }

    public void update(LocalTime time, String title) {
        this.time = time;
        this.title = title;
    }
}
