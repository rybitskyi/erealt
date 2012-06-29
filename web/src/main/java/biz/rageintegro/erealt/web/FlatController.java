package biz.rageintegro.erealt.web;

import biz.rageintegro.erealt.domain.*;
import biz.rageintegro.erealt.reference.RoomCountType;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * User: yury.ribitsky
 * Date: 8/8/11
 */
abstract public class FlatController<T extends Flat> extends AbstractObjectContoller<T> {

    protected FlatController(OperationType operationType) {
        super(operationType);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringNullableEditor stringNullableEditor = new StringNullableEditor();
        binder.registerCustomEditor(RoomCountType.class, stringNullableEditor);
    }

    protected String create(@Valid T flat, BindingResult result, ModelMap modelMap) {
        modelMap.addAttribute("roomcounttype_enum", RoomCountType.class.getEnumConstants());
        return super.create(flat, result, modelMap);
    }

    public String createForm(T flat, ModelMap modelMap) {
        modelMap.addAttribute("roomcounttype_enum", RoomCountType.class.getEnumConstants());
        return super.createForm(flat, modelMap);
    }

    protected void processList(Class clazz, Integer page, ModelMap modelMap) {
        modelMap.addAttribute("roomcounttype_enum", RoomCountType.class.getEnumConstants());
        super.processList(clazz, page, modelMap);
    }

    public String update(T object, BindingResult result, ModelMap modelMap) {
        modelMap.addAttribute("roomcounttype_enum", RoomCountType.class.getEnumConstants());
        return super.update(object, result, modelMap);
    }
}
