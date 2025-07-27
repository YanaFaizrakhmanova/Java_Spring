package demo_annotation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class DemoApplication {

	public static void main(String[] args) {
		// Загружаем контекст Spring из XML
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");

		// Извлекаем объект типа Car из контекста
		Car myCar = context.getBean(Car.class);

		// Выводим полную структуру нашего автомобиля
		System.out.println(myCar.toString());
	}


}
