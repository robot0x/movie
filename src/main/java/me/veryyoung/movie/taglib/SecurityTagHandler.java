package me.veryyoung.movie.taglib;

import lombok.Setter;
import me.veryyoung.movie.entity.User;
import me.veryyoung.movie.utils.ContextUtils;
import me.veryyoung.movie.utils.SessionUtils;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 作为辅助JSP控制输出的Tag.
 * <p/>
 * 根据 LoginRequired 和 是否为本人和管理员 来进行限制
 * <p/>
 * Created by veryyoung on 2015/5/14.
 */
public class SecurityTagHandler extends SimpleTagSupport {


    @Setter
    private String userId;

    @Override
    public void doTag() throws JspException, IOException {
        SessionUtils sessionUtils = getSessionUtils();
        if (null == sessionUtils) {
            return;
        }
        User currentUser = sessionUtils.getUser();
        if (null == currentUser) {
            return;
        }
        if (currentUser.isAdmin() || currentUser.getId().equals(userId)) {
            getJspBody().invoke(null);
        }
        return;
    }


    /**
     * 获取当前的SessionUtils
     *
     * @return
     */
    private SessionUtils getSessionUtils() {
        JspContext jc = getJspContext();
        Object sessionUtils = jc.getAttribute(ContextUtils.SESSIONUTILS, PageContext.SESSION_SCOPE);
        if (sessionUtils != null && sessionUtils instanceof SessionUtils) {
            return (SessionUtils) sessionUtils;
        }
        return null;
    }
}
