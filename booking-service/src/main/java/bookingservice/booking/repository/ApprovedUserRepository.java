package bookingservice.booking.repository;

import bookingservice.booking.entity.ApprovedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApprovedUserRepository extends JpaRepository<ApprovedUser, UUID> {
}
