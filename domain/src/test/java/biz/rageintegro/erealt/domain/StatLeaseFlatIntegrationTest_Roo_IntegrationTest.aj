package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatLeaseFlatDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StatLeaseFlatIntegrationTest_Roo_IntegrationTest {
    
    declare @type: StatLeaseFlatIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: StatLeaseFlatIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private StatLeaseFlatDataOnDemand StatLeaseFlatIntegrationTest.dod;
    
    @Test
    public void StatLeaseFlatIntegrationTest.testCountStatLeaseFlats() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseFlat' failed to initialize correctly", dod.getRandomStatLeaseFlat());
        long count = biz.rageintegro.erealt.domain.StatLeaseFlat.countStatLeaseFlats();
        org.junit.Assert.assertTrue("Counter for 'StatLeaseFlat' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void StatLeaseFlatIntegrationTest.testFindStatLeaseFlat() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseFlat' failed to initialize correctly", dod.getRandomStatLeaseFlat());
        java.lang.Long id = dod.getRandomStatLeaseFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatLeaseFlat obj = biz.rageintegro.erealt.domain.StatLeaseFlat.findStatLeaseFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'StatLeaseFlat' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'StatLeaseFlat' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void StatLeaseFlatIntegrationTest.testFindAllStatLeaseFlats() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseFlat' failed to initialize correctly", dod.getRandomStatLeaseFlat());
        long count = biz.rageintegro.erealt.domain.StatLeaseFlat.countStatLeaseFlats();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'StatLeaseFlat', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.StatLeaseFlat> result = biz.rageintegro.erealt.domain.StatLeaseFlat.findAllStatLeaseFlats();
        org.junit.Assert.assertNotNull("Find all method for 'StatLeaseFlat' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'StatLeaseFlat' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void StatLeaseFlatIntegrationTest.testFindStatLeaseFlatEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseFlat' failed to initialize correctly", dod.getRandomStatLeaseFlat());
        long count = biz.rageintegro.erealt.domain.StatLeaseFlat.countStatLeaseFlats();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.StatLeaseFlat> result = biz.rageintegro.erealt.domain.StatLeaseFlat.findStatLeaseFlatEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'StatLeaseFlat' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'StatLeaseFlat' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void StatLeaseFlatIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseFlat' failed to initialize correctly", dod.getRandomStatLeaseFlat());
        java.lang.Long id = dod.getRandomStatLeaseFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatLeaseFlat obj = biz.rageintegro.erealt.domain.StatLeaseFlat.findStatLeaseFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'StatLeaseFlat' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStatLeaseFlat(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'StatLeaseFlat' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void StatLeaseFlatIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseFlat' failed to initialize correctly", dod.getRandomStatLeaseFlat());
        java.lang.Long id = dod.getRandomStatLeaseFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatLeaseFlat obj = biz.rageintegro.erealt.domain.StatLeaseFlat.findStatLeaseFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'StatLeaseFlat' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStatLeaseFlat(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'StatLeaseFlat' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void StatLeaseFlatIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseFlat' failed to initialize correctly", dod.getRandomStatLeaseFlat());
        biz.rageintegro.erealt.domain.StatLeaseFlat obj = dod.getNewTransientStatLeaseFlat(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseFlat' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'StatLeaseFlat' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'StatLeaseFlat' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void StatLeaseFlatIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseFlat' failed to initialize correctly", dod.getRandomStatLeaseFlat());
        java.lang.Long id = dod.getRandomStatLeaseFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatLeaseFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatLeaseFlat obj = biz.rageintegro.erealt.domain.StatLeaseFlat.findStatLeaseFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'StatLeaseFlat' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'StatLeaseFlat' with identifier '" + id + "'", biz.rageintegro.erealt.domain.StatLeaseFlat.findStatLeaseFlat(id));
    }
    
}
