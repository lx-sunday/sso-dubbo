package com.sso.interfaces.authz;

import java.io.Serializable;

public interface AuthenticateToken extends Serializable{

    //用户凭证
     String getPrincipal();
    //鉴权凭证
    String getCredentials();
    //来源平台
    Integer getPlatform();
    //获取域名
    String getDomain();
}
