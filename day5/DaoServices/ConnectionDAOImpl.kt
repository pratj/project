package day5.DaoServices


//db connection.kt


import java.sql.*
import java.util.*

var conn: Connection? = null
object MainDAO {



    fun getConnection(): Connection? {

        var connectionProps = Properties()
        connectionProps.put("user", "root")
        connectionProps.put("password", "rootqwe")
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()

             conn = DriverManager.getConnection(
                "jdbc:" + "mysql" + "://" +
                        "localhost" +
                        ":" + "3306" + "/" +
                        "",
                connectionProps
            )


        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } catch (ex: Exception) {
            // handle any errors
            ex.printStackTrace()
        }
        return conn
    }
    fun createDatabase(){

        var stmt : Statement? = null
        stmt = conn!!.createStatement()
        stmt!!.execute("CREATE DATABASE IF NOT EXISTS notedb;")
        stmt!!.execute("use notedb;")
    }

    fun connectionClose() {
        conn?.close()
    }
}

