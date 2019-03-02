package top.afanee.blog.exception;

import java.io.IOException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

//实现freemarker异常接口
public class FreemarkerExceptionHandler implements TemplateExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(FreemarkerExceptionHandler.class);

	public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
		logger.error("[FreemarkerExceptionHandler][Freemarker Error: " + te.getMessage() + "]");
		try {
			out.write("<meta http-equiv=\"Refresh\" content=\"0;url=/rece-web/page/error/404.html\">");
			//throw new ViewException(ViewException.BASE_FREEMARKER_EXCEPTION,"Freemarker 解析错误");
		} catch (IOException e) {
			throw new TemplateException("Failed to print error message. Cause: " + e, env);
		}
	}

}
