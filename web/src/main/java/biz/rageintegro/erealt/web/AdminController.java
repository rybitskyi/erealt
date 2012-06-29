package biz.rageintegro.erealt.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import biz.rageintegro.erealt.domain.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.InputStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

@RequestMapping("/admin/**")
@Controller
public class AdminController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        return "admin/index";
    }

/*
    @RequestMapping(value = "/admin/importaviso", method = RequestMethod.GET)
    public String importaviso(ModelMap modelMap) {
        int serialNo = CreationDateGetter.getCurrentAvisoNo();
        System.out.println("serialNo=" + serialNo);
        String result;
        try {
            new ImportAvisoData().run(RgConfiguration.getConfiguration());
            result = "OK";
        } catch (Exception e) {
            result = e.toString();
            e.printStackTrace();
            modelMap.put("exception", e);
        }
        modelMap.put("result", result);
        modelMap.put("serialNo", serialNo);
        return "admin/importavisoresult";
    }
*/

    @RequestMapping(value = "/admin/updateaddress", method = RequestMethod.GET)
    public String updateaddress(ModelMap modelMap) {
        List<District> districts = District.findAllDistricts();
        for (District d : districts) {
            d.setCaption2(TranslitConverter.translit(d.getCaption()));
            d.merge();
        }
        List<Region> regions = Region.findAllRegions();
        for (Region r : regions) {
            r.setCaption2(TranslitConverter.translit(r.getCaption()));
            r.merge();
        }
        modelMap.put("result", "OK");
        return "admin/importavisoresult";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String updateUser(ModelMap modelMap) {
        List<AccessUser> l = AccessUser.findAllAccessUsers();
        for (AccessUser user : l) {
            if (user.getCaption() == null || user.getCaption().length() == 0) {
                user.setCaption(user.getNameFromEmail());
            }
            user.merge();
        }
        modelMap.put("result", "OK");
        return "admin/importavisoresult";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String importHumanName(ModelMap modelMap) {
        try {
            InputStream in = getClass().getResourceAsStream("/human-name.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(in);
            doc.getDocumentElement().normalize();
            NodeList nodeLst = doc.getElementsByTagName("row");

            for (int s = 0; s < nodeLst.getLength(); s++) {

                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element fstElmnt = (Element) fstNode;
                    NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("caption");
                    Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                    NodeList fstNm = fstNmElmnt.getChildNodes();
                    String caption = ((Node) fstNm.item(0)).getNodeValue();
                    NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("male");
                    Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
                    NodeList lstNm = lstNmElmnt.getChildNodes();
                    String male = ((Node) lstNm.item(0)).getNodeValue();

                    HumanName humanName = new HumanName();
                    humanName.setCaption(caption);
                    humanName.setMale(male.toLowerCase().equals("true"));
                    humanName.merge();
                }

            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        modelMap.put("result", "OK");
        return "admin/importavisoresult";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void reboot(String execPath) {
        try {
            String osName = System.getProperty("os.name");
            String[] cmd = new String[3];
            if (osName.equals("Windows NT")) {
                cmd[0] = "cmd.exe";
                cmd[1] = "/C";
                cmd[2] = execPath;
            } else if (osName.equals("Windows 95")) {
                cmd[0] = "command.com";
                cmd[1] = "/C";
                cmd[2] = execPath;
            }

            Runtime rt = Runtime.getRuntime();
            System.out.println("Execing " + cmd[0] + " " + cmd[1]
                    + " " + cmd[2]);
            Process proc = rt.exec(cmd);
            // any error message?
            StreamGobbler errorGobbler = new
                    StreamGobbler(proc.getErrorStream(), "ERROR");

            // any output?
            StreamGobbler outputGobbler = new
                    StreamGobbler(proc.getInputStream(), "OUTPUT");

            // kick them off
            errorGobbler.start();
            outputGobbler.start();

            // any error???
            int exitVal = proc.waitFor();
            System.out.println("ExitValue: " + exitVal);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    class StreamGobbler extends Thread {
        InputStream is;
        String type;

        StreamGobbler(InputStream is, String type) {
            this.is = is;
            this.type = type;
        }

        public void run() {
            try {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null)
                    System.out.println(type + ">" + line);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
