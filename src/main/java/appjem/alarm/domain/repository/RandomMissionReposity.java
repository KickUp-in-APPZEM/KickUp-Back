package appjem.alarm.domain.repository;

import appjem.alarm.domain.MissionType;
import appjem.alarm.domain.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RandomMissionReposity extends JpaRepository<Mission, Long>{
}
