package appjem.alarm.service;

import appjem.alarm.domain.UpdateAlarmRequest;
import appjem.alarm.domain.entity.Alarm;
import appjem.alarm.domain.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;

    public Alarm save(Alarm alarm){

        Alarm newAlarm = Alarm.builder()
                .time(alarm.getTime())
                .title(alarm.getTitle())
                .build();

        return alarmRepository.save(newAlarm);
    }

    public List<Alarm> findAll() {
        return alarmRepository.findAll();
    }

    public Alarm findById(Long id) {
        return alarmRepository.findById(id).orElse(null);
    }

    @Transactional
    public void changeActive(Long id){
        Alarm alarm = findById(id);
        alarm.changeActive();
    }

    @Transactional
    public void delete(Long id){
        alarmRepository.deleteById(id);
    }

    @Transactional
    public Alarm update(UpdateAlarmRequest request){
        Alarm alarm = alarmRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Alarm not found"));
        alarm.update(request.getTime(), request.getTitle());
        return alarmRepository.save(alarm);
    }
}
