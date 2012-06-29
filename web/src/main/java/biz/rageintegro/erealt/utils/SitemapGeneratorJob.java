package biz.rageintegro.erealt.utils;

import org.springframework.scheduling.quartz.QuartzJobBean;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.File;
import java.util.List;

/**
 * User: RybitskyiI
 * Date: 17/2/2011
 */
public class SitemapGeneratorJob extends QuartzJobBean {

    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SitemapGenerator gen = new SitemapGenerator();
        String root = System.getProperty("erealt.root");
        File f = new File(new File(root), "sitemap.xml");
        gen.setFile(f);
        gen.run();
    }
}
