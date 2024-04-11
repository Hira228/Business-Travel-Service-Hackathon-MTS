package bookingservice.booking.web.mapper;

import bookingservice.booking.entity.ApprovedUser;
import bookingservice.booking.web.dto.ApprovedUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApprovedUserMapper extends Mappable<ApprovedUser, ApprovedUserDTO> {

    @Override
    ApprovedUser toEntity(ApprovedUserDTO dto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passportNumber", ignore = true)
    ApprovedUserDTO toDTO(ApprovedUser approvedUser);
}
