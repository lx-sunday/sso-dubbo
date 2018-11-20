package com.sso.interfaces.service;

import com.sso.interfaces.model.SsoUserVo;

public interface SsoUserService {

	SsoUserVo getSsoUserByName(String name);
}
