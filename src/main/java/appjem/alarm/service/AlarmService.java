package appjem.alarm.service;

import appjem.alarm.domain.UpdateAlarmRequest;
import appjem.alarm.domain.entity.Alarm;
import appjem.alarm.domain.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;

    public Alarm save(Alarm alarm) {
        return alarmRepository.save(alarm);
    }

    public List<Alarm> findAll() {
        return alarmRepository.findAll();
    }

    public Alarm findById(Long id) {
        return alarmRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Alarm not found"));
    }

    @Transactional
    public void changeActive(Long id) {
        Alarm alarm = findById(id);
        alarm.changeActive();
    }

    @Transactional
    public void delete(Long id) {
        Alarm alarm = findById(id);
        alarmRepository.delete(alarm);
    }

    @Transactional
    public Alarm update(UpdateAlarmRequest request) {
        Alarm alarm = findById(request.getId());
        alarm.update(request.getTime(), request.getTitle());
        return alarmRepository.save(alarm);
    }

    @Scheduled(fixedRate = 60000)
    public void checkAlarms() {
        LocalTime currentTime = LocalTime.now();
        List<Alarm> alarms = alarmRepository.findAll();
        for (Alarm alarm : alarms) {
            if (isTimeToTrigger(alarm.getTime(), currentTime)) {
                triggerAlarm(alarm);
            }
        }
    }

    public boolean isTimeToTrigger(LocalTime alarmTime, LocalTime currentTime) {
        return alarmTime.getHour() == currentTime.getHour() &&
                alarmTime.getMinute() == currentTime.getMinute();
    }

    public ResponseEntity<String> triggerAlarm(Alarm alarm) {
        return ResponseEntity.ok("알람 실행");
    }
}
