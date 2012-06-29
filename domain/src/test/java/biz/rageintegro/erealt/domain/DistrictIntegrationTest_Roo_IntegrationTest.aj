package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.domain.DistrictDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect DistrictIntegrationTest_Roo_IntegrationTest {
    
    declare @type: DistrictIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: DistrictIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    @Autowired
    private DistrictDataOnDemand DistrictIntegrationTest.dod;
    
    @Test
    public void DistrictIntegrationTest.testCountDistricts() {
        org.junit.Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", dod.getRandomDistrict());
        long count = biz.rageintegro.erealt.domain.District.countDistricts();
        org.junit.Assert.assertTrue("Counter for 'District' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void DistrictIntegrationTest.testFindDistrict() {
        org.junit.Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", dod.getRandomDistrict());
        java.lang.Long id = dod.getRandomDistrict().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'District' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.District obj = biz.rageintegro.erealt.domain.District.findDistrict(id);
        org.junit.Assert.assertNotNull("Find method for 'District' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'District' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void DistrictIntegrationTest.testFindAllDistricts() {
        org.junit.Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", dod.getRandomDistrict());
        long count = biz.rageintegro.erealt.domain.District.countDistricts();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'District', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<biz.rageintegro.erealt.domain.District> result = biz.rageintegro.erealt.domain.District.findAllDistricts();
        org.junit.Assert.assertNotNull("Find all method for 'District' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'District' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void DistrictIntegrationTest.testFindDistrictEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", dod.getRandomDistrict());
        long count = biz.rageintegro.erealt.domain.District.countDistricts();
        if (count > 20) count = 20;
        java.util.List<biz.rageintegro.erealt.domain.District> result = biz.rageintegro.erealt.domain.District.findDistrictEntries(0, (int)count);
        org.junit.Assert.assertNotNull("Find entries method for 'District' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'District' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    @Transactional
    public void DistrictIntegrationTest.testFlush() {
        org.junit.Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", dod.getRandomDistrict());
        java.lang.Long id = dod.getRandomDistrict().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'District' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.District obj = biz.rageintegro.erealt.domain.District.findDistrict(id);
        org.junit.Assert.assertNotNull("Find method for 'District' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyDistrict(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'District' failed to increment on flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void DistrictIntegrationTest.testMerge() {
        org.junit.Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", dod.getRandomDistrict());
        java.lang.Long id = dod.getRandomDistrict().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'District' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.District obj = biz.rageintegro.erealt.domain.District.findDistrict(id);
        org.junit.Assert.assertNotNull("Find method for 'District' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyDistrict(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.merge();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'District' failed to increment on merge and flush directive", obj.getVersion() > currentVersion || !modified);
    }
    
    @Test
    @Transactional
    public void DistrictIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", dod.getRandomDistrict());
        biz.rageintegro.erealt.domain.District obj = dod.getNewTransientDistrict(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'District' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'District' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'District' identifier to no longer be null", obj.getId());
    }
    
    @Test
    @Transactional
    public void DistrictIntegrationTest.testRemove() {
        org.junit.Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", dod.getRandomDistrict());
        java.lang.Long id = dod.getRandomDistrict().getId();
        org.junit.Assert.assertNotNull("Data on demand for 'District' failed to provide an identifier", id);
        biz.rageintegro.erealt.domain.District obj = biz.rageintegro.erealt.domain.District.findDistrict(id);
        org.junit.Assert.assertNotNull("Find method for 'District' illegally returned null for id '" + id + "'", obj);
        obj.remove();
        org.junit.Assert.assertNull("Failed to remove 'District' with identifier '" + id + "'", biz.rageintegro.erealt.domain.District.findDistrict(id));
    }
    
}
