package bookingservice.booking.web.mapper;

import org.mapstruct.Mapping;

public interface Mappable<E, D> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passportNumber", ignore = true)
    E toEntity(D dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passportNumber", ignore = true)
    D toDTO(E entity);
}
