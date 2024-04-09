package passportservice.passport.web.mapper;

import org.mapstruct.Mapper;
import passportservice.passport.entity.Passport;
import passportservice.passport.web.dto.PassportDTO;

@Mapper(componentModel = "spring")
public interface PassportMapper extends Mappable<Passport, PassportDTO>{
}
