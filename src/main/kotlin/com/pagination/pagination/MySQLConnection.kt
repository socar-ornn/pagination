package com.pagination.pagination

import org.jetbrains.exposed.sql.Database

class MySQLConnection {

    companion object {
        fun init() {
            //jdbc:h2:tcp://localhost/~/test;DB_CLOSE_DELAY=-1
            //"jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;"
            Database.connect(
                url = "jdbc:h2:tcp://localhost/~/pagination",
                driver = "org.h2.Driver",
                user = "sa"
            )

        }
    }
}
