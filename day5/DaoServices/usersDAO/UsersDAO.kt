package day5.DaoServices.usersDAO

import day5.DaoServices.conn
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement


    fun createUserTable()
    {
        var stmt: Statement? =null

        stmt = conn!!.createStatement()

        var sql="""CREATE TABLE IF NOT EXISTS users(
            username VARCHAR(255) PRIMARY KEY,
            password VARCHAR(255) NOT NULL,
            name VARCHAR(255) NOT NULL,
            email VARCHAR(255) NOT NULL)
        """.trimMargin()
        stmt!!.execute(sql)


    }
fun insert(username: String, password: String, fullName: String, email: String):Boolean
{
    var sql:String="INSERT INTO users (username, password, name, email) VALUES (?, ?, ?, ?)";
    var statement: PreparedStatement? = conn?.prepareStatement(sql);
    statement?.setString(1, username)
    statement?.setString(2, password)
    statement?.setString(3, fullName)
    statement?.setString(4, email)

    var rowsInserted: Int? = statement?.executeUpdate()
    if (rowsInserted != null) {
        if (rowsInserted > 0) {
            println("A new user was inserted successfully!");}
    }
    return true;
}

fun selectRecord(){
    val sql = "SELECT * FROM users"
    val statement: Statement = conn!!.createStatement()
    val result: ResultSet = statement.executeQuery(sql)

    var count = 0
    while (result.next()) {
        val name = result.getString(2)
        val pass = result.getString(3)
        val fullname = result.getString("fullname")
        val email = result.getString("email")
        val output = "User #%d: %s - %s - %s - %s"
        println(String.format(output, ++count, name, pass, fullname, email))
    }
}

fun updateRecord(username: String, password: String, fullName: String, email: String) {
    val sql = "UPDATE users SET password=?, fullname=?, email=? WHERE username=?"
    val statement = conn!!.prepareStatement(sql)
    statement.setString(1, password)
    statement.setString(2, fullName)
    statement.setString(3, email)
    statement.setString(4, username)

    val rowsUpdated = statement.executeUpdate()
    if (rowsUpdated > 0) {
        println("An existing user was updated successfully!")
    }
}
fun deleteRecord(username: String)
{
    val sql = "DELETE FROM users WHERE username=?"
    val statement = conn!!.prepareStatement(sql)
    statement.setString(1, username)

    val rowsDeleted = statement.executeUpdate()
    if (rowsDeleted > 0) {
        println("A user was deleted successfully!")
    }
}
