package com.beltwhite.loader;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.beltwhite.model.Role;
import com.beltwhite.repository.RoleRepository;

@Component
@Profile({ "dev", "test", "prod" })
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> roles = List.of("ADMIN", "PROFESSOR", "JUJITSUKA");

        for (String roleName : roles) {
            roleRepository.findByNome(roleName)
                    .orElseGet(() -> roleRepository.save(new Role(roleName)));
        }
    }
}