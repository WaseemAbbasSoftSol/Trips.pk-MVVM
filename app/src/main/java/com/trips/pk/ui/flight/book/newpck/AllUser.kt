package com.trips.pk.ui.flight.book.newpck

import com.trips.pk.model.flight.book.ContactPerson
import com.trips.pk.model.flight.book.Passenger

data class AllUser(
   val contactPerson: ContactPerson,
   val passenger: Passenger,
   val index:Int
):java.io.Serializable
