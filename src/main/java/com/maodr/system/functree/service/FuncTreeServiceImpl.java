package com.maodr.system.functree.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.maodr.system.functree.dao.FuncTreeDao;
import com.maodr.system.functree.vo.FuncTreeVO;
import com.maodr.system.model.FuncTreePO;

/**
 * 
 *  功能树Service接口实现
 *  @author Administrator
 *  @created 2013年12月30日 上午6:12:52
 *  @lastModified       
 *  @history
 */
public class FuncTreeServiceImpl implements FuncTreeService {

    private FuncTreeDao funcTreeDao;

    public void setFuncTreeDao(FuncTreeDao funcTreeDao) {
        this.funcTreeDao = funcTreeDao;
    }

    public String saveFuncTree(FuncTreeVO funcTreeVO) {
        return funcTreeDao.saveOrUpdateFuncTree(funcTreeVO);
    }

    /**
     * 
     *  显示功能树
     *  @return
     *  @author Administrator
     *  @created 2014年1月1日 下午3:44:53
     *  @lastModified       
     *  @history
     */
    public List<FuncTreeVO> listFuncTrees() {
        List<FuncTreePO> poList = funcTreeDao.getAllDistinct();
        List<FuncTreeVO> voList = new ArrayList();
        FuncTreeVO funcTreeVO;
        for (FuncTreePO funcTreePO : poList) {
            funcTreeVO = new FuncTreeVO();
            BeanUtils.copyProperties(funcTreePO, funcTreeVO);
            voList.add(funcTreeVO);
        }
        return voList;
    }

    /**
     * 
     *  获取功能树信息
     *  @return
     *  @author Administrator
     *  @created 2014年1月2日 上午7:21:06
     *  @lastModified       
     *  @history
     */
    public FuncTreeVO getFuncTree(String id) {
        FuncTreePO funcTreePO = funcTreeDao.get(id);
        FuncTreeVO funcTreeVO = new FuncTreeVO();
        BeanUtils.copyProperties(funcTreePO, funcTreeVO);
        return funcTreeVO;

    }

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
    public List<FuncTreeVO> listSubFuncTrees(String treeNodeID) {
        return funcTreeDao.listSubFuncTrees(treeNodeID);

    }

}
