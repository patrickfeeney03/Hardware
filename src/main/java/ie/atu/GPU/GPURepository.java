package ie.atu.GPU;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GPURepository extends JpaRepository<GPU, Long>, JpaSpecificationExecutor<GPU> {
}
