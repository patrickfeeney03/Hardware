package ie.atu.Storage;

import jakarta.persistence.criteria.Predicate;
import org.hibernate.ResourceClosedException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService {
    private final StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public List<Storage> getStorage(String brand, String name, Float price) {
        return storageRepository.findAll((Specification<Storage>) (root, query, criteriaBuilder) -> {
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

    public Storage saveStorage(Storage storage) {
        return storageRepository.save(storage);
    }

    public void deleteStorage(Long id) {
        storageRepository.deleteById(id);
    }

    public Storage updateStorage(Long id, Storage updatedStorage) {
        Storage temporaryStorage = storageRepository.findById(id).orElseThrow(() -> new ResourceClosedException("Storage not  found for this id: " + id));
        temporaryStorage.setName(updatedStorage.getName());
        temporaryStorage.setPrice(updatedStorage.getPrice());
        temporaryStorage.setCapacity(updatedStorage.getCapacity());
        temporaryStorage.setBrand(updatedStorage.getBrand());
        temporaryStorage.setEbaylink(updatedStorage.getEbaylink());

        return storageRepository.save(temporaryStorage);
    }
}