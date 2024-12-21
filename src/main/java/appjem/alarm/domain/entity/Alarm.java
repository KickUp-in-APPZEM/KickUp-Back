package appjem.alarm.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long Id;

    private LocalTime time;

    private boolean active = true;

    private String title;

    @Builder
    public Alarm(LocalTime time, String title){
        this.time = time;
        this.title = title;
    }

    public void changeActive(){
        this.active = !this.active;
    }
}
