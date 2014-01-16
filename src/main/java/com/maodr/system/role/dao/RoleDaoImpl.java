package com.maodr.system.role.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;

import com.maodr.framework.base.dao.BaseDaoImpl;
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
public class RoleDaoImpl extends BaseDaoImpl<RolePO, String> implements RoleDao {

    public RoleDaoImpl() {
        super(RolePO.class);
    }

    public RoleDaoImpl(Class<RolePO> persistentClass, SessionFactory sessionFactory) {
        super(persistentClass, sessionFactory);
    }

    /**
     * 
     *  添加角色
     *  @param roleVO
     *  @return
     *  @author Administrator
     *  @created 2014年1月2日 下午7:55:14
     *  @lastModified       
     *  @history
     */
    public String saveOrUpdateRole(RoleVO roleVO) {
        RolePO rolePO = new RolePO();
        BeanUtils.copyProperties(roleVO, rolePO);
        rolePO = super.save(rolePO);
        return rolePO.getId();
    }
 
}
