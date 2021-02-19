package com.jbuild.forms.jbuildforms.tools;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.jbuild.forms.jbuildforms.exception.SystemException;



/**
 * Utility class to evaluate Spring SpEL expressions.
 * 
 * @author path
 */
public class SPELUtils {
	private static final Logger LOG = LoggerFactory.getLogger(SPELUtils.class);

	private SPELUtils() {
	}

	/**
	 * Evaluates an expression
	 * 
	 * @param ctx
	 * @param expressionString
	 * @param methodContextAsMap
	 * @return
	 */
	public static Object evaluateExpressionWithMapContext(ApplicationContext ctx, final String expressionString, final Map<String, Object> methodContextAsMap) {
		Object value = null;

		StandardEvaluationContext context = new StandardEvaluationContext();

		ConfigurableListableBeanFactory confFactory = null;
		if (ctx instanceof GenericApplicationContext) {
			confFactory = ((GenericApplicationContext) ctx).getBeanFactory();
		} else if (ctx instanceof AbstractRefreshableApplicationContext) {
			confFactory = ((AbstractRefreshableApplicationContext) ctx).getBeanFactory();
		} else {
			String errorMsg = "ApplicationContext not instance of 'GenericApplicationContext' or 'AbstractRefreshableApplicationContext' ";
			LOG.debug(errorMsg);
			throw new SystemException(errorMsg);
		}

		context.setBeanResolver(new BeanFactoryResolver(confFactory));
		context.setVariables(methodContextAsMap);

		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression(expressionString);
		value = expression.getValue(context);

		return value;
	}

	/**
	 * Returns true if the expression is empty, or evaluates the expression and returns it's result
	 * 
	 * @param ctx
	 * @param expression
	 * @param dossierId
	 * @return
	 */
	public static Boolean evaluateExpression(ApplicationContext ctx, String expression, Integer dossierId) {
		Boolean result = true;
		if (!StringUtils.isEmptyOrNull(expression)) {
			// Populate context
			Map<String, Object> methodContextAsMap = new HashMap<String, Object>();
			methodContextAsMap.put(StringUtils.DOSSIER_ID, dossierId);
			result = (Boolean) evaluateExpressionWithMapContext(ctx, expression, methodContextAsMap);
		}
		return result;
	}

	public static Boolean evaluateCheckSelected(ApplicationContext ctx, String expression, String variable, String variablename) {
		Boolean result = true;
		if (!StringUtils.isEmptyOrNull(expression)) {
			// Populate context
			Map<String, Object> methodContextAsMap = new HashMap<String, Object>();
			methodContextAsMap.put(variablename, variable);
			result = (Boolean) evaluateExpressionWithMapContext(ctx, expression, methodContextAsMap);
		}
		return result;
	}

	/**
	 * Returns true if the expression is empty, or evaluates the expression and returns it's result
	 * 
	 * @param ctx
	 * @param expression
	 * @param value
	 *            from question form
	 * @return
	 */
	public static Boolean evaluateValidationExpression(ApplicationContext ctx, String expression, Object value, Map<String, Object> additionalParameters) {
		Boolean result = true;
		if (!StringUtils.isEmptyOrNull(expression)) {
			// Populate context
			Map<String, Object> methodContextAsMap = new HashMap<String, Object>();
			methodContextAsMap.put("questionValue", value);
			if(additionalParameters!=null && !additionalParameters.isEmpty()){
				for (Map.Entry<String, Object> entry : additionalParameters.entrySet()) {
					methodContextAsMap.put(entry.getKey(), entry.getValue());
				}
			}
			LOG.debug("****************************EXPRESSION TO DEBUG*****"+expression);
			result = (Boolean) evaluateExpressionWithMapContext(ctx, expression, methodContextAsMap);
		}
		return result;
	}
}