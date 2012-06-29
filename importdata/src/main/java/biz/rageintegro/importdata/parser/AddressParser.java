package biz.rageintegro.importdata.parser;

import biz.rageintegro.erealt.domain.stub.Street;

public interface AddressParser {
    public Street parseStreet(String description);
}
