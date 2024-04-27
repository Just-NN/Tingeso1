package usach.tingeso.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import usach.tingeso.entities.BonusBrandEntity;
import usach.tingeso.services.BonusBrandService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
public class BonusBrandControllerTest {

    @InjectMocks
    BonusBrandController bonusBrandController;

    @Mock
    BonusBrandService bonusBrandService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    //------------------------------------------------------------------------------------------------
    // Test for getBonusBrandById
    @Test
    public void testGetBonusBrandById() {
        BonusBrandEntity bonusBrand = new BonusBrandEntity();
        // Set properties for bonusBrand
        when(bonusBrandService.getBonusBrandById(1L)).thenReturn(bonusBrand);

        ResponseEntity<BonusBrandEntity> response = bonusBrandController.getBonusBrandById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bonusBrand, response.getBody());
    }
    @Test
    public void testGetBonusBrandByIdNotFound() {
        Long id = 1L;
        when(bonusBrandService.getBonusBrandById(id)).thenReturn(null);

        ResponseEntity<BonusBrandEntity> response = bonusBrandController.getBonusBrandById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    //------------------------------------------------------------------------------------------------
    // Test for saveBonusBrand
    @Test
    public void testSaveBonusBrand() {
        BonusBrandEntity bonusBrand = new BonusBrandEntity();
        // Set properties for bonusBrand
        when(bonusBrandService.saveBonusBrand(bonusBrand)).thenReturn(bonusBrand);

        ResponseEntity<BonusBrandEntity> response = bonusBrandController.saveBonusBrand(bonusBrand);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bonusBrand, response.getBody());
    }
    @Test
    public void testSaveBonusBrandBadRequest() {
        when(bonusBrandService.saveBonusBrand(null)).thenReturn(null);

        ResponseEntity<BonusBrandEntity> response = bonusBrandController.saveBonusBrand(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    //------------------------------------------------------------------------------------------------
    // Test for deleteBonusBrand
    @Test
    public void testDeleteBonusBrand() {
        BonusBrandEntity bonusBrand = new BonusBrandEntity();
        // Set properties for bonusBrand
        when(bonusBrandService.getBonusBrandById(1L)).thenReturn(bonusBrand);

        ResponseEntity<Void> response = bonusBrandController.deleteBonusBrand(1L);
        assertEquals(200, response.getStatusCodeValue());
    }
    @Test
    public void testDeleteBonusBrandNotFound() {
        Long id = 1L;
        when(bonusBrandService.getBonusBrandById(id)).thenReturn(null);

        ResponseEntity<Void> response = bonusBrandController.deleteBonusBrand(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    //------------------------------------------------------------------------------------------------
    // Test for updateBonusBrand
    @Test
    public void testUpdateBonusBrand() {
        BonusBrandEntity bonusBrand = new BonusBrandEntity();
        // Set properties for bonusBrand
        when(bonusBrandService.getBonusBrandById(1L)).thenReturn(bonusBrand);
        when(bonusBrandService.updateBonusBrand(bonusBrand)).thenReturn(bonusBrand);

        ResponseEntity<BonusBrandEntity> response = bonusBrandController.updateBonusBrand(bonusBrand);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bonusBrand, response.getBody());
    }
    //------------------------------------------------------------------------------------------------
    // Test for getBonusByBrand
    @Test
    public void testGetBonusByBrand() {
        String brand = "brand";
        Integer expectedBonus = 10;
        when(bonusBrandService.getBonusByBrand(brand)).thenReturn(expectedBonus);

        ResponseEntity<Integer> response = bonusBrandController.getBonusByBrand(brand);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedBonus, response.getBody());
    }

    // Similarly, you can write tests for other methods like saveBonusBrand, deleteBonusBrand, updateBonusBrand, getBonusByBrand
}