package com.pagination.pagination.repository

import com.pagination.pagination.MySQLConnection
import com.pagination.pagination.common.Constants
import com.pagination.pagination.domain.Reservation
import com.pagination.pagination.domain.Reservations
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class ReservationRepository {

    val logger = LoggerFactory.getLogger(ReservationRepository::class.java)

    fun addDummyReservation(numParam: Int) {
        MySQLConnection.init()
        Reservation.new {
            title = "${numParam}번째 제목입니다."
            num = numParam
        }
    }

    fun findReservationsByNowPage(pageNum: Int): List<Reservation> {
        MySQLConnection.init()
        var reservations: List<Reservation> = emptyList()
        transaction {
            val query = Reservations.selectAll()
//            .orderBy(Reservations.id to SortOrder.DESC)
                .limit(
                    Constants.ReservationPagination.ROWS_COUNT,
                    Constants.ReservationPagination.ROWS_COUNT * (pageNum - 1)
                )
            reservations = Reservation.wrapRows(query).toList()
            reservations.forEach {
                logger.info(it.title)
            }
        }
        return reservations
    }
}
