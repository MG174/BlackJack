
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Generate random card!</h1>
        </br></br></br>
        <c:forEach var="vmap" items="${cards_hashmap_game_in_progress}">
            <img style="height: 300px" src=images/${vmap.key}.png><br>
        </c:forEach>
        </br>           
        <h5>Value of card is: ${value_of_cards_in_game}</h5>
        </br>

        <form method="POST" action="RandomCard">
            <c:forEach var="configParams" items="${cards_hashmap_game_in_progress}" varStatus="itemsRow">
                <tr>
                    <td><input type="hidden" name="configParam.${itemsRow.index}" value="${configParams.key}" /></td>
                </tr>
            </c:forEach>
            <input type="submit"></input>
        </form>
        </br>
    </body>
</html>
