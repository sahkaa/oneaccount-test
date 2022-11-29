package com.oneaccount.oneaccount.endpoint

import jakarta.servlet.ServletException
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class BookingEndpointTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun createBookingShouldWork() {
        mockMvc.perform(post("/createBooking/1/2022-01-01/1")).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("id")))
    }

    @Test
    fun createBookingShouldThrowExceptionOnUniqueConstraint() {
        mockMvc.perform(post("/createBooking/1/2022-01-01/3")).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("id")))

        Assertions.assertThrows(ServletException::class.java) {
            mockMvc.perform(post("/createBooking/1/2022-01-01/3")).andDo(MockMvcResultHandlers.print())
        }
    }

    @Test
    fun createBookingShouldThrowExceptionOnSeatMoreThen100() {
        Assertions.assertThrows(ServletException::class.java) {
            mockMvc.perform(post("/createBooking/1/2022-01-01/101")).andDo(MockMvcResultHandlers.print())
        }
    }
}
