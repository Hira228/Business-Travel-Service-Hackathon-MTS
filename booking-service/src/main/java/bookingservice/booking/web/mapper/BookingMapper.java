package bookingservice.booking.web.mapper;

import bookingservice.booking.entity.Booking;
import bookingservice.booking.web.dto.BookingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper extends Mappable<Booking, BookingDTO>{
}
