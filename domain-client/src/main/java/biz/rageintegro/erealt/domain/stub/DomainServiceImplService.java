
package biz.rageintegro.erealt.domain.stub;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-b01-
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "DomainServiceImplService", targetNamespace = "http://ws.domain.erealt.rageintegro.biz/", wsdlLocation = "file:/C:/Users/yury.ribitsky/Documents/private/projects/erealtcomua/domain-client/src/main/resources/wsdl/DomainServiceImplService.wsdl")
public class DomainServiceImplService
    extends Service
{

    private final static URL DOMAINSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(biz.rageintegro.erealt.domain.stub.DomainServiceImplService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = biz.rageintegro.erealt.domain.stub.DomainServiceImplService.class.getResource(".");
            url = new URL(baseUrl, "file:/C:/Users/yury.ribitsky/Documents/private/projects/erealtcomua/domain-client/src/main/resources/wsdl/DomainServiceImplService.wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'file:/C:/Users/yury.ribitsky/Documents/private/projects/erealtcomua/domain-client/src/main/resources/wsdl/DomainServiceImplService.wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        DOMAINSERVICEIMPLSERVICE_WSDL_LOCATION = url;
    }

    public DomainServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DomainServiceImplService() {
        super(DOMAINSERVICEIMPLSERVICE_WSDL_LOCATION, new QName("http://ws.domain.erealt.rageintegro.biz/", "DomainServiceImplService"));
    }

    /**
     * 
     * @return
     *     returns DomainService
     */
    @WebEndpoint(name = "DomainServiceImplPort")
    public DomainService getDomainServiceImplPort() {
        return super.getPort(new QName("http://ws.domain.erealt.rageintegro.biz/", "DomainServiceImplPort"), DomainService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DomainService
     */
    @WebEndpoint(name = "DomainServiceImplPort")
    public DomainService getDomainServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.domain.erealt.rageintegro.biz/", "DomainServiceImplPort"), DomainService.class, features);
    }

}
