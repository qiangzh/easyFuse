package com.maodr.framework.tags;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.maodr.framework.Constants;
import com.maodr.system.functree.vo.FuncTreeVO;
import com.opensymphony.xwork2.ActionContext;

public class MenuTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private static Log log = LogFactory.getLog(MenuTag.class);

    public int doStartTag() throws JspException {
        JspWriter writer = pageContext.getOut();
        try {
            StringBuffer sb = new StringBuffer();
            List mainMenuList = (List) ActionContext.getContext().getSession().get(Constants.MENU_REPOSITORY_KEY);
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
            String contextPath = request.getContextPath();
            if (mainMenuList != null && !mainMenuList.isEmpty()) {
                for (int i = 0, n = mainMenuList.size(); i < n; i++) {
                    FuncTreeVO funcTreeVO = (FuncTreeVO) mainMenuList.get(i);

                    // 一级菜单
                    if (funcTreeVO.isHasChild()) {
                        sb.append("<li class=\"dropdown\">");
                    }
                    else {
                        sb.append("<li class=\"\">");
                    }

                    sb.append(" <a href=\"").append(contextPath).append(funcTreeVO.getUrl()).append("\"");
                    sb.append(" title=\"" + funcTreeVO.getName() + "\"");

                    if (funcTreeVO.isHasChild()) {
                        sb.append(" class=\"dropdown-toggle\" data-toggle=\"dropdown\"");
                    }
                    sb.append(" >").append(funcTreeVO.getName() + "</a>");
                    if (funcTreeVO.isHasChild()) {
                        sb.append(" <ul class=\"dropdown-menu\">");
                        List<FuncTreeVO> childrenList = funcTreeVO.getChildren();
                        if (childrenList != null && !childrenList.isEmpty()) {
                            for (int ii = 0, nn = childrenList.size(); ii < nn; ii++) {
                                FuncTreeVO subFuncTreeVO = (FuncTreeVO) childrenList.get(ii);

                                sb.append(" <li class=\"\">");
                                sb.append(" <a href=\"").append(contextPath).append(subFuncTreeVO.getUrl()).append("\"");
                                sb.append(" title=\"" + subFuncTreeVO.getName() + "\"");
                                sb.append(" >").append(subFuncTreeVO.getName() + "</a>");
                                sb.append(" </li>");
                            }

                        }

                        sb.append(" </ul>");
                    }

                    sb.append("</li>");

                }
            }
            writer.print(sb.toString());
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new JspException(e.toString());
        }
        return SKIP_BODY;
    }

    private StringBuffer getTest() {
        StringBuffer sb = new StringBuffer();

        // 一级菜单
        sb.append("<li class=\"\">");
        sb.append(" <a href=\"/easyFuse/home\" title=\"首页\" >首页</a>");
        sb.append("</li>");

        // 下拉二级菜单
        sb.append("<li class=\"dropdown\">");
        sb.append(" <a href=\"#\" title=\"人事管理\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">人事管理</a>");

        sb.append(" <ul class=\"dropdown-menu\">");

        sb.append(" <li class=\"\">");
        sb.append("  <a href=\"/easyFuse/org/listOrgs\" title=\"组织机构\" >组织机构</a>");
        sb.append(" </li>");

        // 下拉三级子菜单
        sb.append(" <li class=\"dropdown-submenu\">");
        sb.append("    <a tabindex=\"-1\" href=\"#\">More options</a>");

        sb.append("    <ul class=\"dropdown-menu\">");

        sb.append("     <li class=\"\">");
        sb.append("      <a href=\"/easyFuse/org/listOrgs\" title=\"组织机构1\" >组织机构1</a>");
        sb.append("     </li>");

        sb.append("    </ul>");
        sb.append(" </li>");

        sb.append(" </ul>");

        sb.append("</li>");
        return sb;
    }
}
