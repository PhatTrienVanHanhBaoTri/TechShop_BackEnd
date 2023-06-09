package com.techshopbe.controller;

import com.techshopbe.dto.CouponDTO;
import com.techshopbe.dto.StringResponseDTO;
import com.techshopbe.exception.CouponNotFoundException;
import com.techshopbe.service.impl.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/coupons")
public class CouponController {
    private final CouponService couponService;

    @PostMapping(value = "")
    public ResponseEntity<Object> addNewCoupon(@RequestBody CouponDTO dto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(couponService.addCoupon(dto));
        }
        catch (ParseException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StringResponseDTO("Ngày hết hạn không đúng định dạng vui lòng kiểm tra lại"));
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
    @GetMapping(value = "")
    public ResponseEntity<Object> getAllCoupon(){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(couponService.findAllCoupon());
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
    @GetMapping(value = "/{couponID}")
    public ResponseEntity<Object> getCouponByID(@PathVariable int couponID){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(couponService.findCouponByID(couponID));
        }
        catch (CouponNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringResponseDTO(e.getMessage()));
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
    @PutMapping(value = "/{couponID}")
    public ResponseEntity<Object> updateProduct(@RequestBody CouponDTO couponDTO, @PathVariable int couponID){
        try {
            return ResponseEntity.ok(couponService.updateCoupon(couponID, couponDTO));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @DeleteMapping(value = "/{couponID}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int couponID){
        try {
            log.trace("abc");
            couponService.deleteCoupon(couponID);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
}
