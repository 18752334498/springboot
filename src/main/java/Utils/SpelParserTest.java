package Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpelParserTest {
	public static void main(String[] args) {
		testBean();
	}

	public static void testMap() {
		EvaluationContext context = new StandardEvaluationContext();

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		context.setVariable("map", map);
		Map<?, ?> map2 = new SpelExpressionParser().parseExpression("#map").getValue(context, Map.class);
//		String string = new SpelExpressionParser().parseExpression("#map['a']").getValue(context, String.class);
		System.out.println(map2);
	}

	public static void testList() {
		EvaluationContext context = new StandardEvaluationContext();

		List<Integer> list = new ArrayList<Integer>();
		list.addAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17));
		context.setVariable("list", list);
//		List<?> list2 = new SpelExpressionParser().parseExpression("#list").getValue(context, List.class);
		Object value = new SpelExpressionParser().parseExpression("#list.?[#this>10]").getValue(context);
		System.out.println(value);
	}

	public static void testBean() {
		EvaluationContext context = new StandardEvaluationContext();

		Person person = new Person("jack", "rose");
		context.setVariable("person", person);
//		Person person2 = new SpelExpressionParser().parseExpression("#person").getValue(context, Person.class);
		String firstName = (String) new SpelExpressionParser().parseExpression("#person.firstName").getValue(context);
		System.out.println(firstName);
	}
}

class Person {
	private String firstName;
	private String lastName;

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "[" + firstName + "===" + lastName + "]";
	}
}
