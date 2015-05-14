package me.veryyoung.movie.utils;

import me.veryyoung.movie.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by veryyoung on 2015/4/23.
 */
public class ContextUtils {


    /**
     * the attribute name of SessionUtils in session
     */
    public static final String SESSIONUTILS = "SessionUtils";


    /**
     * 获取当前登录的用户
     *
     * @param request
     * @return 没有有效用户则返回null
     */
    public static User getUser(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        } else {
            return getSessionUtils(session).getUser();
        }
    }

    /**
     * 从HttpServletRequest中获取当前登录的用户Id
     *
     * @param request
     * @return 没有有效登录用户则返回null
     */
    public static String getUserId(final HttpServletRequest request) {
        User user = getUser(request);
        return user == null ? null : user.getId();
    }

    /**
     * 获取SessionUtils，不会返回null
     *
     * @param request
     * @return
     */
    public static SessionUtils getSessionUtils(final HttpServletRequest request) {
        return getSessionUtils(request.getSession());
    }

    public static SessionUtils getSessionUtils(final HttpSession session) {
        if (session.getAttribute(SESSIONUTILS) == null) {
            SessionUtils sessionUtils = new SessionUtils();
            session.setAttribute(SESSIONUTILS, sessionUtils);
            return sessionUtils;
        } else {
            return (SessionUtils) session.getAttribute(SESSIONUTILS);
        }
    }

    public static void storeInSession(final HttpServletRequest request,
                                      final String name,
                                      final Object value) {
        request.getSession().setAttribute(name, value);
    }

    /**
     * 从Session中获取给定名字和类型的对象
     *
     * @param <T>
     * @param request
     * @param name
     * @param clazz
     * @param keep    true则在获取后继续保留改该对象，false删除之
     * @return 没有条目则返回null
     */
    public static <T> T getFromSession(final HttpServletRequest request,
                                       final String name,
                                       final Class<T> clazz,
                                       final boolean keep) {
        T result = null;
        HttpSession session = request.getSession();
        if (session.getAttribute(name) != null) {
            result = (T) session.getAttribute(name);
            if (!keep) {
                session.removeAttribute(name);
            }
        }
        return result;
    }
}
