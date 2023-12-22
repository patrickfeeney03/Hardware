package ie.atu.Motherboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MotherboardRepository extends JpaRepository<Motherboard, Long>, JpaSpecificationExecutor<Motherboard> {
}
