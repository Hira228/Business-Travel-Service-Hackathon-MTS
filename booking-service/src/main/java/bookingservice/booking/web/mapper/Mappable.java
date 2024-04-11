package bookingservice.booking.web.mapper;

import org.mapstruct.Mapping;

public interface Mappable<E, D> {


    E toEntity(D dto);

    D toDTO(E entity);
}
