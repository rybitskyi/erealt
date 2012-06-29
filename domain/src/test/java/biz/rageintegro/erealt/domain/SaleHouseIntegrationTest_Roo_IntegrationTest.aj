package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.SaleHouseDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SaleHouseIntegrationTest_Roo_IntegrationTest {
    
    declare @type: SaleHouseIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: SaleHouseIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private SaleHouseDataOnDemand SaleHouseIntegrationTest.dod;
    
    @Test
    public void SaleHouseIntegrationTest.testCountSaleHouses() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleHouse' failed to initialize correctly", dod.getRandomSaleHouse());
        long count = biz.rageintegro.erealt.domain.SaleHouse.countSaleHouses();
        org.junit.Assert.assertTrue("Counter for 'SaleHouse' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void SaleHouseIntegrationTest.testFindSaleHouse() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleHouse' failed to initialize correctly", dod.getRandomSaleHouse());
        java.lang.Long id = dod.getRandomSaleHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleHouse obj = biz.rageintegro.erealt.domain.SaleHouse.findSaleHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleHouse' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'SaleHouse' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void SaleHouseIntegrationTest.testFindAllSaleHouses() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleHouse' failed to initialize correctly", dod.getRandomSaleHouse());
        long count = biz.rageintegro.erealt.domain.SaleHouse.countSaleHouses();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'SaleHouse', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.SaleHouse> result = biz.rageintegro.erealt.domain.SaleHouse.findAllSaleHouses();
        org.junit.Assert.assertNotNull("Find all method for 'SaleHouse' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'SaleHouse' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void SaleHouseIntegrationTest.testFindSaleHouseEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleHouse' failed to initialize correctly", dod.getRandomSaleHouse());
        long count = biz.rageintegro.erealt.domain.SaleHouse.countSaleHouses();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.SaleHouse> result = biz.rageintegro.erealt.domain.SaleHouse.findSaleHouseEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'SaleHouse' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'SaleHouse' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void SaleHouseIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleHouse' failed to initialize correctly", dod.getRandomSaleHouse());
        java.lang.Long id = dod.getRandomSaleHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleHouse obj = biz.rageintegro.erealt.domain.SaleHouse.findSaleHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleHouse' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySaleHouse(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'SaleHouse' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void SaleHouseIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleHouse' failed to initialize correctly", dod.getRandomSaleHouse());
        java.lang.Long id = dod.getRandomSaleHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleHouse obj = biz.rageintegro.erealt.domain.SaleHouse.findSaleHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleHouse' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySaleHouse(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'SaleHouse' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void SaleHouseIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleHouse' failed to initialize correctly", dod.getRandomSaleHouse());
        biz.rageintegro.erealt.domain.SaleHouse obj = dod.getNewTransientSaleHouse(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'SaleHouse' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'SaleHouse' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'SaleHouse' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void SaleHouseIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleHouse' failed to initialize correctly", dod.getRandomSaleHouse());
        java.lang.Long id = dod.getRandomSaleHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleHouse obj = biz.rageintegro.erealt.domain.SaleHouse.findSaleHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleHouse' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'SaleHouse' with identifier '" + id + "'", biz.rageintegro.erealt.domain.SaleHouse.findSaleHouse(id));
    }
    
}
