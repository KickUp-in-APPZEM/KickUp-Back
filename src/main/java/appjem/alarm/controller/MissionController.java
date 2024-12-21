package appjem.alarm.controller;

import appjem.alarm.domain.AnswerRequest;
import appjem.alarm.domain.CreateAnswerRequest;
import appjem.alarm.domain.MissionType;
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

    @PostMapping("/create")  // 새로운 미션 생성 API
    public Mission createMission(@RequestBody CreateAnswerRequest request) {
        Mission mission = new Mission(MissionType.MATH, request.getQuestion(), request.getAnswer());
        return randomMissionRepository.save(mission);
    }

    @PostMapping("/check")
    public boolean checkAnswer(@RequestBody AnswerRequest request) {
        Mission mission = randomMissionRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Mission not found with id: " + request.getId()));

        return missionService.checkAnswer(mission, request.getUserAnswer());
    }


}

