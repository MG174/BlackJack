/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import classes.Counter;
import classes.Deck;
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
import java.util.Random;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author goryc
 */
@WebServlet(urlPatterns = {"/RandomCard"})
public class RandomCard extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RandomCard</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RandomCard at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

        HttpSession session = request.getSession();
        session.setAttribute("map_from_jsp", 0);
        session.setAttribute("cards_hashmap_game_in_progress_croupier", 0);

        Money money;
        int amount_start_game_money_int = 0;

        //Create player and set money amount
        if (session.getAttribute("money_object") == null) {
            money = new Money();
            String amount_start_game_money = request.getParameter("start_game_value");
            amount_start_game_money_int = Integer.valueOf(amount_start_game_money);
            money.setMoney_amount(amount_start_game_money_int);
        } else {
            money = (Money) session.getAttribute("money_object");
        }

        String initial_bet_string = request.getParameter("initial_bet");
        int initial_bet_int = 0;

        if (initial_bet_string.equals("")) {
            initial_bet_int = 0;
        } else {
            initial_bet_int = Integer.valueOf(request.getParameter("initial_bet"));
        }

        if (initial_bet_int > money.getMoney_amount()) {
            initial_bet_int = money.getMoney_amount();
        }

        session.setAttribute("initial_bet", initial_bet_int);

        //deduct initial amount for starting game
        int amount_of_money = money.money_minus(initial_bet_int);

        session.setAttribute("money_object", money);

        //new deck ("disctionary for keys and values of cards")
        Deck deck = new Deck();
        LinkedHashMap<String, Integer> cards_hashmap = deck.getCards_hashmap();

        Counter counter = new Counter();

        Random rnd = new Random();
        int random_int_hashmap_size = rnd.nextInt(cards_hashmap.size());
        String card = (String) cards_hashmap.keySet().toArray()[random_int_hashmap_size];
        int value_of_random_card = cards_hashmap.get(card);

        // player
        LinkedHashMap<String, Integer> cards_hashmap_game_in_progress = new LinkedHashMap<String, Integer>();
        cards_hashmap_game_in_progress.put(card, value_of_random_card);

        do {
            random_int_hashmap_size = rnd.nextInt(cards_hashmap.size());
            card = (String) cards_hashmap.keySet().toArray()[random_int_hashmap_size];
        } while (cards_hashmap_game_in_progress.containsKey(card));

        value_of_random_card = cards_hashmap.get(card);
        cards_hashmap_game_in_progress.put(card, value_of_random_card);

        //croupier
        LinkedHashMap<String, Integer> cards_hashmap_game_in_progress_croupier = new LinkedHashMap<String, Integer>();
        int random_int_hashmap_size_croupier = rnd.nextInt(cards_hashmap.size());
        String card_croupier = (String) cards_hashmap.keySet().toArray()[random_int_hashmap_size_croupier];
        int value_of_random_card_croupier = cards_hashmap.get(card_croupier);
        cards_hashmap_game_in_progress_croupier.put(card_croupier, value_of_random_card_croupier);

        int value_of_cards_in_game_croupier = counter.count_cards(cards_hashmap_game_in_progress_croupier);

        //generating info about value of cards
        String info_value_cards = counter.get_info_value(cards_hashmap_game_in_progress);

        //have player won or lost
        int info_have_won_or_lost = counter.have_won_player(cards_hashmap_game_in_progress, cards_hashmap_game_in_progress_croupier);

        session = request.getSession();
        session.setAttribute("map_from_jsp", cards_hashmap_game_in_progress);
        session.setAttribute("cards_hashmap_game_in_progress_croupier", cards_hashmap_game_in_progress_croupier);
        RequestDispatcher requestDispatcher;

        request.setAttribute("cards_hashmap_game_in_progress", cards_hashmap_game_in_progress);
        request.setAttribute("value_of_cards_in_game", info_value_cards);
        request.setAttribute("cards_hashmap_game_in_progress_croupier", cards_hashmap_game_in_progress_croupier);
        request.setAttribute("value_of_cards_in_game_croupier", value_of_cards_in_game_croupier);
        request.setAttribute("amount_of_money", amount_of_money);

        if (info_have_won_or_lost == 4) {
            requestDispatcher = request.getRequestDispatcher("RandomCardStay");
            requestDispatcher.include(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher("/CardDisplayGameInProgress.jsp");
            requestDispatcher.forward(request, response);
        }

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

        Deck deck = new Deck();
        LinkedHashMap<String, Integer> cards_hashmap = deck.getCards_hashmap();

        Counter counter = new Counter();

        HttpSession session = request.getSession();
        LinkedHashMap<String, Integer> cards_hashmap_game_in_progress = (LinkedHashMap<String, Integer>) session.getAttribute("map_from_jsp");
        LinkedHashMap<String, Integer> cards_hashmap_game_in_progress_croupier = (LinkedHashMap<String, Integer>) session.getAttribute("cards_hashmap_game_in_progress_croupier");

        Money money = (Money) session.getAttribute("money_object");

        Random rnd = new Random();
        int random_int_hashmap_size = rnd.nextInt(cards_hashmap.size());
        String card = (String) cards_hashmap.keySet().toArray()[random_int_hashmap_size];

        do {
            random_int_hashmap_size = rnd.nextInt(cards_hashmap.size());
            card = (String) cards_hashmap.keySet().toArray()[random_int_hashmap_size];
        } while (cards_hashmap_game_in_progress.containsKey(card));

        int value_of_random_card = cards_hashmap.get(card);
        cards_hashmap_game_in_progress.put(card, value_of_random_card);

        //generating info about value of cards
        String info_value_cards = counter.get_info_value(cards_hashmap_game_in_progress);
        String value_of_cards_in_game_croupier = counter.get_info_value(cards_hashmap_game_in_progress_croupier);

        //have player won or lost
        int info_have_won_or_lost = counter.have_won_player(cards_hashmap_game_in_progress, cards_hashmap_game_in_progress_croupier);

        int amount_of_money = money.getMoney_amount();

        session.setAttribute("map_from_jsp", cards_hashmap_game_in_progress);
        session.setAttribute("cards_hashmap_game_in_progress_croupier", cards_hashmap_game_in_progress_croupier);
        RequestDispatcher requestDispatcher;
        request.setAttribute("cards_hashmap_game_in_progress", cards_hashmap_game_in_progress);
        request.setAttribute("value_of_cards_in_game", info_value_cards);
        request.setAttribute("cards_hashmap_game_in_progress_croupier", cards_hashmap_game_in_progress_croupier);
        request.setAttribute("value_of_cards_in_game_croupier", value_of_cards_in_game_croupier);
        request.setAttribute("amount_of_money", amount_of_money);

        if (info_have_won_or_lost == 4) {
            requestDispatcher = request.getRequestDispatcher("RandomCardStay");
            requestDispatcher.include(request, response);
        } else if (info_have_won_or_lost == 2) {
            requestDispatcher = request.getRequestDispatcher("RandomCardStay");
            requestDispatcher.include(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher("/CardDisplayGameInProgress.jsp");
            requestDispatcher.forward(request, response);
        }

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
