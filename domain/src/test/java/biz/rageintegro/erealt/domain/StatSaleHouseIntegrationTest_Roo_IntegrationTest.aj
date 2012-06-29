package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatSaleHouseDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StatSaleHouseIntegrationTest_Roo_IntegrationTest {
    
    declare @type: StatSaleHouseIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: StatSaleHouseIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private StatSaleHouseDataOnDemand StatSaleHouseIntegrationTest.dod;
    
    @Test
    public void StatSaleHouseIntegrationTest.testCountStatSaleHouses() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleHouse' failed to initialize correctly", dod.getRandomStatSaleHouse());
        long count = biz.rageintegro.erealt.domain.StatSaleHouse.countStatSaleHouses();
        org.junit.Assert.assertTrue("Counter for 'StatSaleHouse' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void StatSaleHouseIntegrationTest.testFindStatSaleHouse() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleHouse' failed to initialize correctly", dod.getRandomStatSaleHouse());
        java.lang.Long id = dod.getRandomStatSaleHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleHouse obj = biz.rageintegro.erealt.domain.StatSaleHouse.findStatSaleHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleHouse' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'StatSaleHouse' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void StatSaleHouseIntegrationTest.testFindAllStatSaleHouses() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleHouse' failed to initialize correctly", dod.getRandomStatSaleHouse());
        long count = biz.rageintegro.erealt.domain.StatSaleHouse.countStatSaleHouses();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'StatSaleHouse', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.StatSaleHouse> result = biz.rageintegro.erealt.domain.StatSaleHouse.findAllStatSaleHouses();
        org.junit.Assert.assertNotNull("Find all method for 'StatSaleHouse' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'StatSaleHouse' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void StatSaleHouseIntegrationTest.testFindStatSaleHouseEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleHouse' failed to initialize correctly", dod.getRandomStatSaleHouse());
        long count = biz.rageintegro.erealt.domain.StatSaleHouse.countStatSaleHouses();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.StatSaleHouse> result = biz.rageintegro.erealt.domain.StatSaleHouse.findStatSaleHouseEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'StatSaleHouse' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'StatSaleHouse' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void StatSaleHouseIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleHouse' failed to initialize correctly", dod.getRandomStatSaleHouse());
        java.lang.Long id = dod.getRandomStatSaleHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleHouse obj = biz.rageintegro.erealt.domain.StatSaleHouse.findStatSaleHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleHouse' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStatSaleHouse(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'StatSaleHouse' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void StatSaleHouseIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleHouse' failed to initialize correctly", dod.getRandomStatSaleHouse());
        java.lang.Long id = dod.getRandomStatSaleHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleHouse obj = biz.rageintegro.erealt.domain.StatSaleHouse.findStatSaleHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleHouse' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStatSaleHouse(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'StatSaleHouse' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void StatSaleHouseIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleHouse' failed to initialize correctly", dod.getRandomStatSaleHouse());
        biz.rageintegro.erealt.domain.StatSaleHouse obj = dod.getNewTransientStatSaleHouse(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleHouse' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'StatSaleHouse' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'StatSaleHouse' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void StatSaleHouseIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleHouse' failed to initialize correctly", dod.getRandomStatSaleHouse());
        java.lang.Long id = dod.getRandomStatSaleHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleHouse obj = biz.rageintegro.erealt.domain.StatSaleHouse.findStatSaleHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleHouse' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'StatSaleHouse' with identifier '" + id + "'", biz.rageintegro.erealt.domain.StatSaleHouse.findStatSaleHouse(id));
    }
    
}
