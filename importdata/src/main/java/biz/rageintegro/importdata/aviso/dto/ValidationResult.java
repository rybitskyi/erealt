package biz.rageintegro.importdata.aviso.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class ValidationResult implements Serializable {
    private static final long serialVersionUID = -4512978906112563717L;

    @XmlElementWrapper(name = "warnings")
    @XmlElement(name = "warning")
    private List<ValidationResultItem> warnings;
    @XmlElementWrapper(name = "errors")
    @XmlElement(name = "error")
    private List<ValidationResultItem> errors;
    @XmlElementWrapper(name = "fatalErrors")
    @XmlElement(name = "fatalError")
    private List<ValidationResultItem> fatalErrors;

    public List<ValidationResultItem> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<ValidationResultItem> warnings) {
        this.warnings = warnings;
    }

    public List<ValidationResultItem> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationResultItem> errors) {
        this.errors = errors;
    }

    public List<ValidationResultItem> getFatalErrors() {
        return fatalErrors;
    }

    public void setFatalErrors(List<ValidationResultItem> fatalErrors) {
        this.fatalErrors = fatalErrors;
    }

    public boolean isEmpty() {
        return (warnings == null || warnings.size() == 0) &&
                (errors == null || errors.size() == 0) &&
                (fatalErrors == null || fatalErrors.size() == 0);
    }
}
