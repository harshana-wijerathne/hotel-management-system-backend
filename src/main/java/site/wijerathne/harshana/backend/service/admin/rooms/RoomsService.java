package site.wijerathne.harshana.backend.service.admin.rooms;

import site.wijerathne.harshana.backend.dto.RoomDto;
import site.wijerathne.harshana.backend.dto.RoomsResponseDto;

public interface RoomsService {
    boolean postRoom(RoomDto roomDto);
    RoomsResponseDto getAllRooms(int pageNumber);

    RoomDto getRoomById(Long id);
    public boolean updateRoom(Long id ,RoomDto roomDto);
    public boolean deleteRoom(Long id);
}
