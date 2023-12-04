package dev.tran_vux.demo.controller;


import dev.tran_vux.demo.dto.hotel.CreateHotelRequest;
import dev.tran_vux.demo.dto.hotel.UpdateHotelRequest;
import dev.tran_vux.demo.dto.ResponseDto;
import dev.tran_vux.demo.entity.Hotel;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {
    // List hotel
    private static List<Hotel> hotels = new ArrayList<Hotel>();

    // 1. Tạo hotel
    @PostMapping
    public Hotel createHotel(@RequestBody CreateHotelRequest request) {
        Hotel hotel = new Hotel();

        hotel.setHotelId(request.getHotelId());
        hotel.setHotelName(request.getHotelName());
        hotel.setRate(request.getRate());

        hotels.add(hotel);

        return hotel;
    }


    // 2. Lấy danh sách hotel

    @GetMapping
    public List<Hotel> getHotels(@RequestParam(required = false) Integer rate,
                                 @RequestParam(required = false) Boolean status) {  // nullable
        if (rate != null && status == null) {
            return findHotelsByRate(rate);
        } else if (rate == null && status != null) {
            return findHotelsByStatus(status);
        }
        else if (rate != null && status != null) {
            return findHotelsByStatusAndRate(status, rate);
        }

        return hotels;
    }

    List<Hotel> findHotelsByRate(Integer rate) {
        List<Hotel> result = new LinkedList<>();

        for (Hotel hotel: hotels) {
            if (hotel.getRate() == rate) {
                result.add(hotel);
            }
        }

        return result;
    }

    List<Hotel> findHotelsByStatus(Boolean status) {
        List<Hotel> result = new LinkedList<>();

        for (Hotel hotel: hotels) {
            if (hotel.isStatus() == status) {
                result.add(hotel);
            }
        }

        return result;
    }

    List<Hotel> findHotelsByStatusAndRate(Boolean status, Integer rate) {
        List<Hotel> result = new LinkedList<>();

        for (Hotel hotel: hotels) {
            if (hotel.isStatus() == status && hotel.getRate() == rate) {
                result.add(hotel);
            }
        }

        return result;
    }


    // 3. Lấy thông tin của 1 hotel
    @GetMapping("/{hotelId}")
    public Hotel getHotel(@PathVariable String hotelId) {
        return findHotelById(hotelId);
    }

    // 4. Cập nhật thông tin 1 hotel
    @PutMapping("/{hotelId}")
    public Hotel updateHotel(@PathVariable String hotelId,
                             @RequestBody UpdateHotelRequest request) {
        Hotel hotel = findHotelById(hotelId);
        if (hotel == null) {
            return null;
        }

        hotel.setHotelName(request.getHotelName());
        hotel.setRate(request.getRate());
        hotel.setStatus(request.isStatus());

        return hotel;
    }


    // 5. Vô hiệu hoá 1 hotel
    @DeleteMapping("/{hotelId}")
    public ResponseDto disableHotel(@PathVariable String hotelId) {
        Hotel hotel = findHotelById(hotelId);
        if (hotel == null) {
            return new ResponseDto(false, "Hotel Not Found");
        }

        hotel.setStatus(false);
        return new ResponseDto(true, "Successful");
    }

//tìm kiếm hotel theo id
    private Hotel findHotelById(String hotelId) {
        for (Hotel hotel: hotels) {
            if (hotel.getHotelId().equals(hotelId)) {
                return hotel;
            }
        }

        return null;
    }
}