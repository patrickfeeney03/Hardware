package ie.atu;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/hardware")
@RestController
public class HardwareController {
    // This has the mappings
    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping("/cpus")
    public ResponseEntity<?> getCPUs(@RequestParam(name = "brand", required = false) String brand,
                                     @RequestParam(name = "name", required = false) String name,
                                     @RequestParam(name = "price", required = false) Float price) {
        // cpus?brand=AMD?name=232
        // cpus
        // cpus?name=Intel 5 13600kf
        if (brand != null) {
            return ResponseEntity.ok(hardwareService.getCPUByBrand(brand));
        } else if (name != null) {
            return ResponseEntity.ok(hardwareService.getCPUByName(name));
        } else if (price != null){
            return ResponseEntity.ok(hardwareService.getCPUByPrice(price));
        } else {
            return ResponseEntity.ok(hardwareService.findAll());
        }
    }

    @PostMapping("cpus")
    public ResponseEntity<?> createCPU(@RequestBody CPU cpu) {
        hardwareService.saveCPU(cpu);
        return ResponseEntity.ok("CPU created successfully");
    }

    @DeleteMapping("cpus/{id}")
    public ResponseEntity<?> deleteCPU(@PathVariable("id") Long id) {
        System.out.println("This ran: " + id);
        hardwareService.deleteCPU(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("cpus/{id}")
    public ResponseEntity<?> updateCPU(@PathVariable("id") Long id, @RequestBody CPU updatedCpu) {
        CPU updatedCPU = hardwareService.updateCPU(id, updatedCpu);
        return ResponseEntity.ok(updatedCPU);
    }

    @GetMapping("/gpus")
    public ResponseEntity<?> getGPUs(@RequestParam(name = "brand", required = false) String brand,
                                     @RequestParam(name = "name", required = false) String name,
                                     @RequestParam(name = "price", required = false) Float price) {
        if (brand != null) {
            return ResponseEntity.ok(hardwareService.getGPUByBrand(brand));
        } else if (name != null) {
            return ResponseEntity.ok(hardwareService.getGPUByName(name));
        } else if (price != null){
            return ResponseEntity.ok(hardwareService.getGPUByPrice(price));
        } else {
            return ResponseEntity.ok(hardwareService.findAll());
        }
    }

    @PostMapping("gpus")
    public ResponseEntity<?> createGPU(@RequestBody GPU gpu) {
        hardwareService.saveGPU(gpu);
        return ResponseEntity.ok("GPU created successfully");
    }

    @DeleteMapping("gpus/{id}")
    public ResponseEntity<?> deleteGPU(@PathVariable("id") Long id) {
        hardwareService.deleteGPU(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("gpus/{id}")
    public ResponseEntity<?> updateGPU(@PathVariable("id") Long id, @RequestBody GPU updatedGpu) {
        GPU updatedGPU = hardwareService.updateGPU(id, updatedGpu);
        return ResponseEntity.ok(updatedGPU);
    }

}
