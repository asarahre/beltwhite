package com.beltwhite;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.beltwhite.config.TestDataSourceConfig;
import com.beltwhite.model.Role;
import com.beltwhite.model.RoleName;
import com.beltwhite.model.Usuario;
import com.beltwhite.repository.RoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@Import(TestDataSourceConfig.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void deveRegistrarUsuarioComSucesso() throws Exception {

        Role adminRole = roleRepository.findByNome(RoleName.ADMIN)
                .orElseThrow(() -> new RuntimeException("Role ADMIN não encontrada"));

        List<Role> roles = List.of(adminRole);

        Usuario usuario = new Usuario("sarah1222@test.com", "Sarah", "123456", roles);

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated()); // cridado com suceso 201

    }

}
