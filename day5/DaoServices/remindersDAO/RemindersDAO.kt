package day5.DaoServices.remindersDAO

import day5.DaoServices.conn
import java.sql.PreparedStatement
import java.sql.Statement
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun checkReminders()
{
    val current_time= LocalTime.now()
    val sql : String = "SELECT * FROM reminder;"
    var stmt = conn!!.createStatement()
    val resultSet = stmt!!.executeQuery(sql)


      while (resultSet.next()){
        println("NoteID: "+resultSet.getInt("notenum")+"UserName: "+resultSet.getString("username")
                +"\tReminder Time: "+resultSet.getString("reminder_time")+"\tPresent Time: "+current_time)
    }
}
fun createReminderTable(){
    var stmt: Statement? = null
    stmt = conn!!.createStatement()
    val sql = """
            CREATE TABLE IF NOT EXISTS reminder (
            notenum INT NOT NULL UNIQUE,
            username VARCHAR(255) NOT NULL,
            reminder_time VARCHAR(255) NOT NULL)
        """.trimIndent()
    stmt!!.execute("use notedb;")
    stmt!!.execute(sql)
}
fun getTime(notenum: Int):LocalTime{
    val sql : String = "SELECT created_time FROM notes WHERE notenum = '$notenum'"
    var stmt = conn!!.createStatement()
    val resultSet = stmt!!.executeQuery(sql)
    var res:String

    resultSet.next()
    res = resultSet.getString("created_time")


    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
    return LocalTime.parse(res,formatter)

}
fun insertReminder(notenum:Int, userName : String, reminder_time : String){
    var sql: String = "INSERT INTO reminder VALUES (?,?, ?)";
    val statement: PreparedStatement? = conn?.prepareStatement(sql);
    statement?.setInt(1,notenum)
    //println(notenum)
    statement?.setString(2, userName)
    statement?.setString(3, reminder_time) //note
    //statement?.setString(4, reminder_time)
    statement?.executeUpdate()
}