package com.maodr.system.role.dao;

import com.maodr.framework.base.dao.BaseDao;
import com.maodr.system.role.model.RolePO;
import com.maodr.system.role.vo.RoleVO;

/**
 * 
 *  角色Dao接口
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public interface RoleDao extends BaseDao<RolePO, String> {

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
    public String saveOrUpdateRole(RoleVO roleVO);


}
