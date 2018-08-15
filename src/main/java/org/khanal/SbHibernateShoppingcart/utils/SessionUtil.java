package org.khanal.SbHibernateShoppingcart.utils;

import org.khanal.SbHibernateShoppingcart.commands.CartInfoCommand;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
    public static CartInfoCommand getCartInSession(HttpServletRequest request){
        CartInfoCommand cartInfoCommand = (CartInfoCommand)request.getSession().getAttribute("myCart");
        if(cartInfoCommand == null){
            cartInfoCommand = new CartInfoCommand();
            request.getSession().setAttribute("myCart", cartInfoCommand);
        }
        return cartInfoCommand;
    }

    public static void removeCartInSession(HttpServletRequest request){
        request.getSession().removeAttribute("myCart");
    }

    public static void storeLastOrderedCartInSession(HttpServletRequest request, CartInfoCommand cartInfoCommand){
        request.getSession().setAttribute("myCart", cartInfoCommand);
    }

    public static CartInfoCommand getLastOrderedCartInSession(HttpServletRequest request){
        return (CartInfoCommand)request.getSession().getAttribute("lastOrderedCart");
    }
}
