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
    private final RandomMissionReposity randomMissionReposity;


    @GetMapping("/random")
    public Mission getRandomMission() {
        return missionService.getRandomMission();
    }

    @PostMapping("/check")
    public boolean checkAnswer(@RequestParam String userAnswer, @RequestParam Long missionId) {
        Mission mission = randomMissionReposity.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission not found with id: " + missionId));

        return missionService.checkAnswer(mission, userAnswer);
    }

}

