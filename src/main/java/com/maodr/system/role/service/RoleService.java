package com.maodr.system.role.service;

import java.util.List;

import com.maodr.system.role.vo.RoleFuncTreeVO;
import com.maodr.system.role.vo.RoleVO;
import com.maodr.system.user.vo.UserVO;

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
    public List<UserVO> listUserOfRole(String roleID);

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
    public RoleFuncTreeVO getRoleFuncTree(String roleID, String funcTreeID);

    /**
     * 
     *  保存角色在该功能上的权限
     *  @param roleFuncTreeVO
     *  @author Administrator
     *  @created 2014年1月23日 上午5:54:30
     *  @lastModified       
     *  @history
     */
    public RoleFuncTreeVO saveRoleFuncTree(RoleFuncTreeVO roleFuncTreeVO);

}
