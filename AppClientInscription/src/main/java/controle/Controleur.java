package controle;

import java.io.IOException;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import metier.Reservation;


import java.util.Calendar;

import javax.annotation.Resource;

import meserreurs.MonException;
import service.EnvoiReservation;

/**
 * Servlet implementation class Traitement
 */
@WebServlet("/Controleur")
public class Controleur extends HttpServlet {

    private static final long serialVersionUID = 10L;
    private static final String ACTION_TYPE = "action";
    private static final String AJOUTER_RESERVATION = "ajouteReservation";
    private static final String ENVOI_RESERVATION = "envoiReservation";
    private static final String RETOUR_ACCUEIL = "Retour";

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
        // TODO Auto-generated method stub
        try {
            TraiteRequete(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Procédure principale de démarrage
     */
    public void TraiteRequete(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // On récupère l'action
        String actionName = request.getParameter(ACTION_TYPE);

        // Si on veut afficher l'ensemble des demandes d'inscription
        if (RETOUR_ACCUEIL.equals(actionName)) {
            this.getServletContext().getRequestDispatcher("/index.jsp").include(request, response);
        } else if (AJOUTER_RESERVATION.equals(actionName)) {

            request.getRequestDispatcher("AjouteReservation.jsp").forward(request, response);
        } else if (ENVOI_RESERVATION.equals(actionName)) {
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

                    // On envoie cette demande d'inscription dans le topic
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
                    request.getRequestDispatcher("PostMessage.jsp").forward(request, response);
                } catch (Exception e) {
                    // On passe l'erreur à la page JSP
                    System.out.println("Erreur client  :" + e.getMessage());
                    request.setAttribute("MesErreurs", e.getMessage());
                    request.getRequestDispatcher("PostMessage.jsp").forward(request, response);
                }
        }

    }



}
