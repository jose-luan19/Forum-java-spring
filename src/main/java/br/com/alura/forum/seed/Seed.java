package br.com.alura.forum.seed;

import br.com.alura.forum.modelo.Perfil;
import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.PerfilRepository;
import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Seed {
    private final UsuarioRepository userRepository;
    private final PerfilRepository perfilRepository;
    @Autowired
    public Seed(UsuarioRepository userRepository, PerfilRepository perfilRepository) {
        this.userRepository = userRepository;
        this.perfilRepository = perfilRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        if (!userRepository.findByEmail("admin@.email.com").isPresent()){
            seedUsers();
        }
    }
    public void seedUsers() {

        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        String senhaAdmin = crypt.encode("admin");

        Usuario admin = new Usuario("admin","admin@email.com", senhaAdmin);
        admin.setPerfis(seedPerfil());
        userRepository.save(admin);

//        String senhaUser = crypt.encode("user");
//        Usuario user = new Usuario("user", "user@.user.com", senhaUser);
//        userRepository.save(user);
    }
    public Perfil seedPerfil(){
        Perfil admin = new Perfil("ADM");
        perfilRepository.save(admin);
        return admin;
    }


}