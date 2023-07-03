package com.techshopbe.controller;

import com.techshopbe.dto.CouponDTO;
import com.techshopbe.dto.StringResponseDTO;
import com.techshopbe.exception.CouponNotFoundException;
import com.techshopbe.service.CouponService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/coupons")
public class CouponController {
    private final CouponService couponService;

    @PostMapping(value = "")
    @PreAuthorize("hasAuthority('1')")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> addNewCoupon(@RequestBody CouponDTO dto) throws ParseException {
        return ResponseEntity.status(HttpStatus.CREATED).body(couponService.addCoupon(dto));
    }
    @GetMapping(value = "")
    @PreAuthorize("hasAuthority('1')")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> getAllCoupon(){
        return ResponseEntity.status(HttpStatus.CREATED).body(couponService.findAllCoupon());
    }
    @GetMapping(value = "/{couponID}")
    public ResponseEntity<Object> getCouponByID(@PathVariable int couponID){
        return ResponseEntity.status(HttpStatus.OK).body(couponService.findCouponByID(couponID));
    }
    @PutMapping(value = "/{couponID}")
    @PreAuthorize("hasAuthority('1')")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> updateCoupon(@RequestBody CouponDTO couponDTO, @PathVariable int couponID) throws ParseException {
        return ResponseEntity.ok(couponService.updateCoupon(couponID, couponDTO));
    }

    @DeleteMapping(value = "/{couponID}")
    @PreAuthorize("hasAuthority('1')")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> deleteProduct(@PathVariable int couponID){
        couponService.deleteCoupon(couponID);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
    }
}
