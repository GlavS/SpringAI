import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.menu.Command;
import ru.otus.menu.Menu;

public class Runner {

    @Test
    public void testRunApplication() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Menu menu = (Menu) context.getBean("menu");
        menu.menuItems().forEach(item -> System.out.printf("%d. %s%n", item.id(), item.name()));
        Map<String, Command> commands = context.getBeansOfType(Command.class);
        System.out.println(commands);
    }
}
