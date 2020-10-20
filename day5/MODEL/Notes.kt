package day5.MODEL

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalQueries.localDate




data class Notes(var notenum: Int, var note: String = "", var username: String = "")
{
    var created_time: String
    var current:LocalDateTime
    val time:LocalTime

    init {
        current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter)
        created_time=formatted.toString()
        time = current.toLocalTime()

    }


}