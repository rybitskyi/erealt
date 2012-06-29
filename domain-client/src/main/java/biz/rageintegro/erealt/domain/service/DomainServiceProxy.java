package biz.rageintegro.erealt.domain.service;

import biz.rageintegro.erealt.domain.stub.DomainService;
import biz.rageintegro.erealt.domain.stub.DomainServiceImplService;

import javax.xml.ws.BindingProvider;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DomainServiceProxy {
    private static final String PROP_FILE = "config.props";
    private String domainUrl;
    private DomainService domainService;

    public DomainServiceProxy() {
        readPropertiesFile();
        DomainServiceImplService service = new DomainServiceImplService();
        domainService = service.getDomainServiceImplPort();
        BindingProvider provider = (BindingProvider) domainService;
        System.out.println("domainUrl = " + domainUrl);
        provider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, domainUrl);
/*todo:
        provider.getRequestContext().put("j_username", "admin"); //todo: remove hard-conding
        provider.getRequestContext().put("j_password", "ganjahh1"); //todo: encode password
*/
    }

    public DomainService getDomainService() {
        return domainService;
    }

    private void readPropertiesFile() {
        InputStream is = null;
        try {
            String path = "/" + PROP_FILE;
            is = this.getClass().getResourceAsStream(path);
            if (is == null) {
                throw new RuntimeException("File can't be found by path " + path);
            }
            Properties prop = new Properties();
            prop.load(is);
            domainUrl = prop.getProperty("domain.url");
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from " + PROP_FILE + " file.", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
