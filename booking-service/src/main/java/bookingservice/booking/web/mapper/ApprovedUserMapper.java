package bookingservice.booking.web.mapper;

import bookingservice.booking.entity.ApprovedUser;
import bookingservice.booking.web.dto.ApprovedUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApprovedUserMapper extends Mappable<ApprovedUser, ApprovedUserDTO> {

    @Override
    ApprovedUser toEntity(ApprovedUserDTO dto);
}
