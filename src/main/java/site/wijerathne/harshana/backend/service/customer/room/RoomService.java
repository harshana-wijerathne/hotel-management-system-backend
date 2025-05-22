package site.wijerathne.harshana.backend.service.customer.room;

import site.wijerathne.harshana.backend.dto.RoomsResponseDto;

public interface RoomService {
    RoomsResponseDto getAvailableRooms(int pageNumber);
}
