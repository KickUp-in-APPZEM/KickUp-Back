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
                                      @RequestParam(value = "mp3File", required = false) MultipartFile mp3File) throws IOException {
        // 서비스에서 알람 저장
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

    @PutMapping("/{id}")
    public ResponseEntity<String> changeActive(@PathVariable Long id) {
        log.info("Change active for Alarm ID: {}", id);
        alarmService.changeActive(id);
        return ResponseEntity.ok("변경완료");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        alarmService.delete(id);
        return ResponseEntity.ok("삭제완료");
    }

    @PutMapping("/{id}")
    public Alarm update(@RequestBody UpdateAlarmRequest request) {
        return alarmService.update(request);
    }
}
