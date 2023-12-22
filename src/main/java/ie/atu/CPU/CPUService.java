package ie.atu.CPU;

import jakarta.persistence.criteria.Predicate;
import org.hibernate.ResourceClosedException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CPUService {
    private final CPURepository cpuRepository;

    public CPUService(CPURepository cpuRepository) {
        this.cpuRepository = cpuRepository;
    }

    public List<CPU> getCPU(String brand, String name, Float price) {
        return cpuRepository.findAll((Specification<CPU>) (root, query, criteriaBuilder) -> {
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

    public CPU saveCPU(CPU cpu) {
        return cpuRepository.save(cpu);
    }

    public void deleteCPU(Long id) {
        cpuRepository.deleteById(id);
    }

    public CPU updateCPU(Long id, CPU updatedCpu) {
        CPU temporaryCPU = cpuRepository.findById(id).orElseThrow(() -> new ResourceClosedException("CPU not  found for this id: " + id));
        temporaryCPU.setName(updatedCpu.getName());
        temporaryCPU.setPrice(updatedCpu.getPrice());
        temporaryCPU.setSocket(updatedCpu.getSocket());
        temporaryCPU.setFrequency(updatedCpu.getFrequency());
        temporaryCPU.setBrand(updatedCpu.getBrand());
        temporaryCPU.setEbaylink(updatedCpu.getEbaylink());

        return cpuRepository.save(temporaryCPU);
    }
}
