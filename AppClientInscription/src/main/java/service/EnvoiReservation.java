package service;

import meserreurs.MonException;
import metier.Reservation;

import javax.jms.*;

public class EnvoiReservation {
    // Session établie avec le serveur
    private TopicSession session = null;

    // Le client utilise un Producteur de messsage pour envoyer une demande de
    // formation
    private TopicPublisher producer;

    /**
     * Permet de publier une demande d'inscription dans le topic
     *
     * @param uneDemande La demande d'inscription � publier
     * @return
     * @throws Exception
     */
    public    boolean  publier(Reservation uneDemande, Topic topic, TopicConnectionFactory cf) throws Exception {

        boolean ok = true;
        TopicConnection connection = null;

        try {

            // On crée la connexion JMS , session, producteur et message;
            /*
             * connection = connectionFactory.createConnection(
             * System.getProperty("username", DEFAULT_USERNAME),
             * System.getProperty("password", DEFAULT_PASSWORD));
             */


            // Création Connection et Session JMS
            connection = cf.createTopicConnection();
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            // On crée le producteur utilisé pour envoyer un message
            producer = session.createPublisher(topic);
            // On lance la connection
            connection.start();
            ObjectMessage message = session.createObjectMessage();
            message.setObject(uneDemande);
            // On publie le message
            producer.publish(message);
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException j) {
            new MonException(j.getMessage());
            ok = false;
        } catch (Exception e) {
            new MonException(e.getMessage());
            ok = false;
        }
        return ok;
    }
}
