package biz.rageintegro.erealt.domain.ws;

import biz.rageintegro.erealt.domain.Street;

import java.util.List;

public class StreetListResponse {
    private List<Street> list;

    public List<Street> getList() {
        return list;
    }

    public void setList(List<Street> list) {
        this.list = list;
    }
}
