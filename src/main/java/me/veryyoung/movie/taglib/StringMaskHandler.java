package me.veryyoung.movie.taglib;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by veryyoung on 2015/4/27.
 */
public class StringMaskHandler extends SimpleTagSupport {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());


    String beforeMask;

    int start = 1;

    int length = 0;

    /**
     * 字符串打码。例如：veryyoung -> v*******g
     *
     * @throws javax.servlet.jsp.JspException
     * @throws java.io.IOException
     */
    @Override
    public void doTag() throws JspException, IOException {
        if (StringUtils.isBlank(beforeMask)) {
            logger.warn("Invalid beforeMask String is null.");
            return;
        }
        JspWriter writer = getJspContext().getOut();
        if (length <= 0) {  //没有设置length或默认length
            length = beforeMask.length() == 2 ? 1 : beforeMask.length() - 2;
        }
        if (length > beforeMask.length() - 1) {
            length = beforeMask.length() - 1;
        }
        writer.write(mask(beforeMask, start, length));
    }

    public void setBeforeMask(String beforeMask) {
        this.beforeMask = beforeMask.trim();
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setLength(int length) {
        this.length = length;
    }

    private static char[] mask(String content, int offset, int length) {
        char[] chars = content.toCharArray();
        for (int i = offset; i < offset + length; i++) {
            chars[i] = '*';
        }
        return chars;
    }

}
