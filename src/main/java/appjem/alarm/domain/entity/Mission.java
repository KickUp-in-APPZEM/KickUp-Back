package appjem.alarm.domain.entity;

import appjem.alarm.domain.MissionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private MissionType type;
    private String question;
    private String answer;


    public Mission(MissionType type, String question, String answer) {
        this.type = type;
        this.question = question;
        this.answer = answer;
    }

    public boolean checkAnswer(String userAnswer) {
        return this.answer.equalsIgnoreCase(userAnswer.trim());
    }
}
