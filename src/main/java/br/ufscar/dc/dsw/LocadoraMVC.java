package br.ufscar.dc.dsw;

import java.time.LocalDate;

import br.ufscar.dc.dsw.dao.*;
import br.ufscar.dc.dsw.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LocadoraMVC {

    public static void main(String[] args) {
        SpringApplication.run(LocadoraMVC.class, args);
    }

    @Bean
    public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IAdminDAO adminDAO, ILocadoraDAO locadoraDAO, IClienteDAO clienteDAO, ILocacaoDAO locacaoDAO) {
        return (args) -> {

            Admin a1 = new Admin();
            a1.setUsername("admin");
            a1.setEmail("admin@admin");
            a1.setPassword(encoder.encode("admin"));
            a1.setName("Administrador");
//			a1.setRole("ROLE_ADMIN");
            a1.setEnabled(true);
            usuarioDAO.save(a1);

            Cliente u2 = new Cliente();
            u2.setUsername("beltrano");
            u2.setEmail("cliente@cliente");
            u2.setPassword(encoder.encode("123"));
            u2.setCpf("985.849.614-10");
            u2.setName("Beltrano Andrade");
            u2.setBirthDate(LocalDate.now());
            u2.setSex(Sexo.MASCULINO);
            u2.setPhoneNumber("(82) 99999-9999");
//            u2.setRole("ROLE_USER");
            u2.setEnabled(true);
            clienteDAO.save(u2);

            Locadora u3 = new Locadora();
            u3.setUsername("fulano");
            u3.setEmail("locadora@locadora");
            u3.setPassword(encoder.encode("123"));
            u3.setCnpj("11.111.111/0001-11");
            u3.setCity("Cidade");
            u3.setName("Fulano Silva");
//            u3.setRole("ROLE_USER");
            u3.setEnabled(true);
            locadoraDAO.save(u3);

        };
    }
}
