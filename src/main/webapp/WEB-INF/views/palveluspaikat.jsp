<%-- 
    Document   : palveluspaikat
    Created on : 15.5.2016, 20:52:37
    Author     : Aleksanteri
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Palveluspaikat</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.3/jquery.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"/>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="tyyli.css"/>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/palveluspaikat">Varusmiesrekisteri</a>
                </div>
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/palveluspaikat">Palveluspaikat</a>
                    </li>
                    <!-- Tähän dropdown olemassa olevista palveluspaikoista -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Palveluspaikka.. <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <c:forEach var="palvpaik" items="${palveluspaikat}">
                            <li><a href="/palveluspaikka/${palvpaik.palvpaikID}">${palvpaik.palvnimi}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <h1>Tässä on listaus palveluspaikoista</h1>

            <ul>
                <c:forEach var="palvpaik" items="${palveluspaikat}">
                    <li><a href="/palveluspaikka/${palvpaik.palvpaikID}">${palvpaik.palvnimi} (${palvpaik.paikkakunta}) </a><form action="/palveluspaikat/${palvpaik.palvpaikID}/delete" method="POST" onsubmit="return confirm('Oletko varma että haluat poistaa palveluspaikan ${palvpaik.palvnimi}?');"><input type="submit" value="poista"/></form></li>
                </c:forEach>
            </ul>

            <h2>Lisää uusi palveluspaikka</h2>
            <form class="formi" action="/palveluspaikat" method="POST">
                <p><label>Nimi: <input type="text" name="nimi"/></label></p>
                <p><label>Osoite: <input type="text" name="osoite"/></label></p>
                <p><label>Postinumero: <input type="text" name="postinro"/></label></p>
                <p><label>Paikkakunta: <input type="text" name="paikkakunta"/></label></p>
                <input type="submit" value="Lisää"/>
            </form>
        </div>
    </body>
</html>
