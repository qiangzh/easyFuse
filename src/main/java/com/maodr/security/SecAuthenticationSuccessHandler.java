package com.maodr.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.navigator.menu.MenuRepository;
import net.sf.navigator.util.LoadableResourceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.maodr.framework.Constants;
import com.maodr.framework.util.ApplicationContextProvider;
import com.maodr.system.functree.vo.FuncTreeVO;
import com.maodr.system.user.service.UserService;

/**
 * 
 *  认证成功_设置菜单
 *  @author Administrator
 *  @created 2014年1月27日 下午7:44:17
 *  @lastModified       
 *  @history
 */
public class SecAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static Log log = LogFactory.getLog(SecAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        List mainMenuList = this.getMenu(user);
        request.getSession().setAttribute(Constants.MENU_REPOSITORY_KEY, mainMenuList); 
        // 跳转到主页
        response.sendRedirect("home");
    }

    /**
     * 
     *  设置菜单
     *  @param user
     *  @author Administrator
     *  @created 2014年2月24日 下午10:54:58
     *  @lastModified       
     *  @history
     */
    private List getMenu(User user) {
        ServletContext ctx = ApplicationContextProvider.getServletContext();

        MenuRepository defaultRepository = (MenuRepository) ctx.getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
        MenuRepository repository = new MenuRepository();
        repository.setDisplayers(defaultRepository.getDisplayers());

        UserService userService = (UserService) ApplicationContextProvider.getBean("userService");
        List<FuncTreeVO> funcTreeList = userService.listUserFuncTrees(user.getUsername());

        Map<String, List> menuMap = new HashMap<String, List>();
        for (int i = 0, n = funcTreeList.size(); i < n; i++) {
            FuncTreeVO funcTreeVO = funcTreeList.get(i);
            String parentID = funcTreeVO.getParentID();

            List childList = null;
            if (menuMap.containsKey(parentID)) {
                childList = menuMap.get(parentID);
            }
            else {
                childList = new ArrayList();
                menuMap.put(parentID, childList);
            }
            childList.add(funcTreeVO);
        }

        for (int i = 0, n = funcTreeList.size(); i < n; i++) {
            FuncTreeVO funcTreeVO = funcTreeList.get(i);
            String funcTreeID = funcTreeVO.getId();

            List childList = null;
            if (menuMap.containsKey(funcTreeID)) {
                childList = menuMap.get(funcTreeID);
                funcTreeVO.setHasChild(true);
                funcTreeVO.setChildren(childList);
            }
        }
        List mainMenuList = menuMap.get("0");
        return mainMenuList;   
    }

    private void setDefaultMenu() {
        String menuConfig = "/WEB-INF/menu-config.xml";
        ServletContext ctx = ApplicationContextProvider.getServletContext();

        if (log.isDebugEnabled()) {
            log.debug("Starting struts-menu initialization");
        }

        MenuRepository repository = new MenuRepository();
        repository.setLoadParam(menuConfig);
        repository.setServletContext(ctx);

        try {
            repository.load();
            ctx.setAttribute(MenuRepository.MENU_REPOSITORY_KEY, repository);

            if (log.isDebugEnabled()) {
                log.debug("struts-menu initialization successfull");
            }
        }
        catch (LoadableResourceException lre) {
            log.fatal("Failure initializing struts-menu: " + lre.getMessage());
        }
    }

}