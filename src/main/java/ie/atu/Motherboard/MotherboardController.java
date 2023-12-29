package ie.atu.Motherboard;

import ie.atu.CPU.CPU;
import ie.atu.CPU.CPUDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/motherboards")
@RestController
public class MotherboardController {
    private final MotherboardService motherboardService;

    public MotherboardController(MotherboardService motherboardService) {
        this.motherboardService = motherboardService;
    }

    @GetMapping
    public ResponseEntity<?> getMotherboards(@RequestParam(name = "brand", required = false) String brand,
                                             @RequestParam(name = "name", required = false) String name,
                                             @RequestParam(name = "price", required = false) Float price,
                                             @RequestParam(name = "socket", required = false) String socket,
                                             @RequestParam(name = "chipset", required = false) String chipset,
                                             @RequestParam(name = "compatibleRAMTypes", required = false) List<String> compatibleRAMTypes,
                                             @RequestParam(name = "compatibleStorageTypes", required = false) List<String> compatibleStorageTypes,
                                             @RequestParam(name = "ebaylink", required = false) String ebaylink,
                                             @RequestParam(name = "id", required = false) Long id) {
        List<Motherboard> motherboards = motherboardService.getMotherboard(brand, name, price, socket, compatibleRAMTypes, chipset, compatibleStorageTypes, ebaylink, id);
        return ResponseEntity.ok(motherboards);
    }

    @PostMapping
    public ResponseEntity<?> createMotherboard(@RequestBody Motherboard motherboard) {
        motherboardService.saveMotherboard(motherboard);
        return ResponseEntity.ok("Motherboard created successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMotherboard(@PathVariable("id") Long id) {
        motherboardService.deleteMotherboard(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCPU(@PathVariable("id") Long id, @RequestBody Motherboard modifiedMotherboard) {
        Motherboard updatedMotherboard = motherboardService.updateMotherboard(id, modifiedMotherboard);
        return ResponseEntity.ok(updatedMotherboard);
    }
}
