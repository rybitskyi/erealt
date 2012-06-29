/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.saas.google;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.saas.RestConnection;
import org.netbeans.saas.RestResponse;

/**
 * GoogleMapService Service
 *
 * @author yury.ribitsky
 */
public class GoogleMapService {
    
    private static String apiKey;
    private static final String PROP_FILE = GoogleMapService.class.getSimpleName().toLowerCase() + ".properties";
    
    static {
        //todo: localhost apiKey = "ABQIAAAAR8heUkWNxKsnWM5tuflVdRTwM0brOpm-All5BF6PoaKBxRWWERTRbC_Rc1JiXIkc5NrZc076jYs-IQ";
        apiKey = "ABQIAAAAR8heUkWNxKsnWM5tuflVdRQi9WdyO0Yg5-SO1LZm_wYQ8x3-chSE3GVn4SZ_zhQf7Ajpk0NNAiZ0hg";
/* todo: use it as property in file
        try {
            Properties props = new Properties();
            props.load(GoogleMapService.class.getResourceAsStream(PROP_FILE));
            apiKey = props.getProperty("api_key");
        } catch (IOException ex) {
            Logger.getLogger(GoogleMapService.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
    }
    
    public static String getApiKey() throws IOException {
        if (apiKey == null || apiKey.length() == 0) {
            throw new IOException("Please specify your api key in the " + PROP_FILE + " file.");
        }
        
        return apiKey;
    }

    /**
     * Returns HTML text to access GoogleMap.
     * @param address - address string to generate map for.
     */
    public static RestResponse getGoogleMap(String address, Integer zoom) throws IOException {
        try {
            String key = getApiKey();
            GeoCoder coder = new GeoCoder(address, apiKey);
            GeoCode code = coder.invoke();
            
                String mapRep =
                        "    <div id='map' class='map'></div>\n"
                        + getMapScript(address, zoom, code.getLatitude(), code.getLongitude(), key)
                        + "    <script type='text/javascript'>\n"
                        + "       loadScript();\n"
                        + "    </script>\n";
            return new RestResponse(mapRep.getBytes("UTF-8"));
        } catch (Exception ex) {
            throw new IOException(ex.getMessage());
        }
    }
    
    public static String getMapScript(String address, Integer zoom,
            double latitude, double longitude, String key) {
        String mapRep =
                "    <script type='text/javascript'>\n"
                + "    //<![CDATA[\n"
                + "    function loadMap() {\n"
                + "      if (GBrowserIsCompatible()) {\n"
                + "        var map = new GMap2(document.getElementById('map'));\n"
                + "        var point = new GLatLng(" + latitude + ", " + longitude + ");\n"
                + "        map.addControl(new GSmallMapControl());\n"
                + "        map.addControl(new GMapTypeControl());\n"
                + "        map.setCenter(point, " + zoom + ");\n"
                + "        var marker = createMarker(point);\n"
                + "        map.addOverlay(marker);\n"
                + "        marker.openInfoWindowHtml(\"" + address + "\");\n"
                + "      }\n"
                + "    }\n"
                + "    function createMarker(point) {\n"
                + "      var marker = new GMarker(point);\n"
                + "      GEvent.addListener(marker, \"click\", function() {\n"
                + "         marker.openInfoWindowHtml(\"" + address + "\");\n"
                + "      });\n"
                + "      return marker;\n"
                + "    }\n"
                + "    function loadScript() {\n"
                + "      var script = document.createElement(\"script\");\n"
                + "      script.setAttribute(\"src\", \"http://maps.google.com/maps?file=api&v=2.x&key=" + key + "&async=2&callback=loadMap\");\n"
                + "      script.setAttribute(\"type\", \"text/javascript\");\n"
                + "      document.documentElement.firstChild.appendChild(script);\n"
                + "    }\n"
                + "    //]]>\n"
                + "    </script>\n";
        return mapRep;
    }
    
    public static class GeoCoder {
        
        public final static String GEOCODE_URL = "http://maps.google.com/maps/geo";
        private String location;
        private String key;

        /** Creates a new instance of GeoCoder */
        public GeoCoder(String location, String key) {
            this.location = location;
            this.key = key;
        }

        /**
         *
         * @return geocode
         */
        public GeoCode invoke() throws IOException {
            String[][] params = new String[][]{
                {"q", location},
                {"output", "xml"},
                {"key", key}
            };
            RestConnection cl = new RestConnection(GEOCODE_URL, params);
            RestResponse response = cl.get();
            String codeStr = response.getDataAsString();
            return new GeoCode(codeStr);
        }
    }
    
    public static class GeoCode {
        
        private double longitude;
        private double latitude;

        /** Creates a new instance of GeoCode */
        public GeoCode(String xmlStr) {
            int ts = xmlStr.indexOf("<coordinates>");
            int te = xmlStr.indexOf("</coordinates>");
            String codeStr = "";
            if (ts != -1 && te != -1) {
                codeStr = xmlStr.substring(ts + 13, te);
            }
            String[] codes = codeStr.split(",");
            if (codes.length > 1) {
                this.longitude = Double.parseDouble(codes[0]);
                this.latitude = Double.parseDouble(codes[1]);
            }
        }

        /**
         *
         * @return longitude
         */
        public double getLongitude() {
            return this.longitude;
        }

        /**
         *
         * @return latitude
         */
        public double getLatitude() {
            return this.latitude;
        }
    }    
}
