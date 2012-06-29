package biz.rageintegro.erealt.web;

import biz.rageintegro.erealt.domain.House;
import biz.rageintegro.erealt.domain.OperationType;
import biz.rageintegro.erealt.reference.RoomCountType;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.validation.Valid;

/**
 * User: yury.ribitsky
 * Date: 8/8/11
 */
abstract public class HouseController<T extends House> extends AbstractObjectContoller<T> {

    protected HouseController(OperationType operationType) {
        super(operationType);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    protected String create(@Valid T flat, BindingResult result, ModelMap modelMap) {
        return super.create(flat, result, modelMap);
    }

    public String createForm(T flat, ModelMap modelMap) {
        return super.createForm(flat, modelMap);
    }

    protected void processList(Class clazz, Integer page, ModelMap modelMap) {
        super.processList(clazz, page, modelMap);
    }

    public String update(T object, BindingResult result, ModelMap modelMap) {
        return super.update(object, result, modelMap);
    }
}
