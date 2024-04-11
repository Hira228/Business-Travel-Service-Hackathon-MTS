package depositservice.deposit.web.mapper;

import depositservice.deposit.entity.Deposit;
import depositservice.deposit.entity.DepositReport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepositReportMapper extends Mappable<DepositReport, Deposit>{
}
