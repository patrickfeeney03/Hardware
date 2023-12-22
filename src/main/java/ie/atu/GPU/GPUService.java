package ie.atu.GPU;

import jakarta.persistence.criteria.Predicate;
import org.hibernate.ResourceClosedException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GPUService {
    private final GPURepository gpuRepository;

    public GPUService(GPURepository gpuRepository) {
        this.gpuRepository = gpuRepository;
    }

    public List<GPU> getGPU(String brand, String name, Float price) {
        return gpuRepository.findAll((Specification<GPU>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (brand != null && !brand.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("brand")), "%" + brand.toLowerCase() + "%"));
            }
            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (price != null) {
                predicates.add(criteriaBuilder.equal(root.get("price"), price));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    public GPU saveGPU(GPU gpu) {
        return gpuRepository.save(gpu);
    }

    public void deleteGPU(Long id) {
        gpuRepository.deleteById(id);
    }

    public GPU updateGPU(Long id, GPU updatedGpu) {
        GPU temporaryGPU = gpuRepository
                .findById(id)
                .orElseThrow(() -> new ResourceClosedException("GPU not  found for this id: " + id));
        temporaryGPU.setName(updatedGpu.getName());
        temporaryGPU.setPrice(updatedGpu.getPrice());
        temporaryGPU.setBrand(updatedGpu.getBrand());
        temporaryGPU.setEbaylink(updatedGpu.getEbaylink());

        return gpuRepository.save(temporaryGPU);
    }
}
