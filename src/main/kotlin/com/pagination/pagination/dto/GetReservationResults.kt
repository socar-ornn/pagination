package com.pagination.pagination.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.pagination.pagination.domain.Reservation


data class GetReservationResults(
    @JsonProperty
    val reservations: List<Reservation>,
    val nextFirstPage: Int,
    val nextLastPage: Int,
)
