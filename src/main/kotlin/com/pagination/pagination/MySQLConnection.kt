package com.pagination.pagination

import org.jetbrains.exposed.sql.Database

class MySQLConnection {

    companion object {
        fun init() {
            Database.connect(
                url = "jdbc:h2:tcp://localhost/~/pagination",
                driver = "org.h2.Driver",
                user = "sa"
            )
        }
    }
}
