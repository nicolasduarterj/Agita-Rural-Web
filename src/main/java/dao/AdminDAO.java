package dao;

import modelos.Representante;
import java.util.HashMap;
import java.util.Map;

public class AdminDAO {

    private static final Map<String, Representante> admins = new HashMap<>();

    static {
        admins.put("admin@email.com",
            new Representante(
                "Administrador Geral",
                "00000000000",
                "admin@email.com",
                "senha123"
            )
        );
    }

    public Representante autenticar(String email, String password) {
        Representante a = admins.get(email);

        if (a != null && a.getPasswordHash().equals(password)) {
            return a;
        }
        return null;
    }

    public Representante buscarPorEmail(String email) {
        return admins.get(email);
    }
}
