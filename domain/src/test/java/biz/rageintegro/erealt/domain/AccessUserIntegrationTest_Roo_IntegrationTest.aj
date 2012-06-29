package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.AccessUserDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AccessUserIntegrationTest_Roo_IntegrationTest {
    
    declare @type: AccessUserIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: AccessUserIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private AccessUserDataOnDemand AccessUserIntegrationTest.dod;
    
    @Test
    public void AccessUserIntegrationTest.testCountAccessUsers() {
        org.junit.Assert.assertNotNull("Data on demand for 'AccessUser' failed to initialize correctly", dod.getRandomAccessUser());
        long count = biz.rageintegro.erealt.domain.AccessUser.countAccessUsers();
        org.junit.Assert.assertTrue("Counter for 'AccessUser' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void AccessUserIntegrationTest.testFindAccessUser() {
        org.junit.Assert.assertNotNull("Data on demand for 'AccessUser' failed to initialize correctly", dod.getRandomAccessUser());
        java.lang.Long id = dod.getRandomAccessUser().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'AccessUser' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.AccessUser obj = biz.rageintegro.erealt.domain.AccessUser.findAccessUser(id);
        org.junit.Assert.assertNotNull("Find method for 'AccessUser' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'AccessUser' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void AccessUserIntegrationTest.testFindAllAccessUsers() {
        org.junit.Assert.assertNotNull("Data on demand for 'AccessUser' failed to initialize correctly", dod.getRandomAccessUser());
        long count = biz.rageintegro.erealt.domain.AccessUser.countAccessUsers();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'AccessUser', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.AccessUser> result = biz.rageintegro.erealt.domain.AccessUser.findAllAccessUsers();
        org.junit.Assert.assertNotNull("Find all method for 'AccessUser' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'AccessUser' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void AccessUserIntegrationTest.testFindAccessUserEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'AccessUser' failed to initialize correctly", dod.getRandomAccessUser());
        long count = biz.rageintegro.erealt.domain.AccessUser.countAccessUsers();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.AccessUser> result = biz.rageintegro.erealt.domain.AccessUser.findAccessUserEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'AccessUser' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'AccessUser' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void AccessUserIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'AccessUser' failed to initialize correctly", dod.getRandomAccessUser());
        java.lang.Long id = dod.getRandomAccessUser().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'AccessUser' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.AccessUser obj = biz.rageintegro.erealt.domain.AccessUser.findAccessUser(id);
        org.junit.Assert.assertNotNull("Find method for 'AccessUser' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAccessUser(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'AccessUser' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void AccessUserIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'AccessUser' failed to initialize correctly", dod.getRandomAccessUser());
        java.lang.Long id = dod.getRandomAccessUser().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'AccessUser' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.AccessUser obj = biz.rageintegro.erealt.domain.AccessUser.findAccessUser(id);
        org.junit.Assert.assertNotNull("Find method for 'AccessUser' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAccessUser(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'AccessUser' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void AccessUserIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'AccessUser' failed to initialize correctly", dod.getRandomAccessUser());
        biz.rageintegro.erealt.domain.AccessUser obj = dod.getNewTransientAccessUser(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'AccessUser' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'AccessUser' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'AccessUser' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void AccessUserIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'AccessUser' failed to initialize correctly", dod.getRandomAccessUser());
        java.lang.Long id = dod.getRandomAccessUser().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'AccessUser' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.AccessUser obj = biz.rageintegro.erealt.domain.AccessUser.findAccessUser(id);
        org.junit.Assert.assertNotNull("Find method for 'AccessUser' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'AccessUser' with identifier '" + id + "'", biz.rageintegro.erealt.domain.AccessUser.findAccessUser(id));
    }
    
}
