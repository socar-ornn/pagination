package com.pagination.pagination.domain

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column

object Reservations: IntIdTable("reservation_info", "id") {
    val title: Column<String> = varchar("title", 1024)
    val number: Column<Int> = integer("number")
}

class Reservation(id: EntityID<Int>): IntEntity(id) {
    var title: String by Reservations.title
    var num: Int by Reservations.number

    companion object : IntEntityClass<Reservation>(Reservations)
}
