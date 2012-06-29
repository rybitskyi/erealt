package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatLeaseHouseDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StatLeaseHouseIntegrationTest_Roo_IntegrationTest {
    
    declare @type: StatLeaseHouseIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: StatLeaseHouseIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private StatLeaseHouseDataOnDemand StatLeaseHouseIntegrationTest.dod;
    
    @Test
    public void StatLeaseHouseIntegrationTest.testCountStatLeaseHouses() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseHouse' failed to initialize correctly", dod.getRandomStatLeaseHouse());
        long count = biz.rageintegro.erealt.domain.StatLeaseHouse.countStatLeaseHouses();
        org.junit.Assert.assertTrue("Counter for 'StatLeaseHouse' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void StatLeaseHouseIntegrationTest.testFindStatLeaseHouse() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseHouse' failed to initialize correctly", dod.getRandomStatLeaseHouse());
        java.lang.Long id = dod.getRandomStatLeaseHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatLeaseHouse obj = biz.rageintegro.erealt.domain.StatLeaseHouse.findStatLeaseHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'StatLeaseHouse' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'StatLeaseHouse' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void StatLeaseHouseIntegrationTest.testFindAllStatLeaseHouses() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseHouse' failed to initialize correctly", dod.getRandomStatLeaseHouse());
        long count = biz.rageintegro.erealt.domain.StatLeaseHouse.countStatLeaseHouses();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'StatLeaseHouse', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.StatLeaseHouse> result = biz.rageintegro.erealt.domain.StatLeaseHouse.findAllStatLeaseHouses();
        org.junit.Assert.assertNotNull("Find all method for 'StatLeaseHouse' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'StatLeaseHouse' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void StatLeaseHouseIntegrationTest.testFindStatLeaseHouseEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseHouse' failed to initialize correctly", dod.getRandomStatLeaseHouse());
        long count = biz.rageintegro.erealt.domain.StatLeaseHouse.countStatLeaseHouses();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.StatLeaseHouse> result = biz.rageintegro.erealt.domain.StatLeaseHouse.findStatLeaseHouseEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'StatLeaseHouse' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'StatLeaseHouse' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void StatLeaseHouseIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseHouse' failed to initialize correctly", dod.getRandomStatLeaseHouse());
        java.lang.Long id = dod.getRandomStatLeaseHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatLeaseHouse obj = biz.rageintegro.erealt.domain.StatLeaseHouse.findStatLeaseHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'StatLeaseHouse' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStatLeaseHouse(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'StatLeaseHouse' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void StatLeaseHouseIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseHouse' failed to initialize correctly", dod.getRandomStatLeaseHouse());
        java.lang.Long id = dod.getRandomStatLeaseHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatLeaseHouse obj = biz.rageintegro.erealt.domain.StatLeaseHouse.findStatLeaseHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'StatLeaseHouse' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStatLeaseHouse(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'StatLeaseHouse' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void StatLeaseHouseIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseHouse' failed to initialize correctly", dod.getRandomStatLeaseHouse());
        biz.rageintegro.erealt.domain.StatLeaseHouse obj = dod.getNewTransientStatLeaseHouse(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseHouse' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'StatLeaseHouse' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'StatLeaseHouse' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void StatLeaseHouseIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseHouse' failed to initialize correctly", dod.getRandomStatLeaseHouse());
        java.lang.Long id = dod.getRandomStatLeaseHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatLeaseHouse obj = biz.rageintegro.erealt.domain.StatLeaseHouse.findStatLeaseHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'StatLeaseHouse' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'StatLeaseHouse' with identifier '" + id + "'", biz.rageintegro.erealt.domain.StatLeaseHouse.findStatLeaseHouse(id));
    }
    
}
