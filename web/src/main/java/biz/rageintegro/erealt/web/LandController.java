package biz.rageintegro.erealt.web;

import biz.rageintegro.erealt.domain.Land;
import biz.rageintegro.erealt.domain.OperationType;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.validation.Valid;

/**
 * User: yury.ribitsky
 * Date: 8/8/11
 */
abstract public class LandController<T extends Land> extends AbstractObjectContoller<T> {

    protected LandController(OperationType operationType) {
        super(operationType);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    protected String create(@Valid T land, BindingResult result, ModelMap modelMap) {
        return super.create(land, result, modelMap);
    }

    public String createForm(T land, ModelMap modelMap) {
        return super.createForm(land, modelMap);
    }

    protected void processList(Class clazz, Integer page, ModelMap modelMap) {
        super.processList(clazz, page, modelMap);
    }

    public String update(T object, BindingResult result, ModelMap modelMap) {
        return super.update(object, result, modelMap);
    }
}
