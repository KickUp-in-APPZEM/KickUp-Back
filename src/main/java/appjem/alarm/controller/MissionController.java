package appjem.alarm.controller;

import appjem.alarm.domain.AnswerRequest;
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
    public boolean checkAnswer(@RequestBody AnswerRequest request) {
        Mission mission = randomMissionRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Mission not found with id: " + request.getId()));

        return missionService.checkAnswer(mission, request.getUserAnswer());
    }


}

