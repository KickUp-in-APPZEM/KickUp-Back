package appjem.alarm.service;

import appjem.alarm.domain.UpdateAlarmRequest;
import appjem.alarm.domain.entity.Alarm;
import appjem.alarm.domain.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;

    public Alarm save(Alarm alarm, MultipartFile mp3File) throws IOException {
        byte[] mp3Data = null;
        if (mp3File != null && !mp3File.isEmpty()) {
            mp3Data = mp3File.getBytes();
        }

        Alarm newAlarm = Alarm.builder()
                .time(alarm.getTime())
                .title(alarm.getTitle())
                .mp3Data(mp3Data)
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

    @Scheduled(fixedRate = 60000)
    public void checkAlarms() {
        LocalTime currentTime = LocalTime.now();
        List<Alarm> alarms = alarmRepository.findAll();

        for (Alarm alarm : alarms) {
            if (alarm.getTime().equals(currentTime)) {
                triggerAlarm(alarm);
            }
        }
    }

    private void triggerAlarm(Alarm alarm) {
        System.out.println("알람 울림: " + alarm.getTitle() + " - " + alarm.getTime());
        try {
            byte[] mp3Data = alarm.getMp3Data();
            if (mp3Data != null && mp3Data.length > 0) {
                InputStream byteArrayInputStream = new ByteArrayInputStream(mp3Data);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } else {
                System.out.println("MP3 데이터가 없습니다.");
            }
        } catch (Exception e) {
            System.err.println("알람 소리 재생 중 오류 발생: " + e.getMessage());
        }
    }
}
