package ie.atu.CPU;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/cpus")
@RestController
public class CPUController {
    private final CPUService cpuService;

    public CPUController(CPUService cpuService) {
        this.cpuService = cpuService;
    }

    @GetMapping
    public ResponseEntity<?> getCPUs(@RequestParam(name = "brand", required = false) String brand,
                                     @RequestParam(name = "name", required = false) String name,
                                     @RequestParam(name = "price", required = false) Float price,
                                     @RequestParam(name = "socket", required = false) String socket,
                                     @RequestParam(name = "frequency", required = false) String frequency,
                                     @RequestParam(name = "compatibleRAMTypes", required = false) List<String> compatibleRAMTypes,
                                     @RequestParam(name = "ebaylink", required = false) String ebaylink,
                                     @RequestParam(name = "id", required = false) Long id) {
        // List<CPU> cpus = cpuService.getCPU(brand, name, price);
        List<CPU> cpus = cpuService.getCPU(brand, name, price, socket, frequency, compatibleRAMTypes, ebaylink, id);
        return ResponseEntity.ok(cpus);
    }

    @PostMapping
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
