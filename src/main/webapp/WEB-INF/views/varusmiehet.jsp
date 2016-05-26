<%-- 
    Document   : varusmiehet
    Created on : 15.5.2016, 19:11:45
    Author     : Aleksanteri
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Palveluspaikassa ${palveluspaikka.palvnimi} olevat varusmiehet</title>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
                    <li>
                        <a href="#">Varusmiehet..</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <h1>Palveluspaikka ${palveluspaikka.palvnimi}</h1>


            <ul>
                <c:forEach var="vmies" items="${varusmiehet}">
                    <li>${vmies.nimi}<form action="/palveluspaikka/${palveluspaikka.palvpaikID}/${vmies.vid}/delete" method="POST"><input type="submit" value="poista"/></form></li>
                </c:forEach>
            </ul>

            <h2>Lisää uusi varusmies</h2>
            <form action="/palveluspaikka/${palveluspaikka.palvpaikID}" method="POST">
                <p>Sukunimi: <input type="text" name="sukunimi"/></p>
                <p>Etunimet: <input type="text" name="etunimet"/></p>
                <p>Henkilötunnus: <input type="text" name="hetu"/></p>
                <p>Osoite: <input type="text" name="osoite"/></p>
                <p>Paikkakunta: <input type="text" name="paikkakunta"/></p>
                <p>Postinumero: <input type="text" name="postinro"/></p>
                <p>Cooper: <input type="text" name="cooper"/></p>
                <p>Palveluspaikka ID: <input type="text" name="palvpaikid"/></p>
                <input type="submit" value="Lisää"/>
            </form>
        </div>
    </body>
</html>
