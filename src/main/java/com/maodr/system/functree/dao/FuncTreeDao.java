package com.maodr.system.functree.dao;

import java.util.List;

import com.maodr.framework.base.dao.BaseDao;
import com.maodr.system.functree.model.FuncTreePO;
import com.maodr.system.functree.vo.FuncTreeVO;

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

}
