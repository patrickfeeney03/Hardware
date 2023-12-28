package ie.atu.RAM;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/rams")
@RestController
public class RAMController {

    private final RAMService ramService;

    public RAMController(RAMService ramService) {
        this.ramService = ramService;
    }

    @GetMapping
    public ResponseEntity<?> getRAMs(@RequestParam(name = "brand", required = false) String brand,
                                     @RequestParam(name = "name", required = false) String name,
                                     @RequestParam(name = "price", required = false) Float price,
                                     @RequestParam(name = "cpuRamTypes", required = false) List<String> cpuRamTypes,
                                     @RequestParam(name = "motherboardRamTypes", required = false) List<String> motherboardRamTypes,
                                     @RequestParam(name = "capacity", required = false) Integer capacity,
                                     @RequestParam(name = "ramtype", required = false) String ramtype,
                                     @RequestParam(name = "id", required = false) Long id) {
        List<RAM> rams = ramService.getRAM(brand, name, price, cpuRamTypes, motherboardRamTypes, capacity, ramtype, id);
        return ResponseEntity.ok(rams);
    }

    @PostMapping
    public ResponseEntity<?> createRAM(@RequestBody RAM ram) {
        ramService.saveRAM(ram);
        return ResponseEntity.ok("RAM created successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteRAM(@PathVariable("id") Long id) {
        ramService.deleteRAM(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCPU(@PathVariable("id") Long id, @RequestBody RAM modifiedRAM) {
        RAM updatedRAM = ramService.updateRAM(id, modifiedRAM);
        return ResponseEntity.ok(updatedRAM);
    }
}
