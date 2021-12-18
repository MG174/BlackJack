
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="/BlackJack//CSS/styles.css" rel="stylesheet" type="text/css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="whole_page">
            <div class="playing_div">
                <h1>Croupier's cards:</h1>
                <div class="display_images">
                    <c:forEach var="vmap" items="${cards_hashmap_game_in_progress_croupier}">
                        <img class="playing_card" style="height: 300px" src=images/${vmap.key}.png><br>
                    </c:forEach>
                    <img class="playing_card" style="height: 300px" src=images/back_of_cards.png><br>
                </div>
                <h5>Value of cards: ${value_of_cards_in_game_croupier}</h5>
            </div>
            <div class="playing_div">
                <h1>Your cards:</h1>
                <div class="display_images">
                    <c:forEach var="vmap" items="${cards_hashmap_game_in_progress}">
                        <img class="playing_card" style="height: 300px" src=images/${vmap.key}.png><br>
                    </c:forEach>
                </div>
                <h5>Value of cards: ${value_of_cards_in_game}</h5>
            </div>
            <form class="form_playing" method="POST" action="RandomCard">
                <input type="submit" value="Hit"></input>
            </form >
            <form class="form_playing" method="POST" action="RandomCardStay">
                <input type="submit" value="Stay"></input>
            </form>
            <h5>Wynik: ${info_have_won}</h5>
            <h5>Stan konta: ${amount_of_money}</h5>

        </div>
    </body>
</html>
