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
                                     @RequestParam(name = "price", required = false) Float price) {
        List<CPU> cpus = cpuService.getCPU(brand, name, price);
        return ResponseEntity.ok(cpus);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCPUById(@PathVariable("id") Long id) {
        CPU cpu = cpuService.getCPUById(id);
        // If I dont 'copy' the cpu to another cpu it just doesnt work because of some funky hibernate shit
        CPUDto cpuDto = new CPUDto(
                cpu.getId(),
                cpu.getName(),
                cpu.getPrice(),
                cpu.getSocket(),
                cpu.getFrequency(),
                cpu.getBrand(),
                cpu.getCompatibleRAMTypes(),
                cpu.getEbaylink()
        );
        System.out.println(cpuDto);
        return ResponseEntity.ok(cpuDto);
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
