package bf.orange.oguest.oguestbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class OguestBackendApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(OguestBackendApplication.class, args);

        Date date = new Date();
        System.out.println(new Date());
        System.out.println("------------------------------------------------------------");
        System.out.println("--------------- "+date+" ---------------");
        System.out.println("------------------------------------------------------------");
        System.out.println("---------- Welcome To O'Guest Backend Application! ---------");
        System.out.println("------------------------------------------------------------");

    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

}
