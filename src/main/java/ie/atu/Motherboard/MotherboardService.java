package ie.atu.Motherboard;

import jakarta.persistence.criteria.Predicate;
import org.hibernate.ResourceClosedException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MotherboardService {
    private final MotherboardRepository motherboardRepository;

    public MotherboardService(MotherboardRepository motherboardRepository) {
        this.motherboardRepository = motherboardRepository;
    }

    public List<Motherboard> getMotherboard(String brand, String name, Float price) {
        return motherboardRepository.findAll((Specification<Motherboard>) (root, query, criteriaBuilder) -> {
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

    public Motherboard saveMotherboard(Motherboard motherboard) {
        return motherboardRepository.save(motherboard);
    }

    public void deleteMotherboard(Long id) {
        motherboardRepository.deleteById(id);
    }

    public Motherboard updateMotherboard(Long id, Motherboard updatedMotherboard) {
        Motherboard temporaryMotherboard = motherboardRepository.findById(id).orElseThrow(() -> new ResourceClosedException("Motherboard not  found for this id: " + id));
        temporaryMotherboard.setName(updatedMotherboard.getName());
        temporaryMotherboard.setPrice(updatedMotherboard.getPrice());
        temporaryMotherboard.setBrand(updatedMotherboard.getBrand());
        temporaryMotherboard.setSocket(updatedMotherboard.getSocket());
        temporaryMotherboard.setChipset(updatedMotherboard.getChipset());
        temporaryMotherboard.setEbaylink(updatedMotherboard.getEbaylink());

        return motherboardRepository.save(temporaryMotherboard);
    }
}
