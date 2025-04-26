package com.AssessPro.config.seeder;

import java.util.List;

import java.util.Optional;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.AssessPro.enums.RoleName;
import com.AssessPro.model.RoleModel;
import com.AssessPro.model.UserModel;
import com.AssessPro.repository.RoleRepository;
import com.AssessPro.repository.UserRepository;

@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private boolean alreadySetup = false;

    public DataSeeder(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;

        // Verifica se já existem roles no banco
        if (roleRepository.count() > 0) {
            alreadySetup = true;
            return;
        }

     
        for (RoleName roleName : RoleName.values()) {
            roleRepository.findByRoleName(roleName).orElseGet(() -> {
                RoleModel newRole = new RoleModel();
                newRole.setRoleName(roleName);
                RoleModel saved = roleRepository.save(newRole);
                System.out.printf("Papel %s criado.%n", roleName.name());
                return saved;
            });
        }

        //Criação do usuário admin
        Optional<RoleModel> adminRole = roleRepository.findByRoleName(RoleName.ROLE_ADMIN);
        if (adminRole.isPresent()) {
            UserModel admin = new UserModel();
            admin.setUsername("admin2");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@admin2.com");
            admin.setRoles(List.of(adminRole.get())); // ← transforma em lista
            admin.setEnabled(true);
            userRepository.save(admin);
            System.out.println("Usuário ADMIN criado com sucesso.");
        } else {
            System.out.println("ROLE_ADMIN não foi encontrado no banco.");
        }
        

        alreadySetup = true;
    }
}
