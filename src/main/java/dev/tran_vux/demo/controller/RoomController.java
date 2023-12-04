package dev.tran_vux.demo.controller;

import dev.tran_vux.demo.dto.ResponseDto;
import dev.tran_vux.demo.dto.room.CreateRoom;
import dev.tran_vux.demo.dto.room.UpdateRoom;
import dev.tran_vux.demo.entity.Room;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("api/v1/rooms")
public class RoomController {
    private static List<Room> rooms = new ArrayList<>();

    // Create room
    @PostMapping
    public Room createRoom(@RequestBody CreateRoom request){
        Room room = new Room();

        room.setRoomId(request.getRoomId());
        room.setEmployeeId(request.getEmployeeId());
        room.setNumber(request.getNumber());
        room.setPrice(request.getPrice());
        room.setType(request.getType());
        room.setStatus(request.isStatus());

        rooms.add(room);

        return room;
    }

    // Get list room
    @GetMapping
    public List<Room> getRooms(@RequestParam(required = false) Boolean status){
        if (status != null){
            return findRoomByStatus(status);
        }

        return rooms;
    }

    List<Room> findRoomByStatus(boolean status){
        List<Room> result = new LinkedList<>();

        for (Room room:rooms){
            if (room.isStatus() == status){
                result.add(room);
            }
        }

        return result;
    }


    // Get one hotel
    @GetMapping("/roomId")
    public Room getRoom(@PathVariable String roomId){
        return findRoomById(roomId);
    }

    // Update room
    @PutMapping("/roomId")
    public Room updateRoom(@PathVariable String roomId,
                           @RequestBody UpdateRoom request){

        Room room = findRoomById(roomId);
        if (room == null){
            return null;
        }

        room.setEmployeeId(request.getEmployeeId());
        room.setNumber(request.getNumber());
        room.setPrice(request.getPrice());
        room.setType(request.getType());
        room.setStatus(request.isStatus());

        return room;
    }

    // Delete room
    @DeleteMapping("/roomId")
    public ResponseDto deleteRoom(@PathVariable String roomId){
        Room room = findRoomById(roomId);
        if (room == null){
            return new ResponseDto(false,"Room Not Found!");
        }
        rooms.remove(roomId);
        return new ResponseDto(true,"Room Deleted!");
    }

    // Get room by id
    private Room findRoomById(String roomId){
        for (Room room:rooms){
            if (room.getRoomId().equals(roomId)){
                return room;
            }
        }

        return null;
    }
}
