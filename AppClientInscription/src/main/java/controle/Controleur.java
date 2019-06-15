package controle;

import java.io.IOException;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import metier.ClientEntity;
import metier.Reservation;


import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import meserreurs.MonException;
import metier.StationEntity;
import org.springframework.data.domain.Sort;
import repositories.ClientEntityRepository;
import repositories.StationEntityRepository;
import service.EnvoiReservation;

/**
 * Servlet implementation class Traitement
 */
@WebServlet("/Controleur")
public class Controleur extends HttpServlet {

    private static final long serialVersionUID = 10L;
    private static final String ACTION_TYPE = "action";
    private static final String CONNEXION = "connexion";
    private static final String LOGIN = "login";
    private static final String LOGOUT = "logout";
    private static final String AJOUTER_RESERVATION = "ajouteReservation";
    private static final String ENVOI_RESERVATION = "envoiReservation";
    private static final String LISTE_STATIONS = "listeStations";
    private static final String RETOUR_ACCUEIL = "Retour";

    private ClientEntityRepository unClientRepository;
    private StationEntityRepository unStationRepository;

    /**
     * @see HttpServlet#HttpServlet()
     */

    @Resource(lookup = "java:jboss/exported/topic/DemandeInscriptionJmsTopic")
    private Topic topic;
    // On accède à l'EJB
    @Resource(mappedName = "java:/ConnectionFactory")
    private TopicConnectionFactory cf;

    /**
     * Constructeur par défaut de la classe
     */
    public Controleur() {
        super();
    }

    /**
     *
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            TraiteRequete(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            TraiteRequete(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Procédure principale de démarrage
     */
    public void TraiteRequete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter(ACTION_TYPE);

        if (actionName == null || RETOUR_ACCUEIL.equals(actionName)) {
            retourAccueil(request, response);
        } else if (CONNEXION.equals(actionName)) {
            connexion(request, response);
        } else if (LOGIN.equals(actionName)) {
            login(request, response);
        } else if (LOGOUT.equals(actionName)) {
            logout(request, response);
        } else if (AJOUTER_RESERVATION.equals(actionName)) {
            ajouterReservation(request, response);
        } else if (ENVOI_RESERVATION.equals(actionName)) {
            envoiReservation(request, response);
        } else if (LISTE_STATIONS.equals(actionName)) {
            listeStations(request, response);
        }

    }

    private void retourAccueil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
    }

    private void connexion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Connexion.jsp").forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            ClientEntity unClient;
            unClient = unClientRepository.findById(userId).get();//unClient = unClientRepository.rechercheNomPrenom(lastName, firstName);

            if(unClient != null){
                HttpSession session = request.getSession();
                session.setAttribute("id", unClient.getIdClient());
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

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void ajouterReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/AjouteReservation.jsp").forward(request, response);
    }

    private void envoiReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        response.setContentType("text/html;charset=UTF-8");
        // On récupère les informations sisies


        try {
            Integer idVehicule = Integer.parseInt(request.getParameter("idVehicule"));
            Integer idClient = Integer.parseInt(request.getParameter("idReservataire"));

            java.util.Date dateReservation = sdf.parse(request.getParameter("dateReservation"));
            Timestamp timestampReservation = new java.sql.Timestamp(dateReservation.getTime());
            Calendar c = Calendar.getInstance();
            c.setTime(dateReservation);
            c.add(Calendar.DATE, 1);
            java.util.Date dateEcheance = c.getTime();
            Timestamp timestampEcheance = new java.sql.Timestamp(dateEcheance.getTime());

            // On crée une demande de réservation avec ces valeurs
            Reservation uneReservation = new Reservation();
            uneReservation.setVehicule(idVehicule);
            uneReservation.setClient(idClient);
            uneReservation.setDateReservation(timestampReservation);
            uneReservation.setDateEcheance(timestampEcheance);

            // On envoie cette demande de réservation dans le topic
            EnvoiReservation unEnvoi = new EnvoiReservation();
            boolean ok = unEnvoi.publier(uneReservation,topic,cf);
            if (ok)
                // On retourne àla page d'accueil
                this.getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
            else {
                this.getServletContext().getRequestDispatcher("/Erreur.jsp").include(request, response);
            }
        } catch (MonException m) {
            // On passe l'erreur à  la page JSP
            request.setAttribute("MesErreurs", m.getMessage());
            request.getRequestDispatcher("/PostMessage.jsp").forward(request, response);
        } catch (Exception e) {
            // On passe l'erreur à la page JSP
            System.out.println("Erreur client  :" + e.getMessage());
            request.setAttribute("MesErreurs", e.getMessage());
            request.getRequestDispatcher("/PostMessage.jsp").forward(request, response);
        }
    }

    private void listeStations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<StationEntity> mesStations = unStationRepository.findAll(new Sort(Sort.Direction.ASC, "codePostal"));
        request.setAttribute("stations", mesStations);
        request.getRequestDispatcher("/ListeStations.jsp").forward(request, response);
    }

}
