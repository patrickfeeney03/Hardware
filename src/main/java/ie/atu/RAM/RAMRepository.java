package ie.atu.RAM;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RAMRepository extends JpaRepository<RAM, Long>, JpaSpecificationExecutor<RAM> {
}
