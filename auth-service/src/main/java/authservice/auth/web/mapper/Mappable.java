package authservice.auth.web.mapper;

import org.mapstruct.Mapping;

public interface Mappable<E, D> {

    @Mapping(target = "confirmPassword", ignore = true)
    @Mapping(target = "roles", ignore = true)
    E toEntity(D dto);

    @Mapping(target = "confirmPassword", ignore = true)
    @Mapping(target = "roles", ignore = true)
    D toDTO(E entity);
}
