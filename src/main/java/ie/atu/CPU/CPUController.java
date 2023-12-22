package ie.atu.CPU;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cpus")
@RestController
public class CPUController {
    private final CPUService cpuService;

    public CPUController(CPUService cpuService) {
        this.cpuService = cpuService;
    }

    @GetMapping("")
    public ResponseEntity<?> getCPUs(@RequestParam(name = "brand", required = false) String brand,
                                     @RequestParam(name = "name", required = false) String name,
                                     @RequestParam(name = "price", required = false) Float price) {
        List<CPU> cpus = cpuService.getCPU(brand, name, price);
        return ResponseEntity.ok(cpus);
    }

    @PostMapping("")
    public ResponseEntity<?> createCPU(@RequestBody CPU cpu) {
        cpuService.saveCPU(cpu);
        return ResponseEntity.ok("CPU created successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCPU(@PathVariable("id") Long id) {
        cpuService.deleteCPU(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCPU(@PathVariable("id") Long id, @RequestBody CPU modifiedCPU) {
        CPU updatedCPU = cpuService.updateCPU(id, modifiedCPU);
        return ResponseEntity.ok(updatedCPU);
    }
}
