package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatSaleNewFlatDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StatSaleNewFlatIntegrationTest_Roo_IntegrationTest {
    
    declare @type: StatSaleNewFlatIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: StatSaleNewFlatIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private StatSaleNewFlatDataOnDemand StatSaleNewFlatIntegrationTest.dod;
    
    @Test
    public void StatSaleNewFlatIntegrationTest.testCountStatSaleNewFlats() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleNewFlat' failed to initialize correctly", dod.getRandomStatSaleNewFlat());
        long count = biz.rageintegro.erealt.domain.StatSaleNewFlat.countStatSaleNewFlats();
        org.junit.Assert.assertTrue("Counter for 'StatSaleNewFlat' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void StatSaleNewFlatIntegrationTest.testFindStatSaleNewFlat() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleNewFlat' failed to initialize correctly", dod.getRandomStatSaleNewFlat());
        java.lang.Long id = dod.getRandomStatSaleNewFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleNewFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleNewFlat obj = biz.rageintegro.erealt.domain.StatSaleNewFlat.findStatSaleNewFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleNewFlat' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'StatSaleNewFlat' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void StatSaleNewFlatIntegrationTest.testFindAllStatSaleNewFlats() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleNewFlat' failed to initialize correctly", dod.getRandomStatSaleNewFlat());
        long count = biz.rageintegro.erealt.domain.StatSaleNewFlat.countStatSaleNewFlats();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'StatSaleNewFlat', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.StatSaleNewFlat> result = biz.rageintegro.erealt.domain.StatSaleNewFlat.findAllStatSaleNewFlats();
        org.junit.Assert.assertNotNull("Find all method for 'StatSaleNewFlat' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'StatSaleNewFlat' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void StatSaleNewFlatIntegrationTest.testFindStatSaleNewFlatEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleNewFlat' failed to initialize correctly", dod.getRandomStatSaleNewFlat());
        long count = biz.rageintegro.erealt.domain.StatSaleNewFlat.countStatSaleNewFlats();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.StatSaleNewFlat> result = biz.rageintegro.erealt.domain.StatSaleNewFlat.findStatSaleNewFlatEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'StatSaleNewFlat' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'StatSaleNewFlat' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void StatSaleNewFlatIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleNewFlat' failed to initialize correctly", dod.getRandomStatSaleNewFlat());
        java.lang.Long id = dod.getRandomStatSaleNewFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleNewFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleNewFlat obj = biz.rageintegro.erealt.domain.StatSaleNewFlat.findStatSaleNewFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleNewFlat' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStatSaleNewFlat(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'StatSaleNewFlat' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void StatSaleNewFlatIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleNewFlat' failed to initialize correctly", dod.getRandomStatSaleNewFlat());
        java.lang.Long id = dod.getRandomStatSaleNewFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleNewFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleNewFlat obj = biz.rageintegro.erealt.domain.StatSaleNewFlat.findStatSaleNewFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleNewFlat' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStatSaleNewFlat(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'StatSaleNewFlat' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void StatSaleNewFlatIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleNewFlat' failed to initialize correctly", dod.getRandomStatSaleNewFlat());
        biz.rageintegro.erealt.domain.StatSaleNewFlat obj = dod.getNewTransientStatSaleNewFlat(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleNewFlat' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'StatSaleNewFlat' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'StatSaleNewFlat' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void StatSaleNewFlatIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleNewFlat' failed to initialize correctly", dod.getRandomStatSaleNewFlat());
        java.lang.Long id = dod.getRandomStatSaleNewFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleNewFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleNewFlat obj = biz.rageintegro.erealt.domain.StatSaleNewFlat.findStatSaleNewFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleNewFlat' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'StatSaleNewFlat' with identifier '" + id + "'", biz.rageintegro.erealt.domain.StatSaleNewFlat.findStatSaleNewFlat(id));
    }
    
}
