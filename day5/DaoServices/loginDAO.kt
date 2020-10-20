package day5.DaoServices

import day5.DaoServices.MainDAO.getConnection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

object LoginDAO {

    fun createLoginTable(){
        var stmt: Statement? = null
        stmt = conn!!.createStatement()
        val sql = """
            CREATE TABLE IF NOT EXISTS login (
            username VARCHAR(255) PRIMARY KEY,
            password VARCHAR(255) NOT NULL)
        """.trimIndent()
        stmt!!.execute(sql)
    }

    fun insertLoginDetails(userName : String, password : String){

        val sql: String = "INSERT INTO login VALUES (?, ?)";
        val statement: PreparedStatement? = conn?.prepareStatement(sql);
        statement?.setString(1, userName)
        statement?.setString(2, password)

        statement?.executeUpdate()
    }

//    fun validateLogin(userName: String, password: String) : Boolean{
//
//        val sql: String = "SELECT password FROM login WHERE username = '$userName'"
//        val statement : Statement? = conn!!.createStatement()
//        var resultSet: ResultSet? = null
//        var validated : Boolean = false
//        if (statement != null) {
//            resultSet = statement.executeQuery(sql)
//        }
//
//        if (resultSet != null) {
//            if(!resultSet.isBeforeFirst)
//                println("Check Your Username")
//            else{
//                resultSet.next()
//                if(password == resultSet.getString("password")){
//                    validated = true
//                }
//            }
//        }
//
//        if(validated){
//            println("Welcome Back, You are now Logged In!")
//        }else{
//            println("Check Your Password")
//        }
//        return validated
//
//    }
fun validateLogin(userName: String, password: String) : Boolean{

    //conn = getConnection()
    var statement:Statement?=null
    var resultSet: ResultSet?=null
    var validated: Boolean = false
    try {

        val sql: String = "SELECT * FROM users WHERE username = '$userName' and password = '$password';"
        statement = conn!!.createStatement()


        if (statement != null) {
            resultSet = statement.executeQuery(sql)
        }

        if (resultSet != null) {
            if (!resultSet.isBeforeFirst)
                println("Check Your Username")
            else {
                resultSet.next()
                if (password == resultSet.getString("password")) {
                    validated = true
                }
            }
        }


    }catch (ex: SQLException) {ex.printStackTrace()}
    finally {
        if(resultSet!=null)
        {
            try {
                resultSet.close();
            }catch (ex:SQLException)
            {

            }
        }
    }
    if (validated) {
        println("Logged In!!\n")
        println("Start taking Notes\n")
        setadmin(userName)
    } else {
        println("Check Your Password")
    }
    return validated
}
    private lateinit var admin:String
    fun setadmin(userName: String)
    {
        admin=userName
    }
    fun getadmin(): String {
        return admin
    }
}