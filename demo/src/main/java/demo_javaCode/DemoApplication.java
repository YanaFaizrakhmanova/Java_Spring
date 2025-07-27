package demo_javaCode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;



@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

		// Получаем экземпляр автомобиля из Spring-контейнера
		Car car = ctx.getBean(Car.class);

		// Выводим объект на консоль
		System.out.println(car);
	}



}
