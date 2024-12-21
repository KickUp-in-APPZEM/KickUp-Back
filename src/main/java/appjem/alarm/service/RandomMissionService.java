package appjem.alarm.service;

import appjem.alarm.domain.MissionType;
import appjem.alarm.domain.entity.Mission;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomMissionService {

    private static final List<Mission> MISSIONS = List.of(
            new Mission(MissionType.MATH, "5 + 3는?", "8"),
            new Mission(MissionType.MATH, "12 * 7은?", "84"),
            new Mission(MissionType.QUIZ, "한국의 수도는?", "서울"),
            new Mission(MissionType.QUIZ, "지구에서 가장 큰 대륙은?", "아시아"),
            new Mission(MissionType.TYPING, "다음 문장을 입력하세요: '안녕하세요!'", "안녕하세요!"),
            new Mission(MissionType.TYPING, "다음 문장을 입력하세요: '자바는 재미있다.'", "자바는 재미있다.")
    );

    public Mission getRandomMission() {
        int randomIndex = ThreadLocalRandom.current().nextInt(MISSIONS.size());
        return MISSIONS.get(randomIndex);
    }

    public boolean checkAnswer(Mission mission, String userAnswer) {
        return mission.checkAnswer(userAnswer);
    }
}
