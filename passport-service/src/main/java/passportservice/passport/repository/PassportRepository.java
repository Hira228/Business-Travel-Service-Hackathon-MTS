package passportservice.passport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import passportservice.passport.entity.Passport;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PassportRepository extends JpaRepository<Passport, UUID> {
    boolean existsByPassportNumber(Long passportNumber);
    boolean existsByUserId(UUID userId);
    Optional<Passport> findByUserId(UUID userId);
}
