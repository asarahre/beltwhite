package com.beltwhite.loader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.beltwhite.model.Role;
import com.beltwhite.model.RoleName;
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

        // Percorre os valores do enum RoleName
        for (RoleName roleName : RoleName.values()) {
            // Busca pelo enum no banco ou cria se não existir
            roleRepository.findByNome(roleName)
                    .orElseGet(() -> roleRepository.save(new Role(roleName)));
        }
    }
}