package com.pagination.pagination.controller

import com.pagination.pagination.domain.Reservation
import com.pagination.pagination.dto.GetReservationResults
import com.pagination.pagination.service.ReservationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ReservationController(
    private val reservationService: ReservationService
) {

    @GetMapping("/getReservations")
    fun getReservations(@RequestParam pageNum: Int): GetReservationResults {
        return reservationService.findReservationsByNowPage(pageNum)
    }
}
