<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="Header.jsp" %>
<script language="Javascript" type="text/javascript"></script>
<script type="text/javascript" src="js/foncControle.js"></script>
<script>
    function Chargement() {
        var obj = document.getElementById("id_erreur");
        if (obj.value != '')
            alert('Erreur signalée  : "' + obj.value + "'");
    }
</script>
<script src="js/js_verif.js" type="text/javascript"></script>

<form action='Controleur?action=envoiReservation' method='post' onsubmit="return verif(this);">
    <div>
        <div class="container">
            <div class="well">
                <h1>Ajouter une réservation</h1>
                <div class="form-group">
                    <label for="idVehicule">Id véhicule</label>
                    <input type="text" class="form-control" name="idVehicule">
                </div>

                <div class="form-group">
                    <label for="idReservataire">Id réservataire</label>
                    <input type="text" class="form-control" name="idReservataire">
                </div>
                <div class="form-group">
                    <label for="dateReservation"> Date Réservation:</label>
                    <input name="dateReservation" id="dateReservation" class="form-control" min="0">
                </div>
                <script>$("#dateReservation").datepicker();</script>
                <div class="form-group">
                    <button type="submit" class="btn btn-default btn-primary"><span
                            class="glyphicon glyphicon-ok"></span>
                        Valider
                    </button>
                    <button type="button" class="btn btn-default btn-primary"
                            onclick="{ window.location = 'index.jsp'; }">
                        <span class="glyphicon glyphicon-remove"></span> Annuler
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
