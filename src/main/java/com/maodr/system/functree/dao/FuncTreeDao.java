package com.maodr.system.functree.dao;

import java.util.List;

import com.maodr.framework.base.dao.BaseDao;
import com.maodr.system.functree.vo.FuncTreeVO;
import com.maodr.system.model.FuncTreePO;

/**
 * 
 *  功能树Dao接口
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public interface FuncTreeDao extends BaseDao<FuncTreePO, String> {

    /**
     * 
     *  添加功能树
     *  @param funcTreeVO
     *  @return
     *  @author Administrator
     *  @created 2014年1月2日 下午7:55:14
     *  @lastModified       
     *  @history
     */
    public String saveOrUpdateFuncTree(FuncTreeVO funcTreeVO);

    /**
     * 
     *  获取子节点
     *  @param treeNodeID
     *  @return
     *  @author Administrator
     *  @created 2014年1月7日 上午6:53:22
     *  @lastModified       
     *  @history
     */
    public List<FuncTreeVO> listSubFuncTrees(String treeNodeID);

    /**
     * 
     *  校验功能编码重复
     *  @return
     *  @author Administrator
     *  @created 2014年3月5日 上午4:44:11
     *  @lastModified       
     *  @history
     */
    public boolean checkFuncTreeCodeExist(FuncTreeVO funcTreeVO);

    /**
     * 
     *  校验功能名称重复
     *  @return
     *  @author Administrator
     *  @created 2014年3月5日 上午4:44:22
     *  @lastModified       
     *  @history
     */
    public boolean checkFuncTreeNameExist(FuncTreeVO funcTreeVO);

    /**
     * 
     *  生成Sort字段
     *  @param funcTreeVO
     *  @return
     *  @author Administrator
     *  @created 2014年3月8日 下午12:40:42
     *  @lastModified       
     *  @history
     */
    public String generateFuncTreeSort(FuncTreeVO funcTreeVO);

    /**
     * 
     *  校验功能下是否存在功能(是否为叶子节点)
     *  @param funcTreeVO
     *  @return
     *  @author Administrator
     *  @created 2014年3月10日 上午6:13:03
     *  @lastModified       
     *  @history
     */
    public boolean checkFuncTreeHasChild(FuncTreeVO funcTreeVO);

    /**
     * 
     *  删除角色功能树关联关系
     *  @param funcTreeID
     *  @author Administrator
     *  @created 2014年3月10日 上午6:26:36
     *  @lastModified       
     *  @history
     */
    public void deleteRoleFuncTree(String funcTreeID);

}
