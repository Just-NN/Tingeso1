package usach.tingeso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usach.tingeso.entities.BonusBrandEntity;
import usach.tingeso.services.BonusBrandService;

@RestController
@RequestMapping("api/v1/bonusBrand")
public class BonusBrandController {

    @Autowired
    BonusBrandService bonusBrandService;

    @GetMapping("/{id}")
    public ResponseEntity<BonusBrandEntity> getBonusBrandById(@PathVariable Long id){
        BonusBrandEntity bonusBrand = bonusBrandService.getBonusBrandById(id);
        if (bonusBrand == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(bonusBrand);
    }

    @PostMapping("/")
    public ResponseEntity<BonusBrandEntity> saveBonusBrand(@RequestBody BonusBrandEntity bonusBrand){
        if (bonusBrand == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(bonusBrandService.saveBonusBrand(bonusBrand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBonusBrand(@PathVariable Long id){
        BonusBrandEntity bonusBrand = bonusBrandService.getBonusBrandById(id);
        if (bonusBrand == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bonusBrandService.deleteBonusBrand(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<BonusBrandEntity> updateBonusBrand(@RequestBody BonusBrandEntity bonusBrand){
        if (bonusBrand == null || bonusBrandService.getBonusBrandById(bonusBrand.getIdBonus()) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(bonusBrandService.updateBonusBrand(bonusBrand));
    }

    @GetMapping("/bonus/{brand}")
    public ResponseEntity<Integer> getBonusByBrand(@PathVariable String brand){
        Integer bonus = bonusBrandService.getBonusByBrand(brand);
        return ResponseEntity.ok(bonus);
    }
}