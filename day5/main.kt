package day5

import day5.DaoServices.LoginDAO.createLoginTable
import day5.DaoServices.LoginDAO.getadmin
import day5.DaoServices.LoginDAO.insertLoginDetails
import day5.DaoServices.LoginDAO.validateLogin
import day5.DaoServices.MainDAO.connectionClose
import day5.DaoServices.MainDAO.createDatabase
import day5.DaoServices.MainDAO.getConnection
import day5.DaoServices.notesDAO.*
import day5.DaoServices.remindersDAO.checkReminders
import day5.DaoServices.remindersDAO.createReminderTable
import day5.DaoServices.remindersDAO.getTime
import day5.DaoServices.remindersDAO.insertReminder
import day5.DaoServices.usersDAO.createUserTable
import day5.DaoServices.usersDAO.insert
import day5.MODEL.Notes
import day5.MODEL.Users
import java.sql.Connection
import java.time.LocalTime
import java.util.*


public var conn: Connection? = null
internal var username = "root" // provide the username
internal var password = "rootqwe" // provide the corresponding password

fun main(args: Array<String>)
{
    val read = Scanner(System.`in`)
    var currentUserName : String = ""
    getConnection()
    createDatabase()
    println("\n")

    while (true){

        println("\n1.Registration \n2.Login")
        val i = read.nextInt()
        var validate = true
        when(i){
            1 -> {
                register()
            }

            2 -> {
                login()
            }
            else -> invalid()
        }
        if (i == 1)
            continue

        if(validate){
            break
        }
    }


    while (true){
        println("***************************************************************************")
        println("Choose from the Below Options: ")
        println(
            "1. Make a new Note\n2. Delete a note\n3. View Your Notebook\n" +
                    "4. Display notes By Username\n5. Edit your notes\n6. Set Reminder\n" +
                    "7. Check Reminders"+"\n8. Save And Exit"
        )
        val i = read.nextInt()
        read.nextLine()
        when(i){

            1 -> {
                inputNewNote()
            }

            2 -> {
                delNote()
            }

            3 -> {
                displayNotes(getadmin())
            }

            4 -> {
                println("Enter the UserName to see public Notes made by them: ")
                val userName = read.nextLine()
                //check visibility
                displayNotes(userName)
            }

            5 -> {
                edit()
            }
            6 -> {
                reminder()
            }

            7 ->{
                checkReminders()
            }

            8-> {
                println("********************************")
                println("Application is Closing...GoodBye")
                System.exit(0)
            }

            else -> println("Enter a Valid Option!")
        }
    }

    connectionClose()

}
fun register()
{
    val sc= Scanner(System.`in`)
    println("Enter your name")
    val name=sc.nextLine()
    println("Enter your User_name")
    val u_name=sc.nextLine()
    println("Enter your Email")
    val email=sc.nextLine()
    println("Enter your Password")
    val passwd=sc.nextLine()
    val obj=Users(name, u_name, email, passwd)
    createUserTable()
    insert(obj.u_name, obj.passwd, obj.name, obj.email)
    createLoginTable()
    insertLoginDetails(obj.u_name, obj.passwd)
    login()

}
fun login()
{
    val sc= Scanner(System.`in`)
    println("Enter your User_name")
    val cuname=sc.nextLine()
    println("Enter your Password")
    val cpasswd=sc.nextLine()
    validateLogin(cuname, cpasswd)

}
fun invalid()
{
    println("You have entered invalid input. Try Again!")

}
fun inputNewNote()
{
    val sc= Scanner(System.`in`)
    println("Enter a unique NoteID: ")
    val notenum = sc.nextInt()

    println("Enter Your Note: ")
    val note = readLine()
    println()
    val curUser= getadmin()
    val obj = note?.let { notenum?.let { it1 -> Notes(it1, it, curUser) } }
    createNotesTable()
    if (obj != null) {
        insertNewNote(obj.notenum, obj.note, curUser, obj.created_time)
    }
}
fun delNote()
{
    val sc= Scanner(System.`in`)
    println("Enter the NoteID to be Deleted: ")
    val del = sc.nextInt()
    deleteNote(del)
}
fun edit()
{
    val sc= Scanner(System.`in`)
    println("Enter the NoteID to edit your notes: ")
    val ed = sc.nextInt()
    println("Enter your note")
    val edNote = readLine()
    if (edNote != null) {
        editNote(ed, edNote)
    }
    println("Edit is saved")
}
fun reminder()
{
    val sc = Scanner(System.`in`)
    println("Enter NoteID to set reminder to")
    val notenum=sc.nextInt();
    val ob = Notes(notenum)
    println("Enter time (minutes):")
    val minute=sc.nextInt();
    getTime(notenum)
    val time= getTime(notenum)
    val value: LocalTime = time.plusMinutes(minute.toLong())
    createReminderTable()
    insertReminder(notenum, getadmin(), value.toString())


}
















