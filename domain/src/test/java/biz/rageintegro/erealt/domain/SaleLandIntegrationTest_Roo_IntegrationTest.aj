package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.SaleLandDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SaleLandIntegrationTest_Roo_IntegrationTest {
    
    declare @type: SaleLandIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: SaleLandIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private SaleLandDataOnDemand SaleLandIntegrationTest.dod;
    
    @Test
    public void SaleLandIntegrationTest.testCountSaleLands() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleLand' failed to initialize correctly", dod.getRandomSaleLand());
        long count = biz.rageintegro.erealt.domain.SaleLand.countSaleLands();
        org.junit.Assert.assertTrue("Counter for 'SaleLand' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void SaleLandIntegrationTest.testFindSaleLand() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleLand' failed to initialize correctly", dod.getRandomSaleLand());
        java.lang.Long id = dod.getRandomSaleLand().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleLand' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleLand obj = biz.rageintegro.erealt.domain.SaleLand.findSaleLand(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleLand' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'SaleLand' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void SaleLandIntegrationTest.testFindAllSaleLands() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleLand' failed to initialize correctly", dod.getRandomSaleLand());
        long count = biz.rageintegro.erealt.domain.SaleLand.countSaleLands();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'SaleLand', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.SaleLand> result = biz.rageintegro.erealt.domain.SaleLand.findAllSaleLands();
        org.junit.Assert.assertNotNull("Find all method for 'SaleLand' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'SaleLand' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void SaleLandIntegrationTest.testFindSaleLandEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleLand' failed to initialize correctly", dod.getRandomSaleLand());
        long count = biz.rageintegro.erealt.domain.SaleLand.countSaleLands();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.SaleLand> result = biz.rageintegro.erealt.domain.SaleLand.findSaleLandEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'SaleLand' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'SaleLand' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void SaleLandIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleLand' failed to initialize correctly", dod.getRandomSaleLand());
        java.lang.Long id = dod.getRandomSaleLand().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleLand' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleLand obj = biz.rageintegro.erealt.domain.SaleLand.findSaleLand(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleLand' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySaleLand(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'SaleLand' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void SaleLandIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleLand' failed to initialize correctly", dod.getRandomSaleLand());
        java.lang.Long id = dod.getRandomSaleLand().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleLand' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleLand obj = biz.rageintegro.erealt.domain.SaleLand.findSaleLand(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleLand' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySaleLand(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'SaleLand' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void SaleLandIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleLand' failed to initialize correctly", dod.getRandomSaleLand());
        biz.rageintegro.erealt.domain.SaleLand obj = dod.getNewTransientSaleLand(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'SaleLand' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'SaleLand' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'SaleLand' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void SaleLandIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'SaleLand' failed to initialize correctly", dod.getRandomSaleLand());
        java.lang.Long id = dod.getRandomSaleLand().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'SaleLand' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.SaleLand obj = biz.rageintegro.erealt.domain.SaleLand.findSaleLand(id);
        org.junit.Assert.assertNotNull("Find method for 'SaleLand' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'SaleLand' with identifier '" + id + "'", biz.rageintegro.erealt.domain.SaleLand.findSaleLand(id));
    }
    
}
