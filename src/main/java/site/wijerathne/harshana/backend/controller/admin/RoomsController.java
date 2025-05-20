package site.wijerathne.harshana.backend.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.wijerathne.harshana.backend.dto.RoomDto;
import site.wijerathne.harshana.backend.entity.Room;
import site.wijerathne.harshana.backend.service.admin.rooms.RoomService;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomService roomService;

    @PostMapping("/room")
    public ResponseEntity<?> postRoom(@RequestBody RoomDto roomDto) {
        System.out.println("POST ROOM");
         boolean success = roomService.postRoom(roomDto);
        if (success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

}
