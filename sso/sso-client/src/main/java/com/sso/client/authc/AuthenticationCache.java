package com.sso.client.authc;

import com.sso.interfaces.model.SsoUserVo;

public interface AuthenticationCache {

    void putSession(String key,SsoUserVo value);


    SsoUserVo getSession(String key);

    public void putSession(String key,Object obj,int expireTime);

    public void putSession(String key,Object obj);

    public Object getSessionBykey(String key);

    public void delSession(String key);
}
