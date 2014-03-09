package com.maodr.system.role.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.maodr.framework.exception.BusinessException;
import com.maodr.system.model.RolePO;
import com.maodr.system.role.dao.RoleDao;
import com.maodr.system.role.vo.RoleFuncTreeVO;
import com.maodr.system.role.vo.RoleVO;
import com.maodr.system.user.vo.UserVO;

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

    /**
     *  添加|修改角色
     *  @param roleVO
     *  @return
     *  @author Administrator
     *  @created 2014年3月5日 上午6:58:24
     *  @lastModified      
     *  @history
     */
    public String saveRole(RoleVO roleVO) {
        // 校验机构编码重复
        if (roleDao.checkRoleCodeExist(roleVO)) {
            throw new BusinessException("编码为{0}的角色已存在", new String[] { roleVO.getRoleCode() });
        }

        // 校验机构名称重复    
        if (roleDao.checkRoleNameExist(roleVO)) {
            throw new BusinessException("名称为{0}的角色已存在", new String[] { roleVO.getRoleName() });
        }
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
        List<RoleVO> voList = new ArrayList<RoleVO>();
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

    /**
     * 
     *  查看已分配用户
     *  @param roleID
     *  @return
     *  @author Administrator
     *  @created 2014年1月19日 下午2:52:40
     *  @lastModified       
     *  @history
     */
    public List<UserVO> listUserOfRole(String roleID) {
        return roleDao.listUserOfRole(roleID);
    }

    /**
     * 
     *  获取角色在该功能上的权限
     *  @param roleID
     *  @param funcTreeID
     *  @return
     *  @author Administrator
     *  @created 2014年1月23日 上午5:38:41
     *  @lastModified       
     *  @history
     */
    public RoleFuncTreeVO getRoleFuncTree(String roleID, String funcTreeID) {
        return roleDao.getRoleFuncTree(roleID, funcTreeID);
    }

    /**
     * 
     *  保存角色在该功能上的权限
     *  @param roleFuncTreeVO
     *  @author Administrator
     *  @created 2014年1月23日 上午5:54:30
     *  @lastModified       
     *  @history
     */
    public RoleFuncTreeVO saveRoleFuncTree(RoleFuncTreeVO roleFuncTreeVO) {
        return roleDao.saveRoleFuncTree(roleFuncTreeVO);
    }

    /**
     * 
     *  删除角色
     *  @param id
     *  @author Administrator
     *  @created 2014年3月5日 上午6:42:59
     *  @lastModified       
     *  @history
     */
    public void deleteRole(String id) {
        RoleVO roleVO = this.getRole(id);
        // 校验角色下是否存在用户
        if (roleDao.checkRoleHasUser(roleVO)) {
            throw new BusinessException("角色{0}下存在用户，不能删除", roleVO.getRoleName());
        }

        roleDao.deleteRole(id);
    }

}
