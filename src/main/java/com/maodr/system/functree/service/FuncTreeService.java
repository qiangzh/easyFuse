package com.maodr.system.functree.service;

import java.util.List;

import com.maodr.system.functree.vo.FuncTreeVO;

/**
 * 
 *  功能树Service接口
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public interface FuncTreeService {

    /**
     * 
     *  保存FuncTree
     *  @param funcTreeVO
     *  @return
     *  @author Administrator
     *  @created 2013年12月31日 上午4:39:37
     *  @lastModified       
     *  @history
     */
    public String saveFuncTree(FuncTreeVO funcTreeVO);

    /**
     * 
     *  显示功能树
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:44:53
     *  @lastModified       
     *  @history
     */
    public List<FuncTreeVO> listFuncTrees();

    /**
     * 
     *  获取功能树信息
     *  @return
     *  @author Administrator
     *  @created 2014年1月2日 上午7:21:06
     *  @lastModified       
     *  @history
     */
    public FuncTreeVO getFuncTree(String id);

    /**
     * 
     *  获取子功能树
     *  @param treeNodeID
     *  @return
     *  @author Administrator
     *  @created 2014年1月6日 上午7:56:17
     *  @lastModified       
     *  @history
     */
    public List<FuncTreeVO> listSubFuncTrees(String treeNodeID);
    
    /**
     * 
     *  删除功能树
     *  @param id
     *  @author Administrator
     *  @created 2014年3月8日 下午1:05:57
     *  @lastModified       
     *  @history
     */
    public void deleteFuncTree(String id);

}
