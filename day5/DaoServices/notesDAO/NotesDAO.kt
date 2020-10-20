package day5.DaoServices.notesDAO

import day5.DaoServices.conn
import java.sql.Statement
import day5.DaoServices.conn
import java.sql.PreparedStatement

fun createNotesTable(){
    var stmt: Statement? = null
    stmt = conn!!.createStatement()
    val sql = """
            CREATE TABLE IF NOT EXISTS notes (
            notenum INT NOT NULL UNIQUE AUTO_INCREMENT,
            username VARCHAR(255) NOT NULL,
            note VARCHAR(255) NOT NULL,
            created_time VARCHAR(255) NOT NULL)
        """.trimIndent()
    stmt!!.execute("use notedb;")
    stmt!!.execute(sql)
}


fun deleteNote(notenum: Int){

    val sql : String = "DELETE FROM notes WHERE notenum = '$notenum'"
    var stmt = conn!!.createStatement()
    stmt!!.execute(sql)
}
fun editNote(notenum : Int , newnote : String){

    val sql : String = """
            UPDATE notes 
            SET note = '$newnote'
            WHERE notenum = '$notenum'
        """.trimIndent()

    var stmt = conn!!.createStatement()
    stmt!!.execute(sql)
}
fun insertNewNote(notenum:Int, note : String, userName : String, createdTime : String){
    var sql: String = "INSERT INTO notes VALUES (?,?, ?, ?)";
    val statement: PreparedStatement? = conn?.prepareStatement(sql);
    statement?.setInt(1,notenum)
    //println(notenum)
    statement?.setString(2, userName)
    statement?.setString(3, note)
    statement?.setString(4, createdTime)
    statement?.executeUpdate()
}
fun displayNotes(userName: String){
    val sql : String = "SELECT * FROM notes WHERE username = '$userName'"
    var stmt = conn!!.createStatement()
    val resultSet = stmt!!.executeQuery(sql)
    while (resultSet.next()){
        println("NoteID: "+resultSet.getInt("notenum")+"UserName: "+resultSet.getString("username")+"\tNote: "+resultSet.getString("note")
                +"\tCreation Time: "+resultSet.getString("created_time"))
    }
}

