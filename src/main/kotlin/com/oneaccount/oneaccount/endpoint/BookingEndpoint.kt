package com.oneaccount.oneaccount.endpoint

import com.oneaccount.oneaccount.model.Booking
import com.oneaccount.oneaccount.model.Film
import com.oneaccount.oneaccount.repository.BookingRepository
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.util.*

@RestController
@Validated
class BookingEndpoint(val bookingRepository: BookingRepository) {

    /**
     * Returns the bookingId, filmId and date of booking
     */
    @GetMapping("/getBooking/{bookingId}")
    fun getBooking(@PathVariable bookingId: Int): Booking {
        return bookingRepository.findById(bookingId).get()
    }

    @PostMapping("/cancelBooking/{bookingId}")
    fun cancelBooking(@PathVariable bookingId: Int) {
        bookingRepository.deleteById(bookingId)
    }

    @PostMapping("/createBooking/{filmId}/{date}/{seat}")
    fun createBooking(
        @Min(1) @Max(5)
        @PathVariable filmId: Int, @PathVariable date: LocalDate,
        @Min(1) @Max(100)
        @PathVariable seat: Int
    ): Booking {
        return bookingRepository.save(Booking(film = Film(id = filmId), date = date, seat = seat))
    }

    /**
     * Return a list of bookings for a given date
     */
    @GetMapping("/listBookings/{date}")
    fun listBookings(@PathVariable date: LocalDate): List<Booking> {
        return bookingRepository.findAllByDate(date)
    }
}
