package com.pagination.pagination.service

import com.pagination.pagination.common.Constants
import com.pagination.pagination.dto.GetReservationResults
import com.pagination.pagination.repository.ReservationRepository
import org.springframework.stereotype.Service

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
) {

    fun findReservationsByNowPage(pageNum: Int): GetReservationResults {
        val reservations = reservationRepository.findReservationsByNowPage(pageNum)
        val nextFirstPage = calculateFirstPage(pageNum)
        val nextLastPage = calculateLastPage(pageNum)

        return GetReservationResults(reservations, nextFirstPage, nextLastPage)
    }

    private fun calculateFirstPage(pageNum: Int): Int {
        return (pageNum / Constants.ReservationPagination.PAGE_COUNT) * Constants.ReservationPagination.PAGE_COUNT + 1
    }

    private fun calculateLastPage(pageNum: Int): Int {
        return (pageNum / Constants.ReservationPagination.PAGE_COUNT) * Constants.ReservationPagination.PAGE_COUNT + Constants.ReservationPagination.PAGE_COUNT
    }
}
