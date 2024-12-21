package appjem.alarm.controller;

import appjem.alarm.domain.entity.Mission;
import appjem.alarm.domain.repository.RandomMissionReposity;
import appjem.alarm.service.RandomMissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mission")
public class MissionController {

    private final RandomMissionService missionService;
    private final RandomMissionReposity randomMissionRepository;


    @GetMapping("/random")
    public Mission getRandomMission() {
        return missionService.getRandomMission();
    }

    @PostMapping("/check")
    public boolean checkAnswer(@RequestParam String userAnswer, @RequestParam Long id) {
        Mission mission = randomMissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mission not found with id: " + id));

        return missionService.checkAnswer(mission, userAnswer); // checkAnswer 메서드가 서비스에서 구현되어야 합니다.
    }

}

