package biz.rageintegro.erealt.domain;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;

public class FileDataWrapper implements Serializable{
    private CommonsMultipartFile fileData;
    private byte[] contents;
    private String fileExtension;

    public FileDataWrapper() {
    }

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}
