import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import ru.otus.console.IOService;
import ru.otus.db.CSVWorksDB;
import ru.otus.menu.Menu;
import ru.otus.menu.MenuItem;
import ru.otus.menu.commands.ShowRepertoireCommand;
import ru.otus.model.Work;

public class Runner {

    @Test
    public void testRunApplication() {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CSVWorksDB csvWorksDB = context.getBean(CSVWorksDB.class);
        List<Work> works = csvWorksDB.getDB();
        System.out.println(works);
    }

    @Test
    void createReader() {
        CsvToBean<Work> csvReader;
        List<Work> works = new ArrayList<>();
        try (Reader reader = new InputStreamReader(new ClassPathResource("repertoire.csv").getInputStream())) {

            csvReader = new CsvToBeanBuilder<Work>(reader)
                    .withSeparator(';')
                    .withType(Work.class)
                    .build();
            works = csvReader.parse();

        } catch (IOException e) {
            System.err.printf("%s%s%n", e.getMessage(), e);
            throw new RuntimeException(e);
        }
        System.out.println(works);
    }

    @Test
    void allRepertoireCommands() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ShowRepertoireCommand showRepertoireCommand = context.getBean(ShowRepertoireCommand.class);
        showRepertoireCommand.execute();
    }

    @Test
    void printMenu() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Menu menu = context.getBean(Menu.class);
        IOService ioService = context.getBean(IOService.class);
        List<MenuItem> menuItems = menu.menuItems();
        menuItems.forEach(item -> ioService.printLineFormatted("%s. %s%n", item.id(), item.name()));
        ioService.printLine("5. Exit program");
    }
}
