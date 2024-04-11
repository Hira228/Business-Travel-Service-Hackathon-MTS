package depositservice.deposit.web.mapper;

public interface Mappable<E, D>{
    E toEntity(D Dto);
    D toDTO(E entity);
}
