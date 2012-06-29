package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.RegionDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect RegionIntegrationTest_Roo_IntegrationTest {
    
    declare @type: RegionIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: RegionIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private RegionDataOnDemand RegionIntegrationTest.dod;
    
    @Test
    public void RegionIntegrationTest.testCountRegions() {
        org.junit.Assert.assertNotNull("Data on demand for 'Region' failed to initialize correctly", dod.getRandomRegion());
        long count = biz.rageintegro.erealt.domain.Region.countRegions();
        org.junit.Assert.assertTrue("Counter for 'Region' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void RegionIntegrationTest.testFindRegion() {
        org.junit.Assert.assertNotNull("Data on demand for 'Region' failed to initialize correctly", dod.getRandomRegion());
        java.lang.Long id = dod.getRandomRegion().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Region' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.Region obj = biz.rageintegro.erealt.domain.Region.findRegion(id);
        org.junit.Assert.assertNotNull("Find method for 'Region' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Region' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void RegionIntegrationTest.testFindAllRegions() {
        org.junit.Assert.assertNotNull("Data on demand for 'Region' failed to initialize correctly", dod.getRandomRegion());
        long count = biz.rageintegro.erealt.domain.Region.countRegions();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Region', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.Region> result = biz.rageintegro.erealt.domain.Region.findAllRegions();
        org.junit.Assert.assertNotNull("Find all method for 'Region' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Region' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void RegionIntegrationTest.testFindRegionEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Region' failed to initialize correctly", dod.getRandomRegion());
        long count = biz.rageintegro.erealt.domain.Region.countRegions();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.Region> result = biz.rageintegro.erealt.domain.Region.findRegionEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'Region' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Region' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void RegionIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'Region' failed to initialize correctly", dod.getRandomRegion());
        java.lang.Long id = dod.getRandomRegion().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Region' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.Region obj = biz.rageintegro.erealt.domain.Region.findRegion(id);
        org.junit.Assert.assertNotNull("Find method for 'Region' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyRegion(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Region' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void RegionIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'Region' failed to initialize correctly", dod.getRandomRegion());
        java.lang.Long id = dod.getRandomRegion().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Region' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.Region obj = biz.rageintegro.erealt.domain.Region.findRegion(id);
        org.junit.Assert.assertNotNull("Find method for 'Region' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyRegion(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Region' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void RegionIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Region' failed to initialize correctly", dod.getRandomRegion());
        biz.rageintegro.erealt.domain.Region obj = dod.getNewTransientRegion(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Region' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Region' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Region' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void RegionIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'Region' failed to initialize correctly", dod.getRandomRegion());
        java.lang.Long id = dod.getRandomRegion().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Region' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.Region obj = biz.rageintegro.erealt.domain.Region.findRegion(id);
        org.junit.Assert.assertNotNull("Find method for 'Region' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'Region' with identifier '" + id + "'", biz.rageintegro.erealt.domain.Region.findRegion(id));
    }
    
}
