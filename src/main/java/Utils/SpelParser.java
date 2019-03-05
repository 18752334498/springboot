package Utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelParser {

	private static ExpressionParser parser = new SpelExpressionParser();

	public static Object getKey(String key, ProceedingJoinPoint pjp) {
		Method method = ((MethodSignature) pjp.getSignature()).getMethod();
		// 获得方法的形参名称，jdk的反射里没有提供相应方法，但spring提供一个
		String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
		return parseKey(key, parameterNames, pjp.getArgs());
	}

	/**
	 * 将 "#value" 这个字符串解析为vaule对应的值
	 * 
	 * @param key            "#value"
	 * @param parameterNames 形参的名称
	 * @param args           形参的值
	 * @return
	 */
	private static Object parseKey(String key, String[] parameterNames, Object[] args) {

		EvaluationContext context = new StandardEvaluationContext();
		if (args == null || args.length < 0) {
			System.out.println("===============");
			return null;
		}
		for (int i = 0; i < args.length; i++) {
			context.setVariable(parameterNames[i], args[i]);
		}

		Expression expression = parser.parseExpression(key);// 将key字符串解析为el表达式
		return expression.getValue(context);
	}


	public static void main(String[] args1) {
		String key = "#age";
		List<Integer> list = new ArrayList<Integer>();
		list.addAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17));
		// 方法的形参名称
		String[] parameterNames = new String[] { "age", "list" };
		// 方法的形参值
		Object[] args = new Object[] { "", list };
		System.out.println(parseKey(key, parameterNames, args));
	}

}
