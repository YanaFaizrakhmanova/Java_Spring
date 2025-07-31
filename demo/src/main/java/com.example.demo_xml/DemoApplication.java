package com.example.demo_xml;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class DemoApplication {

	public static void main(String[] args) {
		// Загружаем контекст Spring из XML
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// Извлекаем объект типа Car из контекста
		Car myCar = context.getBean(Car.class);

		// Выводим полную структуру нашего автомобиля
		System.out.println(myCar);
		context.close();
	}


}
