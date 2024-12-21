package appjem.alarm.controller;

import appjem.alarm.domain.UpdateAlarmRequest;
import appjem.alarm.domain.entity.Alarm;
import appjem.alarm.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Alarm")
@Slf4j
public class AlarmController {

    private final AlarmService alarmService;

    @PostMapping
    public ResponseEntity<Alarm> save(@RequestBody Alarm alarm,
                                      @RequestPart(value = "mp3File", required = false) MultipartFile mp3File) throws IOException {
        if (mp3File == null) {
            log.error("파일이 전송되지 않았습니다.");
        } else {
            log.info("파일이 전송되었습니다: " + mp3File.getOriginalFilename());
        }
        Alarm savedAlarm = alarmService.save(alarm, mp3File);
        return ResponseEntity.ok(savedAlarm);
    }
    @GetMapping
    public List<Alarm> getAll() {
        return alarmService.findAll();
    }

    @GetMapping("/{id}")
    public Alarm getById(@PathVariable Long id) {
        return alarmService.findById(id);
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<String> changeActive(@PathVariable Long id) {
        log.info("Change active for Alarm ID: {}", id);
        alarmService.changeActive(id);
        return ResponseEntity.ok("변경완료");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            alarmService.delete(id);
            return ResponseEntity.ok("삭제완료");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("알람을 찾을 수 없습니다.");
        }
    }

    @PutMapping("/{id}")
    public Alarm update(@RequestBody UpdateAlarmRequest request) {
        return alarmService.update(request);
    }
}
