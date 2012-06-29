package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.SaleFlatDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SaleFlatIntegrationTest_Roo_IntegrationTest {
    
    declare @type: SaleFlatIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: SaleFlatIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private SaleFlatDataOnDemand SaleFlatIntegrationTest.dod;
    
    @Test
    public void SaleFlatIntegrationTest.testCountSaleFlats() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleFlat' failed to initialize correctly", dod.getRandomSaleFlat());
        long count = biz.rageintegro.erealt.domain.SaleFlat.countSaleFlats();
        org.junit.Assert.assertTrue("Counter for 'SaleFlat' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void SaleFlatIntegrationTest.testFindSaleFlat() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleFlat' failed to initialize correctly", dod.getRandomSaleFlat());
        java.lang.Long id = dod.getRandomSaleFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleFlat obj = biz.rageintegro.erealt.domain.SaleFlat.findSaleFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleFlat' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'SaleFlat' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void SaleFlatIntegrationTest.testFindAllSaleFlats() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleFlat' failed to initialize correctly", dod.getRandomSaleFlat());
        long count = biz.rageintegro.erealt.domain.SaleFlat.countSaleFlats();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'SaleFlat', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.SaleFlat> result = biz.rageintegro.erealt.domain.SaleFlat.findAllSaleFlats();
        org.junit.Assert.assertNotNull("Find all method for 'SaleFlat' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'SaleFlat' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void SaleFlatIntegrationTest.testFindSaleFlatEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleFlat' failed to initialize correctly", dod.getRandomSaleFlat());
        long count = biz.rageintegro.erealt.domain.SaleFlat.countSaleFlats();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.SaleFlat> result = biz.rageintegro.erealt.domain.SaleFlat.findSaleFlatEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'SaleFlat' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'SaleFlat' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void SaleFlatIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleFlat' failed to initialize correctly", dod.getRandomSaleFlat());
        java.lang.Long id = dod.getRandomSaleFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleFlat obj = biz.rageintegro.erealt.domain.SaleFlat.findSaleFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleFlat' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySaleFlat(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'SaleFlat' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void SaleFlatIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleFlat' failed to initialize correctly", dod.getRandomSaleFlat());
        java.lang.Long id = dod.getRandomSaleFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleFlat obj = biz.rageintegro.erealt.domain.SaleFlat.findSaleFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleFlat' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySaleFlat(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'SaleFlat' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void SaleFlatIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleFlat' failed to initialize correctly", dod.getRandomSaleFlat());
        biz.rageintegro.erealt.domain.SaleFlat obj = dod.getNewTransientSaleFlat(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'SaleFlat' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'SaleFlat' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'SaleFlat' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void SaleFlatIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleFlat' failed to initialize correctly", dod.getRandomSaleFlat());
        java.lang.Long id = dod.getRandomSaleFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleFlat obj = biz.rageintegro.erealt.domain.SaleFlat.findSaleFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleFlat' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'SaleFlat' with identifier '" + id + "'", biz.rageintegro.erealt.domain.SaleFlat.findSaleFlat(id));
    }
    
}
