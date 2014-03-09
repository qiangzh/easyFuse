package com.maodr.system.functree.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.maodr.framework.exception.BusinessException;
import com.maodr.framework.util.StringUtil;
import com.maodr.system.functree.dao.FuncTreeDao;
import com.maodr.system.functree.vo.FuncTreeVO;
import com.maodr.system.model.FuncTreePO;
import com.maodr.system.org.vo.OrgVO;

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
        // 校验功能树编码重复
        if (funcTreeDao.checkFuncTreeCodeExist(funcTreeVO)) {
            throw new BusinessException("编码为{0}的功能已存在", new String[] { funcTreeVO.getCode() });
        }

        // 校验功能树名称重复    
        if (funcTreeDao.checkFuncTreeNameExist(funcTreeVO)) {
            throw new BusinessException("名称为{0}的功能已存在", new String[] { funcTreeVO.getName() });
        }

        // 生成sort
        if (StringUtil.isEmpty(funcTreeVO.getId())) {
            funcTreeVO.setSort(funcTreeDao.generateFuncTreeSort(funcTreeVO));

        }
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

    /**
     * 
     *  删除功能树
     *  @param id
     *  @author Administrator
     *  @created 2014年3月8日 下午1:05:57
     *  @lastModified       
     *  @history
     */
    public void deleteFuncTree(String id) {
        FuncTreeVO funcTreeVO = this.getFuncTree(id);
        // 校验机构下是否存在机构
        if (funcTreeDao.checkFuncTreeHasChild(funcTreeVO)) {
            throw new BusinessException("{0}功能下存在其他功能,不能删除", new String[] { funcTreeVO.getName() });
        }
        
        // 删除角色功能树关联表
        funcTreeDao.deleteRoleFuncTree(id);
        
        // 删除功能
        funcTreeDao.remove(id);
    }

}
