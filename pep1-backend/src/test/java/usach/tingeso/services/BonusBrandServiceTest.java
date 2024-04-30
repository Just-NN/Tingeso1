package usach.tingeso.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import usach.tingeso.entities.BonusBrandEntity;
import usach.tingeso.repositories.BonusBrandRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BonusBrandServiceTest {

    @InjectMocks
    BonusBrandService bonusBrandService;

    @Mock
    BonusBrandRepository bonusBrandRepository;

    //------------------------------------------------------------------------------------------------
    // Test for getBonusBrandById
    @Test
    public void testGetBonusBrandById() {
        BonusBrandEntity bonusBrand = new BonusBrandEntity();
        when(bonusBrandRepository.findBonusBrandByIdBonus(1L)).thenReturn(bonusBrand);

        BonusBrandEntity result = bonusBrandService.getBonusBrandById(1L);
        assertNotNull(result);
        assertEquals(bonusBrand, result);
    }
    @Test
    public void testGetBonusBrandByIdNotFound() {
        when(bonusBrandRepository.findBonusBrandByIdBonus(1L)).thenReturn(null);

        BonusBrandEntity result = bonusBrandService.getBonusBrandById(1L);
        assertEquals(null, result);
    }

    //------------------------------------------------------------------------------------------------
    // Test for saveBonusBrand
    @Test
    public void testSaveBonusBrand() {
        BonusBrandEntity bonusBrand = new BonusBrandEntity();
        when(bonusBrandRepository.save(bonusBrand)).thenReturn(bonusBrand);

        BonusBrandEntity result = bonusBrandService.saveBonusBrand(bonusBrand);
        assertNotNull(result);
        assertEquals(bonusBrand, result);
    }
    @Test
    public void testSaveBonusBrandNull() {
        BonusBrandEntity result = bonusBrandService.saveBonusBrand(null);
        assertEquals(null, result);
    }
    //------------------------------------------------------------------------------------------------
    // Test for getAllBonusBrands
    @Test
    public void testGetAllBonusBrands() {
        BonusBrandEntity bonusBrand = new BonusBrandEntity();
        when(bonusBrandRepository.findAll()).thenReturn(Collections.singletonList(bonusBrand));

        List<BonusBrandEntity> result = bonusBrandService.bonusBrandRepository.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(bonusBrand, result.get(0));
    }
    @Test
    public void testGetAllBonusBrandsEmpty() {
        when(bonusBrandRepository.findAll()).thenReturn(Collections.emptyList());

        List<BonusBrandEntity> result = bonusBrandService.bonusBrandRepository.findAll();
        assertNotNull(result);
        assertEquals(0, result.size());
    }
    //------------------------------------------------------------------------------------------------
// Test for updateBonusBrand
    @Test
    public void testUpdateBonusBrand() {
        BonusBrandEntity bonusBrand = new BonusBrandEntity();
        when(bonusBrandRepository.save(bonusBrand)).thenReturn(bonusBrand);

        BonusBrandEntity result = bonusBrandService.updateBonusBrand(bonusBrand);
        assertNotNull(result);
        assertEquals(bonusBrand, result);
    }
    @Test
    public void testUpdateBonusBrandNull() {
        BonusBrandEntity result = bonusBrandService.updateBonusBrand(null);
        assertEquals(null, result);
    }

    //------------------------------------------------------------------------------------------------
    // Test for getBonusByBrand
    @Test
    public void testGetBonusByBrand() {
        BonusBrandEntity bonusBrand = new BonusBrandEntity();
        bonusBrand.setBonus(100);
        when(bonusBrandRepository.findHighestActiveBonusByBrand("brand")).thenReturn(Collections.singletonList(bonusBrand));

        int result = bonusBrandService.getBonusByBrand("brand");
        assertEquals(100, result);
    }
    @Test
    public void testGetBonusByBrandNull() {
        int result = bonusBrandService.getBonusByBrand(null);
        assertEquals(0, result);
    }

    @Test
    public void testGetBonusByBrandNoBonus() {
        when(bonusBrandRepository.findHighestActiveBonusByBrand("brand")).thenReturn(Collections.emptyList());

        int result = bonusBrandService.getBonusByBrand("brand");
        assertEquals(0, result);
    }
    //------------------------------------------------------------------------------------------------
    // Test for deleteBonusBrand
    @Test
    public void testDeleteBonusBrand() {
        bonusBrandService.deleteBonusBrand(1L);
    }
    @Test
    public void testDeleteBonusBrandNotFound() {
        when(bonusBrandRepository.findBonusBrandByIdBonus(1L)).thenReturn(null);

        bonusBrandService.deleteBonusBrand(1L);
    }
    //------------------------------------------------------------------------------------------------

}