package biz.rageintegro.importdata.aviso.service;

import biz.rageintegro.erealt.domain.stub.AbstractObject;

public interface AbstractObjectEntity<T extends AbstractObject> {
    T create();
    void persist(T abstractObject);
    void merge(T abstractObject);
}
