package biz.rageintegro.erealt.jsf.converter;

import biz.rageintegro.erealt.domain.AbstractObject;
import biz.rageintegro.erealt.domain.AccessUser;
import biz.rageintegro.erealt.domain.FileDataWrapper;
import biz.rageintegro.erealt.jsf.CreateBean;
import org.apache.commons.io.FilenameUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * User: yury.ribitsky
 * Date: 6/18/12
 */
public abstract class EntityFactory<T extends AbstractObject> {

    public abstract T createEntity(CreateBean createBean);

    protected void init(T entity, CreateBean createBean) {
        entity.setDistrict(createBean.getDistrict());
        entity.setRegion(createBean.getRegion());
        entity.setStreet(createBean.getStreet());
        entity.setStreetObjectNo(createBean.getStreetObjectNo());
        entity.setPrice(createBean.getPrice());
        entity.setDescription(createBean.getDescription());

        String username = null;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        }
        AccessUser user = AccessUser.findAccessUserByName(username);
        if (user == null) {
            throw new IllegalArgumentException("An user object is required");
        }
        entity.setAccessUser(user);

        if (createBean.getFile() != null && createBean.getFile().getContents() != null && createBean.getFile().getFileName() != null) {
            FileDataWrapper fileData = new FileDataWrapper();
            fileData.setContents(createBean.getFile().getContents());
            fileData.setFileExtension(FilenameUtils.getExtension(createBean.getFile().getFileName()));
            entity.setFileDataWrapper(fileData);
        }
    }
}
