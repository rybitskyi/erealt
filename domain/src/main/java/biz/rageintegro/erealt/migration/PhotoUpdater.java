package biz.rageintegro.erealt.migration;

import biz.rageintegro.erealt.domain.PhotoManager;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//@Service
public class PhotoUpdater implements InitializingBean {

    @Autowired
    private PhotoManager photoManager;

    public PhotoUpdater() {
        System.out.println("PhotoUpdater started " + new Date());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("photoManager.getBaseDir= " + photoManager.getBaseDir());
        Collection<File> files = photoManager.getAllOriginalPhotos();
        System.out.println("count Files = " + files.size());
        for (File f : files) {
            File f2 = f;
            List<File> fileList = new ArrayList<File>();
            for (int i = 0; i < 4; i++) {
                f2 = f2.getParentFile();
                fileList.add(0, f2);
            }
            StringBuilder sb = new StringBuilder();
            for (File f3 : fileList) {
                sb.append(f3.getName());
            }
            String idStr = sb.toString().replaceFirst("0+", "");
            long id = Long.parseLong(idStr);
            String ext = FilenameUtils.getExtension(f.getName());
            try {
                photoManager.createPhotoFile(id, ext);
                photoManager.createThumbnail(id, ext);
            } catch (Exception e) {
                throw new RuntimeException("Can't process id = " + id, e);
            }
        }
        System.out.println("PhotoUpdater ended " + new Date());
    }
}
