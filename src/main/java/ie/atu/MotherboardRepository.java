package ie.atu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MotherboardRepository extends JpaRepository<Motherboard, Long>, JpaSpecificationExecutor<Motherboard> {

    List<Motherboard> findByBrand(String brand);

    List<Motherboard> findByName(String name);

    List<Motherboard> findByPrice(Float price);
}
