package ie.atu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CPURepository extends JpaRepository<CPU, Long> {
    // This seems to be If I want to create special queries

    List<CPU> findByBrand(String brand);

    List<CPU> findByName(String name);

    List<CPU> findByPrice(Float price);
}
