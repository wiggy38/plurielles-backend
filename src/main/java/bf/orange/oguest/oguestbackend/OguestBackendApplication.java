package bf.orange.oguest.oguestbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class OguestBackendApplication {

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

}
