package site.wijerathne.harshana.backend.dto;


import lombok.Data;

import java.util.List;

@Data
public class RoomsResponseDto {

    private List<RoomDto> rooms;
    private Integer totalPages;
    private Integer pageNumber;
}
