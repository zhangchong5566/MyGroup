package com.zhc.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.zhc.sys.entity.SysUser;
import com.zhc.sys.entity.SysUserToSysRole;
import com.zhc.sys.service.base.BaseJpaService;
import com.zhc.sys.service.base.Pages;


public class SysUserServiceImpl extends BaseJpaService implements SysUserService {

	@Override
	public SysUser login(String loginName, String password) {
		
		String where = "loginName=? and password=?";
		
		SysUser sysuser = super.getSingleResult(SysUser.class, where, new Object[]{loginName,password});
		
		if(sysuser != null){
			//更新最后登录时间
			sysuser.setLastLoginTime(new Date());
			super.update(sysuser);
		}
		
		return sysuser;
	}

	@Override
	public List<SysUser> findList(SysUser sysUser, Pages pages) {
		
		StringBuffer where = new StringBuffer();
		
		List params = new ArrayList();
		
		if(sysUser!=null){
			if(StringUtils.isNotBlank(sysUser.getLoginName())){
				if(StringUtils.isNotBlank(where)){
					where.append(" and ");
				}
				where.append(" loginName=?");
				params.add(sysUser.getLoginName());
			}
			
			if(StringUtils.isNotBlank(sysUser.getTrueName())){
				if(StringUtils.isNotBlank(where)){
					where.append(" and ");
				}
				where.append(" trueName=?");
				params.add(sysUser.getTrueName());
			}
		}
		
		return super.getScrollData(SysUser.class, pages, where.toString(), params.toArray());
	}

	@Override
	public List<SysUserToSysRole> findRolesByUser(Long userId) {
		
		String jpql = "from SysUserToSysRole s where s.sysuser.id="+userId;
		
		return super.queryByJPQL(jpql);
	}

	@Override
	public void updateSysUserRoles(SysUser user,
			List<SysUserToSysRole> userRoleList) {
		
		super.deleteByWhere(SysUserToSysRole.class, "sysuser.id="+user.getId(),null);
		
		for(SysUserToSysRole bean : userRoleList){
			super.create(bean);
		}
		
	}

	
	
}
