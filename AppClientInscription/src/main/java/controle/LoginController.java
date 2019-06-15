package controle;

import metier.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import repositories.ClientEntityRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
    //@Autowired
    private ClientEntityRepository unClientRepository;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean correctLogin = true;

        //String userName = request.getParameter("userName");
        int userId = -1;
        try{
            userId = Integer.parseInt(request.getParameter("userId"));
        } catch(NumberFormatException e){
            correctLogin = false;
        }

        //int underscoreIndex = userName.indexOf("_");
        if(correctLogin && request.getParameter("password").equals("secret")){
            /*String firstName = userName.substring(0, underscoreIndex).toUpperCase();
            String lastName = userName.substring(underscoreIndex+1).toUpperCase();*/

            /*ClientEntity unClient;
            unClient = unClientRepository.findById(userId).get();*///unClient = unClientRepository.rechercheNomPrenom(lastName, firstName);
//            if(unClientRepository == null){
//                unClientRepository = new ClientEntityRepository();
//            }
            Optional<ClientEntity> optional = unClientRepository.findById(userId);

            if(optional.isPresent()){
                HttpSession session = request.getSession();
                session.setAttribute("id", userId);
                /*session.setAttribute("jwt", new JWTManager.Builder()
                        .setId(unUtilisateur.getIdClient().toString())
                        .setExpiredAfterMillis(3600000L)
                        .build());*/
            }
            else{
                correctLogin = false;
            }
        }
        else {
            correctLogin = false;
        }

        if(correctLogin){
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("/Connexion.jsp").forward(request, response);
        }
    }
}
