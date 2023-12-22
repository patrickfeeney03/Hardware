package ie.atu.Storage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/storages")
@RestController
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("")
    public ResponseEntity<?> getStorages(@RequestParam(name = "brand", required = false) String brand,
                                         @RequestParam(name = "name", required = false) String name,
                                         @RequestParam(name = "price", required = false) Float price) {
        List<Storage> storages = storageService.getStorage(brand, name, price);
        return ResponseEntity.ok(storages);
    }

    @PostMapping("")
    public ResponseEntity<?> createStorage(@RequestBody Storage storage) {
        storageService.saveStorage(storage);
        return ResponseEntity.ok("Storage created successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStorage(@PathVariable("id") Long id) {
        storageService.deleteStorage(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCPU(@PathVariable("id") Long id, @RequestBody Storage modifiedStorage) {
        Storage updatedStorage = storageService.updateStorage(id, modifiedStorage);
        return ResponseEntity.ok(updatedStorage);
    }
}
