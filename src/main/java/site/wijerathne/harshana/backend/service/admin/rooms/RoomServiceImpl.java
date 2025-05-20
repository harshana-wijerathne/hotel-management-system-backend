package site.wijerathne.harshana.backend.service.admin.rooms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.wijerathne.harshana.backend.dto.RoomDto;
import site.wijerathne.harshana.backend.entity.Room;
import site.wijerathne.harshana.backend.repository.RoomRepository;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public boolean postRoom(RoomDto roomDto) {
        try{
            Room room = new Room();
            room.setName(roomDto.getName());
            room.setPrice(roomDto.getPrice());
            room.setType(roomDto.getType());
            room.setAvailable(true);

            roomRepository.save(room);
            return true;


        }catch (Exception e){
            return false;
        }
    }



}
