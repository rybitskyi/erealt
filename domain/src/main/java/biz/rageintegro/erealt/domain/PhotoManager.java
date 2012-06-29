package biz.rageintegro.erealt.domain;

import biz.rageintegro.erealt.helpers.ImageUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * User: rybitskyii
 */
public class PhotoManager {
    private String baseDir;
    private String photoRepoUrl;
    public static final String THUMBNAIL = "thumb";

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getPhotoRepoUrl() {
        return photoRepoUrl;
    }

    public void setPhotoRepoUrl(String photoRepoUrl) {
        this.photoRepoUrl = photoRepoUrl;
    }

    public void setPhotoExt(AbstractObject entity) {
        String ext = getPhotoExt(entity);
        if (ext.length() > 0) {
            entity.setPhotoExt(ext);
        }
    }

    private String getPhotoExt(AbstractObject entity) {
        if (entity.getFileDataWrapper() != null && entity.getFileDataWrapper().getFileData() != null) {
            return FilenameUtils.getExtension(entity.getFileDataWrapper().getFileData().getOriginalFilename());
        } else {
            return null;
        }
    }

    public String getPhotoUrl(AbstractObject entity) {
        if (entity.getPhotoExt() != null) {
            StringBuilder sb = new StringBuilder(getPhotoRepoUrl());
            sb.append(getRelativePhotoFile(entity).getPath().replace('\\', '/'));
            return sb.toString();
        } else {
            return null;
        }
    }

    public String getThumbnailPhotoUrl(AbstractObject entity) {
        if (entity.getPhotoExt() != null) {
            StringBuilder sb = new StringBuilder(getPhotoRepoUrl());
            sb.append(getRelativeThumbnailPhotoFile(entity).getPath().replace('\\', '/'));
            return sb.toString();
        } else {
            return null;
        }
    }

    @Deprecated
    public void checkExistThumbnailPhotoFile(AbstractObject entity) {
        File f = getThumbnailPhotoFile(entity);
        if (!f.exists()) {
            try {
                createThumbnail(entity, getPhotoFile(entity));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File getPhotoFile(AbstractObject entity) {
        return getPhotoFile(entity, false);
    }

    public File getOriginalPhotoFile(AbstractObject entity) {
        return getOriginalPhotoFile(entity.getId(), entity.getPhotoExt());
    }

    public File getOriginalPhotoFile(long id, String extension) {
        if (extension == null) {
            throw new RuntimeException("Image extension must be determined for id " + id);
        }
        File entityDir = new File(baseDir, getEntityDir(id).getPath());
        try {
            FileUtils.forceMkdir(entityDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new File(entityDir, "original" + "." + extension);
    }

    public File getPhotoFile(AbstractObject entity, boolean forceMkDir) {
        return getPhotoFile(entity.getId(), entity.getPhotoExt(), forceMkDir);
    }

    public File getPhotoFile(long id, String extension, boolean forceMkDir) {
        if (extension == null) {
            throw new RuntimeException("Image extension must be determined for id " + id);
        }
        File f = new File(baseDir, getRelativePhotoFile(id, extension).getPath());
        if (forceMkDir) {
            try {
                FileUtils.forceMkdir(f.getParentFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return f;
    }

    private File getRelativePhotoFile(AbstractObject entity) {
        return getRelativePhotoFile(entity.getId(), entity.getPhotoExt());
    }

    private File getRelativePhotoFile(long id, String extension) {
        if (extension == null) {
            throw new RuntimeException("Image extension must be determined for id " + id);
        }
        File entityDir = getEntityDir(id);
        return new File(entityDir, "0" + "." + extension);
    }

    private File getThumbnailPhotoFile(AbstractObject entity) {
        return getThumbnailPhotoFile(entity.getId(), entity.getPhotoExt());
    }

    private File getThumbnailPhotoFile(long id, String extension) {
        if (extension == null) {
            throw new RuntimeException("Image extension must be determined for id " + id);
        }
        return new File(baseDir, getRelativeThumbnailPhotoFile(id, extension).getPath());
    }

    private File getRelativeThumbnailPhotoFile(AbstractObject entity) {
        return getRelativeThumbnailPhotoFile(entity.getId(), entity.getPhotoExt());
    }

    private File getRelativeThumbnailPhotoFile(long id, String extension) {
        if (extension == null) {
            throw new RuntimeException("Image extension must be determined for id " + id);
        }
        File entityDir = getEntityDir(id);
        return new File(entityDir, THUMBNAIL + "." + extension);
    }

    public File createPhotoFile(AbstractObject entity) throws IOException {
        return createPhotoFile(entity.getId(), entity.getPhotoExt());
    }

    public File createPhotoFile(long id, String ext) throws IOException {
        File result = getPhotoFile(id, ext, true);
        File f = getOriginalPhotoFile(id, ext);
		if (f != null) {
		BufferedImage resizeImage = ImageUtil.fit(f, 320, 400);
		if (resizeImage != null) {
        ImageIO.write(resizeImage, ext, result);
		}
        return result;
		} else {
		return null;
		}
    }

    File getEntityDir(long id) {
        String idString = String.valueOf(id);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12 - idString.length(); i++) {
            sb.append("0");
        }
        sb.append(idString);
        File f = null;
        for (int i = 0; i < sb.toString().length(); i = i + 3) {
            String str = sb.toString().substring(i, i + 3);
            if (f == null) {
                f = new File(str);
            } else {
                f = new File(f, str);
            }
        }
        return f;
    }

    public void createThumbnail(AbstractObject entity) throws IOException {
        createThumbnail(entity, getOriginalPhotoFile(entity));
    }

    public void createThumbnail(AbstractObject entity, File sourceFile) throws IOException {
        if (entity == null) {
            return;
        }
        createThumbnail(entity.getId(), entity.getPhotoExt(), sourceFile);
    }

    public void createThumbnail(long id, String extension) throws IOException {
        File f = getPhotoFile(id, extension, false);
        if (f.exists()) {
            createThumbnail(id, extension, f);
        }
    }

    public void createThumbnail(long id, String extension, File sourceFile) throws IOException {
        if (extension == null || sourceFile == null) {
            return;
        }
        BufferedImage originalImage = ImageIO.read(sourceFile);
		if (originalImage == null) return;
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

        BufferedImage resizeImageJpg = resizeImage(originalImage, 100, 80, type);
        if (resizeImageJpg != null) {
            ImageIO.write(resizeImageJpg, extension, getThumbnailPhotoFile(id, extension));
        }
        //todo: else log about file doesn't exist
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type) {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    public Collection<File> getAllOriginalPhotos() {
        return FileUtils.listFiles(new File(getBaseDir()),
                FileFilterUtils.prefixFileFilter("original."),
                TrueFileFilter.INSTANCE);
    }
}
