package ie.atu.Motherboard;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
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

    public List<Motherboard> getMotherboard(String brand, String name, Float price, String socket, List<String> compatibleRAMTypes, String chipset, List<String> compatibleStorageTypes, String ebaylink, Long id) {
        return motherboardRepository.findAll((Specification<Motherboard>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (brand != null && !brand.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("brand")), "%" + brand.toLowerCase() + "%"));
            }
            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (socket != null && !socket.isEmpty()) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("socket")), socket.toLowerCase()));
            }
            if (compatibleRAMTypes != null && !compatibleRAMTypes.isEmpty()) {
                Join<Motherboard, String> ramTypeJoin = root.join("compatibleRAMTypes"); // Joins both tables to have original object
                CriteriaBuilder.In<String> inClause = criteriaBuilder.in(ramTypeJoin); // Filter
                compatibleRAMTypes.forEach(inClause::value);
                predicates.add(inClause);
            }
            if (price != null) {
                predicates.add(criteriaBuilder.equal(root.get("price"), price));
            }
            if (chipset != null && !chipset.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("chipset"), chipset));
            }
            if (compatibleStorageTypes != null && !compatibleStorageTypes.isEmpty()) {
                Join<Motherboard, String> storageTypeJoin = root.join("compatibleStorageTypes");
                CriteriaBuilder.In<String> inClause = criteriaBuilder.in(storageTypeJoin);
                compatibleStorageTypes.forEach(inClause::value);
                predicates.add(inClause);
            }
            if (ebaylink != null && !ebaylink.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("ebaylink"), ebaylink));
            }
            if (id != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
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

    public Motherboard getMotherboardById(Long id) {
        return motherboardRepository.getById(id);
    }
}
