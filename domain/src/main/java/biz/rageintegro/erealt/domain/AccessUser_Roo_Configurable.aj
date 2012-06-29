package biz.rageintegro.erealt.domain;

import org.springframework.beans.factory.annotation.Configurable;

privileged aspect AccessUser_Roo_Configurable {
    
    declare @type: AccessUser: @Configurable;
    
}
