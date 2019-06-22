package com.louis.mango.admin.service.impl;

import com.louis.mango.admin.dao.SysUserMapper;
import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.model.SysUserRole;
import com.louis.mango.admin.service.SysUserService;
import com.louis.mango.core.page.MybatisPageHelper;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Set;

@Service
public class SysUserServiceImpl  implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;


	@Override
	public int save(SysUser record) {
		return 0;
	}

	@Override
	public int delete(SysUser record) {
		return 0;
	}

	@Override
	public int delete(List<SysUser> records) {
		return 0;
	}

	@Override
	public SysUser findById(Long id) {
		return null;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, sysUserMapper);
	}

	@Override
	public SysUser findByName(String username) {
		return null;
	}

	@Override
	public Set<String> findPermissions(String userName) {
		return null;
	}

	@Override
	public List<SysUserRole> findUserRoles(Long userId) {
		return null;
	}

	@Override
	public File createUserExcelFile(PageRequest pageRequest) {
		return null;
	}
}
