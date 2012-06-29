package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.LeaseFlatDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect LeaseFlatIntegrationTest_Roo_IntegrationTest {
    
    declare @type: LeaseFlatIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: LeaseFlatIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private LeaseFlatDataOnDemand LeaseFlatIntegrationTest.dod;
    
    @Test
    public void LeaseFlatIntegrationTest.testCountLeaseFlats() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseFlat' failed to initialize correctly", dod.getRandomLeaseFlat());
        long count = biz.rageintegro.erealt.domain.LeaseFlat.countLeaseFlats();
        org.junit.Assert.assertTrue("Counter for 'LeaseFlat' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void LeaseFlatIntegrationTest.testFindLeaseFlat() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseFlat' failed to initialize correctly", dod.getRandomLeaseFlat());
        java.lang.Long id = dod.getRandomLeaseFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.LeaseFlat obj = biz.rageintegro.erealt.domain.LeaseFlat.findLeaseFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'LeaseFlat' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'LeaseFlat' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void LeaseFlatIntegrationTest.testFindAllLeaseFlats() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseFlat' failed to initialize correctly", dod.getRandomLeaseFlat());
        long count = biz.rageintegro.erealt.domain.LeaseFlat.countLeaseFlats();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'LeaseFlat', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.LeaseFlat> result = biz.rageintegro.erealt.domain.LeaseFlat.findAllLeaseFlats();
        org.junit.Assert.assertNotNull("Find all method for 'LeaseFlat' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'LeaseFlat' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void LeaseFlatIntegrationTest.testFindLeaseFlatEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseFlat' failed to initialize correctly", dod.getRandomLeaseFlat());
        long count = biz.rageintegro.erealt.domain.LeaseFlat.countLeaseFlats();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.LeaseFlat> result = biz.rageintegro.erealt.domain.LeaseFlat.findLeaseFlatEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'LeaseFlat' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'LeaseFlat' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void LeaseFlatIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseFlat' failed to initialize correctly", dod.getRandomLeaseFlat());
        java.lang.Long id = dod.getRandomLeaseFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.LeaseFlat obj = biz.rageintegro.erealt.domain.LeaseFlat.findLeaseFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'LeaseFlat' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyLeaseFlat(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'LeaseFlat' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void LeaseFlatIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseFlat' failed to initialize correctly", dod.getRandomLeaseFlat());
        java.lang.Long id = dod.getRandomLeaseFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.LeaseFlat obj = biz.rageintegro.erealt.domain.LeaseFlat.findLeaseFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'LeaseFlat' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyLeaseFlat(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'LeaseFlat' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void LeaseFlatIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseFlat' failed to initialize correctly", dod.getRandomLeaseFlat());
        biz.rageintegro.erealt.domain.LeaseFlat obj = dod.getNewTransientLeaseFlat(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseFlat' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'LeaseFlat' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'LeaseFlat' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void LeaseFlatIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseFlat' failed to initialize correctly", dod.getRandomLeaseFlat());
        java.lang.Long id = dod.getRandomLeaseFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'LeaseFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.LeaseFlat obj = biz.rageintegro.erealt.domain.LeaseFlat.findLeaseFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'LeaseFlat' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'LeaseFlat' with identifier '" + id + "'", biz.rageintegro.erealt.domain.LeaseFlat.findLeaseFlat(id));
    }
    
}
