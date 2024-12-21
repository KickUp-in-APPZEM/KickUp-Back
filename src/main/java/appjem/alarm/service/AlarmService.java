package appjem.alarm.service;

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
    public void update(Long id, LocalTime time, String title){
        Alarm alarm = findById(id);
        if(alarm != null){
            alarm.update(time, title);
        }else{
            throw new IllegalArgumentException("존재하지 않는 알람입니다");
        }
    }
}
