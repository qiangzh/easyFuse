package com.maodr.system.role.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.maodr.system.role.dao.RoleDao;
import com.maodr.system.role.model.RolePO;
import com.maodr.system.role.vo.RoleVO;

/**
 * 
 *  角色Service接口实现
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public String saveRole(RoleVO roleVO) {
        return roleDao.saveOrUpdateRole(roleVO);
    }

    /**
     * 
     *  显示角色
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:44:53
     *  @lastModified       
     *  @history
     */
    public List<RoleVO> listRoles() {
        List<RolePO> poList = roleDao.getAllDistinct();
        List<RoleVO> voList = new ArrayList();
        RoleVO roleVO;
        for (RolePO rolePO : poList) {
            roleVO = new RoleVO();
            BeanUtils.copyProperties(rolePO, roleVO);
            voList.add(roleVO);
        }
        return voList;
    }

    /**
     * 
     *  获取角色信息
     *  @return
     *  @author Administrator
     *  @created 2014年1月2日 上午7:21:06
     *  @lastModified       
     *  @history
     */
    public RoleVO getRole(String id) {
        RolePO rolePO = roleDao.get(id);
        RoleVO roleVO = new RoleVO();
        BeanUtils.copyProperties(rolePO, roleVO);
        return roleVO;

    }

}
