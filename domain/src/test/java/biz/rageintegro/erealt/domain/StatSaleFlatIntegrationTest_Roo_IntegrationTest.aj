package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StatSaleFlatDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StatSaleFlatIntegrationTest_Roo_IntegrationTest {
    
    declare @type: StatSaleFlatIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: StatSaleFlatIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private StatSaleFlatDataOnDemand StatSaleFlatIntegrationTest.dod;
    
    @Test
    public void StatSaleFlatIntegrationTest.testCountStatSaleFlats() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleFlat' failed to initialize correctly", dod.getRandomStatSaleFlat());
        long count = biz.rageintegro.erealt.domain.StatSaleFlat.countStatSaleFlats();
        org.junit.Assert.assertTrue("Counter for 'StatSaleFlat' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void StatSaleFlatIntegrationTest.testFindStatSaleFlat() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleFlat' failed to initialize correctly", dod.getRandomStatSaleFlat());
        java.lang.Long id = dod.getRandomStatSaleFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleFlat obj = biz.rageintegro.erealt.domain.StatSaleFlat.findStatSaleFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleFlat' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'StatSaleFlat' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void StatSaleFlatIntegrationTest.testFindAllStatSaleFlats() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleFlat' failed to initialize correctly", dod.getRandomStatSaleFlat());
        long count = biz.rageintegro.erealt.domain.StatSaleFlat.countStatSaleFlats();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'StatSaleFlat', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.StatSaleFlat> result = biz.rageintegro.erealt.domain.StatSaleFlat.findAllStatSaleFlats();
        org.junit.Assert.assertNotNull("Find all method for 'StatSaleFlat' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'StatSaleFlat' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void StatSaleFlatIntegrationTest.testFindStatSaleFlatEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleFlat' failed to initialize correctly", dod.getRandomStatSaleFlat());
        long count = biz.rageintegro.erealt.domain.StatSaleFlat.countStatSaleFlats();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.StatSaleFlat> result = biz.rageintegro.erealt.domain.StatSaleFlat.findStatSaleFlatEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'StatSaleFlat' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'StatSaleFlat' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void StatSaleFlatIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleFlat' failed to initialize correctly", dod.getRandomStatSaleFlat());
        java.lang.Long id = dod.getRandomStatSaleFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleFlat obj = biz.rageintegro.erealt.domain.StatSaleFlat.findStatSaleFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleFlat' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStatSaleFlat(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'StatSaleFlat' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void StatSaleFlatIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleFlat' failed to initialize correctly", dod.getRandomStatSaleFlat());
        java.lang.Long id = dod.getRandomStatSaleFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleFlat obj = biz.rageintegro.erealt.domain.StatSaleFlat.findStatSaleFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleFlat' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStatSaleFlat(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'StatSaleFlat' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void StatSaleFlatIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleFlat' failed to initialize correctly", dod.getRandomStatSaleFlat());
        biz.rageintegro.erealt.domain.StatSaleFlat obj = dod.getNewTransientStatSaleFlat(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleFlat' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'StatSaleFlat' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'StatSaleFlat' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void StatSaleFlatIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleFlat' failed to initialize correctly", dod.getRandomStatSaleFlat());
        java.lang.Long id = dod.getRandomStatSaleFlat().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StatSaleFlat' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.StatSaleFlat obj = biz.rageintegro.erealt.domain.StatSaleFlat.findStatSaleFlat(id);
        org.junit.Assert.assertNotNull("Find method for 'StatSaleFlat' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'StatSaleFlat' with identifier '" + id + "'", biz.rageintegro.erealt.domain.StatSaleFlat.findStatSaleFlat(id));
    }
    
}
