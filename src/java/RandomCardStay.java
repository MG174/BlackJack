/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import classes.Counter;
import classes.Money;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;

/**
 *
 * @author goryc
 */
@WebServlet(urlPatterns = {"/RandomCardStay"})
public class RandomCardStay extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        LinkedHashMap<String, Integer> cards_hashmap_game_in_progress = (LinkedHashMap<String, Integer>) session.getAttribute("map_from_jsp");
        LinkedHashMap<String, Integer> cards_hashmap_game_in_progress_croupier = (LinkedHashMap<String, Integer>) session.getAttribute("cards_hashmap_game_in_progress_croupier");

        Money money = (Money) session.getAttribute("money_object");

        Counter counter = new Counter();

        cards_hashmap_game_in_progress_croupier = counter.get_random_cards_croupier_and_return(cards_hashmap_game_in_progress_croupier);

        int have_won = counter.have_won_player(cards_hashmap_game_in_progress, cards_hashmap_game_in_progress_croupier);
        String info_have_won = "";

        int initial_bet = (int) session.getAttribute("initial_bet");

        if (have_won == 4) {
            info_have_won = "BLACK JACK";
            money.money_plus(initial_bet * 2);
        } else if (have_won == 1) {
            info_have_won = "Gracz wygrał";
            money.money_plus(initial_bet * 2);
        } else if (have_won == 2) {
            info_have_won = "Gracz przegrał";
        } else if (have_won == 3) {
            info_have_won = "Remis";
            money.money_plus(initial_bet);
        } else if (have_won == 5) {
            info_have_won = "Wystąpił błąd";
        }

        int amount_of_money = money.getMoney_amount();

        String info_value_cards = counter.get_info_value(cards_hashmap_game_in_progress);
        String info_value_croupier = counter.get_info_value(cards_hashmap_game_in_progress_croupier);

        RequestDispatcher requestDispatcher;
        request.setAttribute("cards_hashmap_game_in_progress", cards_hashmap_game_in_progress);
        request.setAttribute("value_of_cards_in_game", info_value_cards);
        request.setAttribute("cards_hashmap_game_in_progress_croupier", cards_hashmap_game_in_progress_croupier);
        request.setAttribute("value_of_cards_in_game_croupier", info_value_croupier);
        request.setAttribute("info_have_won", info_have_won);
        request.setAttribute("amount_of_money", amount_of_money);

        session.setAttribute("map_from_jsp", 0);
        session.setAttribute("cards_hashmap_game_in_progress_croupier", 0);

        if (amount_of_money == 0) {
            session.invalidate();
            requestDispatcher = request.getRequestDispatcher("/GameOver.jsp");
        } else {
            requestDispatcher = request.getRequestDispatcher("/CardDisplayGameOver.jsp");
        }

        requestDispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
