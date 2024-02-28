package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      userService.add(new User("John", "Doe", "john.doe@example.com", new Car("Toyota", 12345)));
      userService.add(new User("Jane", "Smith", "jane.smith@example.com", new Car("Ford", 54321)));


       System.out.println(userService.getUserByCarModelAndSeries("Toyota", 12345));
       System.out.println("--------------------------------\n");
       userService.getUsersByCarModelAndSeries("Toyota", 12345).forEach(System.out::println);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("User: " + user.getFirstName() + " " + user.getLastName());
         System.out.println("Car: " + user.getCar().getModel() + " " + user.getCar().getSeries());
         System.out.println();
      }

      context.close();
   }
}
