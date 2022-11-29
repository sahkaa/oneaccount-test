package com.oneaccount.oneaccount.model

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import org.hibernate.Hibernate
import java.time.LocalDate

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["film_id", "seat", "date"])])
data class Booking(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    var film: Film?,

    @Min(1)
    @Max(100)
    var seat: Int?,

    var date: LocalDate?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Booking

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , film = $film , seat = $seat , date = $date )"
    }
}
