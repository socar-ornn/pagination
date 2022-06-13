package com.pagination.pagination.dto

import com.pagination.pagination.domain.Reservation

class ReservationDto(
    val title: String,
    val num: Int,
) {
    companion object {
        fun fromReservation(reservation: Reservation): ReservationDto {
            return ReservationDto(title = reservation.title, num = reservation.num)
        }
    }
}
