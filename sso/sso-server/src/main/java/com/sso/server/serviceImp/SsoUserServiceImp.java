package com.sso.server.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sso.interfaces.model.SsoUserVo;
import com.sso.interfaces.service.SsoUserService;
import com.sso.server.dao.SsoUserDao;
import com.sso.server.entity.SsoUser;

@Service("ssoUserService")
@Transactional(rollbackFor=Exception.class)
public class SsoUserServiceImp implements SsoUserService {
	
	@Autowired
	private SsoUserDao dao;

	@Override
	public SsoUserVo getSsoUserByName(String username) {
		// TODO Auto-generated method stub
		List<SsoUser> list = this.dao.findAll();
		if(list!=null && list.size()>0){
			for(SsoUser user :list){
				if(user.getAppkey().equals(username)){
					SsoUserVo vo=new SsoUserVo();
					vo.setId(user.getId());
					vo.setUsername(user.getAppkey());
					vo.setPassword(user.getPassword());
					return vo;
				}
			}
		}
		return null;
	}

}
