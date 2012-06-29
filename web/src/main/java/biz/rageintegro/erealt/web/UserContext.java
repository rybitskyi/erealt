package biz.rageintegro.erealt.web;

import java.io.Serializable;

/**
 * User: yury.ribitsky
 * Date: 8/5/11
 */
public class UserContext implements Serializable{
    private String selectedMainMenu;

    public String getSelectedMainMenu() {
        return selectedMainMenu;
    }

    public void setSelectedMainMenu(String selectedMainMenu) {
        this.selectedMainMenu = selectedMainMenu;
    }
}
