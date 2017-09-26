package com.java.tag;

import com.java.dao.DictDao;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by xxjs-gd-llf
 * DATETIME:2017/9/25 22:42
 * Description:
 */

@Setter
@Getter
public class OptionsTag  extends BodyTagSupport {

    // collection只是传递一个标识，具体下拉值内容是从数据库取还是从请求中得到为不同具体实现
    private String dicttype;
    private DictDao dictDao = new DictDao();

    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            StringBuffer results = new StringBuffer("");
            Map optionMap = dictDao.getDictByDictTypeId(dicttype);
            Iterator<Map.Entry<String, String>> it = optionMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                results.append("<option value=\"");
                results.append(entry.getKey());
                results.append(" \">");
                results.append(entry.getValue());
                results.append("</option>");

            }
            System.out.println("results: " + results);
            pageContext.getOut().write(results.toString());
        } catch (IOException ex) {
            throw new JspTagException("错误");
        }
        return EVAL_PAGE;
    }
}
