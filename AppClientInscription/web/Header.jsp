<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="refresh" content="0;URL=javascript:fermer();">
    <title> Autolib </title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="resources/css/jquery-ui.min.css">
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/jquery-ui.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse inscription-header">
    <div class="container-fluid">
        <div class="navbar-header">
            <a id="logo_polytech" class="navbar-brand" href=""> <img src="resources/images/logo.png"
                                                                              height="45px"></a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="">Accueil</a></li>
            <c:if test="${sessionScope.id == null }">
                <li><a href="Controleur?action=connexion">Connexion</a></li>
            </c:if>
            <c:if test="${sessionScope.id > 0  }">
                <li><a href="Controleur?action=listeStations">Nouvelle réservation</a></li>
                <li><a href="Controleur?action=logout">Déconnexion</a></li>
            </c:if>
        </ul>
    </div>
</nav>
<br><br><br><br>

