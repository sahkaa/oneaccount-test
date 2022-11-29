package com.oneaccount.oneaccount.repository

import com.oneaccount.oneaccount.model.Booking
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface BookingRepository : JpaRepository<Booking, Int> {

    @Query("select b from Booking b where b.date = ?1")
    fun findAllByDate(@Param("date") date: LocalDate): List<Booking>
}
