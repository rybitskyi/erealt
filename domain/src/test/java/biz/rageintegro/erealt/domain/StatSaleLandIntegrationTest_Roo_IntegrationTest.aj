package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatSaleLandDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StatSaleLandIntegrationTest_Roo_IntegrationTest {
    
    declare @type: StatSaleLandIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: StatSaleLandIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private StatSaleLandDataOnDemand StatSaleLandIntegrationTest.dod;
    
    @Test
    public void StatSaleLandIntegrationTest.testCountStatSaleLands() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleLand' failed to initialize correctly", dod.getRandomStatSaleLand());
        long count = biz.rageintegro.erealt.domain.StatSaleLand.countStatSaleLands();
        org.junit.Assert.assertTrue("Counter for 'StatSaleLand' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void StatSaleLandIntegrationTest.testFindStatSaleLand() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleLand' failed to initialize correctly", dod.getRandomStatSaleLand());
        java.lang.Long id = dod.getRandomStatSaleLand().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleLand' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleLand obj = biz.rageintegro.erealt.domain.StatSaleLand.findStatSaleLand(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleLand' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'StatSaleLand' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void StatSaleLandIntegrationTest.testFindAllStatSaleLands() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleLand' failed to initialize correctly", dod.getRandomStatSaleLand());
        long count = biz.rageintegro.erealt.domain.StatSaleLand.countStatSaleLands();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'StatSaleLand', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.StatSaleLand> result = biz.rageintegro.erealt.domain.StatSaleLand.findAllStatSaleLands();
        org.junit.Assert.assertNotNull("Find all method for 'StatSaleLand' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'StatSaleLand' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void StatSaleLandIntegrationTest.testFindStatSaleLandEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleLand' failed to initialize correctly", dod.getRandomStatSaleLand());
        long count = biz.rageintegro.erealt.domain.StatSaleLand.countStatSaleLands();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.StatSaleLand> result = biz.rageintegro.erealt.domain.StatSaleLand.findStatSaleLandEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'StatSaleLand' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'StatSaleLand' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void StatSaleLandIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleLand' failed to initialize correctly", dod.getRandomStatSaleLand());
        java.lang.Long id = dod.getRandomStatSaleLand().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleLand' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleLand obj = biz.rageintegro.erealt.domain.StatSaleLand.findStatSaleLand(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleLand' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStatSaleLand(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'StatSaleLand' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void StatSaleLandIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleLand' failed to initialize correctly", dod.getRandomStatSaleLand());
        java.lang.Long id = dod.getRandomStatSaleLand().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleLand' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleLand obj = biz.rageintegro.erealt.domain.StatSaleLand.findStatSaleLand(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleLand' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStatSaleLand(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'StatSaleLand' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void StatSaleLandIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleLand' failed to initialize correctly", dod.getRandomStatSaleLand());
        biz.rageintegro.erealt.domain.StatSaleLand obj = dod.getNewTransientStatSaleLand(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleLand' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'StatSaleLand' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'StatSaleLand' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void StatSaleLandIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleLand' failed to initialize correctly", dod.getRandomStatSaleLand());
        java.lang.Long id = dod.getRandomStatSaleLand().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleLand' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleLand obj = biz.rageintegro.erealt.domain.StatSaleLand.findStatSaleLand(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleLand' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'StatSaleLand' with identifier '" + id + "'", biz.rageintegro.erealt.domain.StatSaleLand.findStatSaleLand(id));
    }
    
}
