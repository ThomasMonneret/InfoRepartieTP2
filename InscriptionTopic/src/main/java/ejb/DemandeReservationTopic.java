package ejb;

import dao.EnregistreInscription;
import dao.EnregistrerReservation;
import meserreurs.MonException;
import metier.Inscription;
import metier.InscriptionEntity;
import metier.Reservation;
import metier.ReservationEntity;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.NamingException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination",
                propertyValue = "java:jboss/exported/topic/DemandeInscriptionJmsTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")},
        mappedName = "DemandeInscriptionJmsTopic")
public class DemandeReservationTopic implements MessageListener {

    @Resource
    private MessageDrivenContext context;

    /*
     * Default constructor.
     */
    public DemandeReservationTopic(){
        // TODO Auto-generated constructor stub
    }

    /**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message)  {
        // TODO Auto-generated method stub
        boolean ok = false;
        // On gère le message récupéré dans le topic

        try {
            // On transforme le message en demande de réservation
            ObjectMessage objectMessage = (ObjectMessage) message;
            Reservation uneReservation = (Reservation) objectMessage.getObject();

            if (message != null) {
                // On insère cette demande de réservation dans la base de données
                // on s'assure que l'écriture ne se fera qu'une fois.
                message = null;

                try {

                    // on construit un objet Entity
                    ReservationEntity uneResEntity = new ReservationEntity();
                    // on tansfère les données reçues dans l'objet Entity
                    uneResEntity.setVehicule(uneReservation.getVehicule());
                    uneResEntity.setClient(uneReservation.getClient());
                    uneResEntity.setDateReservation(uneReservation.getDateReservation());
                    uneResEntity.setDateEcheance(uneReservation.getDateEcheance());
                    EnregistrerReservation uneR = new EnregistrerReservation();

                    uneR.insertionReservation(uneResEntity);
                } catch (Exception ex) {
                    System.out.println("Message Excep :" + ex.getMessage());
                    EcritureErreur(ex.getMessage());
                }
            }

        }   catch (JMSException e) {
            e.printStackTrace();
            System.err.println("JMSException in onMessage(): " + e.getMessage());
            EcritureErreur(e.getMessage());
        }
        catch (Exception ex) {
            System.out.println("Erreur Cast : "); ex.printStackTrace(System.out);
            EcritureErreur(ex.getMessage());
        }
    }

    /**
     * Permet d'enregistrer une erreur dans un fichier log
     *
     * @param message Le message d'erreur
     */
    public void EcritureErreur(String message) {
        BufferedWriter wr;
        String nomf = "erreurs.log";
        Date madate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm");
        try {
            // On écrit à la fin du fichier
            wr = new BufferedWriter(new FileWriter(nomf, true));
            wr.newLine();
            wr.write(sdf.format(madate));
            wr.newLine();
            wr.write(message);
            wr.close();
        } catch (FileNotFoundException ef) {
            ;
        } catch (IOException eio) {
            ;
        }
    }
}
