package com.maodr.system.role.service;

import java.util.List;

import com.maodr.system.role.vo.RoleVO;

/**
 * 
 *  角色Service接口
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public interface RoleService {

    /**
     * 
     *  保存Role
     *  @param roleVO
     *  @return
     *  @author Administrator
     *  @created 2013年12月31日 上午4:39:37
     *  @lastModified       
     *  @history
     */
    public String saveRole(RoleVO roleVO);
    
    /**
     * 
     *  显示角色
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:44:53
     *  @lastModified       
     *  @history
     */
    public List<RoleVO> listRoles();

    /**
     * 
     *  获取角色信息
     *  @return
     *  @author Administrator
     *  @created 2014年1月2日 上午7:21:06
     *  @lastModified       
     *  @history
     */
    public RoleVO getRole(String id);


}
