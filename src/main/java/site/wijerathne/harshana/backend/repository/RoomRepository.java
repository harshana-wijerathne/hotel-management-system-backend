package site.wijerathne.harshana.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.wijerathne.harshana.backend.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
