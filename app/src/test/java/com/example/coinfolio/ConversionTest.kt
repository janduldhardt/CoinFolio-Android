package com.example.coinfolio

import com.example.coinfolio.utils.Converters
import org.junit.Test
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ConversionTest {
    @Test
    fun DateFormatIsoTest(){
        val date = ZonedDateTime.now()
        val converter = Converters()
        val dateFormatted = converter.zonedDateTimeToString(date)
        assert(!dateFormatted.contains(Regex("[.#$\\[]]*")))
    }
}