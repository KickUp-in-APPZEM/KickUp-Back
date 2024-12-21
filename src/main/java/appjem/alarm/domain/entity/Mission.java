package appjem.alarm.domain.entity;

import appjem.alarm.domain.MissionType;
import lombok.Getter;

@Getter
public class Mission {
    private final MissionType type;
    private final String question;
    private final String answer;

    public Mission(MissionType type, String question, String answer) {
        this.type = type;
        this.question = question;
        this.answer = answer;
    }

    public boolean checkAnswer(String userAnswer) {
        return this.answer.equalsIgnoreCase(userAnswer.trim());
    }
}

