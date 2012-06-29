package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.LeaseHouseDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect LeaseHouseIntegrationTest_Roo_IntegrationTest {
    
    declare @type: LeaseHouseIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: LeaseHouseIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private LeaseHouseDataOnDemand LeaseHouseIntegrationTest.dod;
    
    @Test
    public void LeaseHouseIntegrationTest.testCountLeaseHouses() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseHouse' failed to initialize correctly", dod.getRandomLeaseHouse());
        long count = biz.rageintegro.erealt.domain.LeaseHouse.countLeaseHouses();
        org.junit.Assert.assertTrue("Counter for 'LeaseHouse' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void LeaseHouseIntegrationTest.testFindLeaseHouse() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseHouse' failed to initialize correctly", dod.getRandomLeaseHouse());
        java.lang.Long id = dod.getRandomLeaseHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.LeaseHouse obj = biz.rageintegro.erealt.domain.LeaseHouse.findLeaseHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'LeaseHouse' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'LeaseHouse' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void LeaseHouseIntegrationTest.testFindAllLeaseHouses() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseHouse' failed to initialize correctly", dod.getRandomLeaseHouse());
        long count = biz.rageintegro.erealt.domain.LeaseHouse.countLeaseHouses();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'LeaseHouse', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.LeaseHouse> result = biz.rageintegro.erealt.domain.LeaseHouse.findAllLeaseHouses();
        org.junit.Assert.assertNotNull("Find all method for 'LeaseHouse' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'LeaseHouse' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void LeaseHouseIntegrationTest.testFindLeaseHouseEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseHouse' failed to initialize correctly", dod.getRandomLeaseHouse());
        long count = biz.rageintegro.erealt.domain.LeaseHouse.countLeaseHouses();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.LeaseHouse> result = biz.rageintegro.erealt.domain.LeaseHouse.findLeaseHouseEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'LeaseHouse' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'LeaseHouse' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void LeaseHouseIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseHouse' failed to initialize correctly", dod.getRandomLeaseHouse());
        java.lang.Long id = dod.getRandomLeaseHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.LeaseHouse obj = biz.rageintegro.erealt.domain.LeaseHouse.findLeaseHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'LeaseHouse' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyLeaseHouse(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'LeaseHouse' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void LeaseHouseIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseHouse' failed to initialize correctly", dod.getRandomLeaseHouse());
        java.lang.Long id = dod.getRandomLeaseHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.LeaseHouse obj = biz.rageintegro.erealt.domain.LeaseHouse.findLeaseHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'LeaseHouse' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyLeaseHouse(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'LeaseHouse' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void LeaseHouseIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseHouse' failed to initialize correctly", dod.getRandomLeaseHouse());
        biz.rageintegro.erealt.domain.LeaseHouse obj = dod.getNewTransientLeaseHouse(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseHouse' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'LeaseHouse' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'LeaseHouse' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void LeaseHouseIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseHouse' failed to initialize correctly", dod.getRandomLeaseHouse());
        java.lang.Long id = dod.getRandomLeaseHouse().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseHouse' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.LeaseHouse obj = biz.rageintegro.erealt.domain.LeaseHouse.findLeaseHouse(id);
        org.junit.Assert.assertNotNull("Find method for 'LeaseHouse' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'LeaseHouse' with identifier '" + id + "'", biz.rageintegro.erealt.domain.LeaseHouse.findLeaseHouse(id));
    }
    
}
