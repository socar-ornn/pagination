package com.pagination.pagination

import com.pagination.pagination.domain.Reservation
import com.pagination.pagination.domain.Reservations
import com.pagination.pagination.repository.ReservationRepository
import com.pagination.pagination.service.ReservationService
import org.assertj.core.api.Assertions
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PaginationApplicationTests {

	@Autowired
	lateinit var reservationService: ReservationService

	@Autowired
	lateinit var reservationRepository: ReservationRepository

	@BeforeEach
	fun initDataBase() {
		MySQLConnection.init()
	}

	@Test
	fun `13번째 페이지의 예약내역을 호출했을 때 게시글은 144번째 게시글이다`() {
		transaction {
			addLogger(StdOutSqlLogger)
			SchemaUtils.create(Reservations)
			repeat(1234) {
				reservationRepository.addDummyReservation(it)
			}

			// when
			val nowPage = 13

			// then
			val findReservationsByNowPage = reservationService.findReservationsByNowPage(nowPage)
			Assertions.assertThat(findReservationsByNowPage.nextFirstPage).isEqualTo(11)
			Assertions.assertThat(findReservationsByNowPage.nextLastPage).isEqualTo(20)
			Assertions.assertThat(findReservationsByNowPage.reservations[0].num).isEqualTo(144)
		}
	}
}
