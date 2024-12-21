package appjem.alarm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAnswerRequest {
    private String question;
    private String answer;
}
