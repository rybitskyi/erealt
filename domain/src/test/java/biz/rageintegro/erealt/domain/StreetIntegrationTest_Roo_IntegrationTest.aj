package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.StreetDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StreetIntegrationTest_Roo_IntegrationTest {
    
    declare @type: StreetIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: StreetIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private StreetDataOnDemand StreetIntegrationTest.dod;
    
    @Test
    public void StreetIntegrationTest.testCountStreets() {
        org.junit.Assert.assertNotNull("Data on demand for 'Street' failed to initialize correctly", dod.getRandomStreet());
        long count = biz.rageintegro.erealt.domain.Street.countStreets();
        org.junit.Assert.assertTrue("Counter for 'Street' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void StreetIntegrationTest.testFindStreet() {
        org.junit.Assert.assertNotNull("Data on demand for 'Street' failed to initialize correctly", dod.getRandomStreet());
        java.lang.Long id = dod.getRandomStreet().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Street' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.Street obj = biz.rageintegro.erealt.domain.Street.findStreet(id);
        org.junit.Assert.assertNotNull("Find method for 'Street' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Street' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void StreetIntegrationTest.testFindAllStreets() {
        org.junit.Assert.assertNotNull("Data on demand for 'Street' failed to initialize correctly", dod.getRandomStreet());
        long count = biz.rageintegro.erealt.domain.Street.countStreets();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Street', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.Street> result = biz.rageintegro.erealt.domain.Street.findAllStreets();
        org.junit.Assert.assertNotNull("Find all method for 'Street' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Street' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void StreetIntegrationTest.testFindStreetEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Street' failed to initialize correctly", dod.getRandomStreet());
        long count = biz.rageintegro.erealt.domain.Street.countStreets();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.Street> result = biz.rageintegro.erealt.domain.Street.findStreetEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'Street' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Street' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void StreetIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'Street' failed to initialize correctly", dod.getRandomStreet());
        java.lang.Long id = dod.getRandomStreet().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Street' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.Street obj = biz.rageintegro.erealt.domain.Street.findStreet(id);
        org.junit.Assert.assertNotNull("Find method for 'Street' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStreet(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Street' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void StreetIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'Street' failed to initialize correctly", dod.getRandomStreet());
        java.lang.Long id = dod.getRandomStreet().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Street' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.Street obj = biz.rageintegro.erealt.domain.Street.findStreet(id);
        org.junit.Assert.assertNotNull("Find method for 'Street' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStreet(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Street' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void StreetIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Street' failed to initialize correctly", dod.getRandomStreet());
        biz.rageintegro.erealt.domain.Street obj = dod.getNewTransientStreet(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Street' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Street' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Street' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void StreetIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'Street' failed to initialize correctly", dod.getRandomStreet());
        java.lang.Long id = dod.getRandomStreet().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Street' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.Street obj = biz.rageintegro.erealt.domain.Street.findStreet(id);
        org.junit.Assert.assertNotNull("Find method for 'Street' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'Street' with identifier '" + id + "'", biz.rageintegro.erealt.domain.Street.findStreet(id));
    }
    
}
