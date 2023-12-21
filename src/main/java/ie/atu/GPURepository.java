package ie.atu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GPURepository extends JpaRepository<GPU, Long> {

    List<GPU> findByBrand(String brand);

    List<GPU> findByName(String name);

    List<GPU> findByPrice(Float price);
}
