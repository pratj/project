package day5.DaoServices.visibility

import day5.DaoServices.conn
import java.sql.PreparedStatement
import java.sql.Statement

fun createVisibilityTable(){
    var stmt: Statement? = null
    stmt = conn!!.createStatement()
    val sql = """
            CREATE TABLE IF NOT EXISTS visibility (
            notenum INT NOT NULL UNIQUE,
            username VARCHAR(255) NOT NULL,
            visibility VARCHAR(255) NOT NULL)
        """.trimIndent()
    stmt!!.execute("use notedb;")
    stmt!!.execute(sql)
}

