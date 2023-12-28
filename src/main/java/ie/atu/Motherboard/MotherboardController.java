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
                                             @RequestParam(name = "compatibleRAMTypes", required = false) List<String> compatibleRAMTypes) {
        //List<Motherboard> motherboards = motherboardService.getMotherboard(brand, name, price);
        List<Motherboard> motherboards = motherboardService.getMotherboard(brand, name, price, socket, compatibleRAMTypes);
        return ResponseEntity.ok(motherboards);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getMotherboardById(@PathVariable("id") Long id) {
        Motherboard motherboard = motherboardService.getMotherboardById(id);
        // If I dont 'copy' the cpu to another cpu it just doesnt work because of some funky hibernate shit
        MotherboardDto motherboardDto = new MotherboardDto(
                motherboard.getId(),
                motherboard.getName(),
                motherboard.getPrice(),
                motherboard.getBrand(),
                motherboard.getSocket(),
                motherboard.getChipset(),
                motherboard.getCompatibleRAMTypes(),
                motherboard.getCompatibleStorageTypes(),
                motherboard.getEbaylink()
        );
        System.out.println(motherboardDto);
        return ResponseEntity.ok(motherboardDto);
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
