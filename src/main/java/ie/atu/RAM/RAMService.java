package ie.atu.RAM;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.ResourceClosedException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RAMService {
    private final RAMRepository ramRepository;

    public RAMService(RAMRepository ramRepository) {
        this.ramRepository = ramRepository;
    }

    public List<RAM> getRAM(String brand, String name, Float price, List<String> cpuRamTypes, List<String> motherboardRamTypes) {
        return ramRepository.findAll((Specification<RAM>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (brand != null && !brand.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("brand")), "%" + brand.toLowerCase() + "%"));
            }
            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (cpuRamTypes != null && !cpuRamTypes.isEmpty()) {
                System.out.println("CPU RAM TYPE IS: " + cpuRamTypes);
                CriteriaBuilder.In<String> inCpuRamTypes = criteriaBuilder.in(root.get("ramtype"));
                cpuRamTypes.forEach(inCpuRamTypes::value);
                predicates.add(inCpuRamTypes);
            }
            if (motherboardRamTypes != null && !motherboardRamTypes.isEmpty()) {
                CriteriaBuilder.In<String> inMotherboardRamTypes = criteriaBuilder.in(root.get("ramtype"));
                motherboardRamTypes.forEach(inMotherboardRamTypes::value);
                predicates.add(inMotherboardRamTypes);
            }
            if (price != null) {
                predicates.add(criteriaBuilder.equal(root.get("price"), price));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    public RAM saveRAM(RAM ram) {
        return ramRepository.save(ram);
    }

    public void deleteRAM(Long id) {
        ramRepository.deleteById(id);
    }

    public RAM updateRAM(Long id, RAM updatedRam) {
        RAM temporaryRAM = ramRepository.findById(id).orElseThrow(() -> new ResourceClosedException("RAM not  found for this id: " + id));
        temporaryRAM.setName(updatedRam.getName());
        temporaryRAM.setPrice(updatedRam.getPrice());
        temporaryRAM.setCapacity(updatedRam.getCapacity());
        temporaryRAM.setEbaylink(updatedRam.getEbaylink());

        return ramRepository.save(temporaryRAM);
    }
}
