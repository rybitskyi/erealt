package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.SaleNewFlatDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SaleNewFlatIntegrationTest_Roo_IntegrationTest {
    
    declare @type: SaleNewFlatIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: SaleNewFlatIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private SaleNewFlatDataOnDemand SaleNewFlatIntegrationTest.dod;
    
    @Test
    public void SaleNewFlatIntegrationTest.testCountSaleNewFlats() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleNewFlat' failed to initialize correctly", dod.getRandomSaleNewFlat());
        long count = biz.rageintegro.erealt.domain.SaleNewFlat.countSaleNewFlats();
        org.junit.Assert.assertTrue("Counter for 'SaleNewFlat' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void SaleNewFlatIntegrationTest.testFindSaleNewFlat() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleNewFlat' failed to initialize correctly", dod.getRandomSaleNewFlat());
        java.lang.Long id = dod.getRandomSaleNewFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleNewFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleNewFlat obj = biz.rageintegro.erealt.domain.SaleNewFlat.findSaleNewFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleNewFlat' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'SaleNewFlat' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void SaleNewFlatIntegrationTest.testFindAllSaleNewFlats() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleNewFlat' failed to initialize correctly", dod.getRandomSaleNewFlat());
        long count = biz.rageintegro.erealt.domain.SaleNewFlat.countSaleNewFlats();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'SaleNewFlat', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.SaleNewFlat> result = biz.rageintegro.erealt.domain.SaleNewFlat.findAllSaleNewFlats();
        org.junit.Assert.assertNotNull("Find all method for 'SaleNewFlat' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'SaleNewFlat' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void SaleNewFlatIntegrationTest.testFindSaleNewFlatEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleNewFlat' failed to initialize correctly", dod.getRandomSaleNewFlat());
        long count = biz.rageintegro.erealt.domain.SaleNewFlat.countSaleNewFlats();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.SaleNewFlat> result = biz.rageintegro.erealt.domain.SaleNewFlat.findSaleNewFlatEntries(0, (int)count, null, null, null);
        org.junit.Assert.assertNotNull("Find entries method for 'SaleNewFlat' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'SaleNewFlat' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void SaleNewFlatIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleNewFlat' failed to initialize correctly", dod.getRandomSaleNewFlat());
        java.lang.Long id = dod.getRandomSaleNewFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleNewFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleNewFlat obj = biz.rageintegro.erealt.domain.SaleNewFlat.findSaleNewFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleNewFlat' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySaleNewFlat(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'SaleNewFlat' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void SaleNewFlatIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleNewFlat' failed to initialize correctly", dod.getRandomSaleNewFlat());
        java.lang.Long id = dod.getRandomSaleNewFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleNewFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleNewFlat obj = biz.rageintegro.erealt.domain.SaleNewFlat.findSaleNewFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleNewFlat' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySaleNewFlat(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'SaleNewFlat' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void SaleNewFlatIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleNewFlat' failed to initialize correctly", dod.getRandomSaleNewFlat());
        biz.rageintegro.erealt.domain.SaleNewFlat obj = dod.getNewTransientSaleNewFlat(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'SaleNewFlat' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'SaleNewFlat' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'SaleNewFlat' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void SaleNewFlatIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleNewFlat' failed to initialize correctly", dod.getRandomSaleNewFlat());
        java.lang.Long id = dod.getRandomSaleNewFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleNewFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleNewFlat obj = biz.rageintegro.erealt.domain.SaleNewFlat.findSaleNewFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleNewFlat' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'SaleNewFlat' with identifier '" + id + "'", biz.rageintegro.erealt.domain.SaleNewFlat.findSaleNewFlat(id));
    }
    
}
