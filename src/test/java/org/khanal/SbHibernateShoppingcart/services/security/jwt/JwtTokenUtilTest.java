package org.khanal.SbHibernateShoppingcart.services.security.jwt;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class JwtTokenUtilTest {

    @Test
    public void canTokenBeRefreshed() {
        //isCreatedBeforeChange = true
        boolean value = canTokenBeRefreshed(true, true, false);
        assertEquals(true, value);
        value = canTokenBeRefreshed(true, true, true);
        assertEquals(true, value);
        value = canTokenBeRefreshed(true, false, true);
        assertEquals(true, value);

//        ignoreExpiration=true
        value = canTokenBeRefreshed(false, true, true);
        assertEquals(false, value);
        value = canTokenBeRefreshed(false, true, false);
        assertEquals(false, value);

        // ignoreExpiration = false
        value = canTokenBeRefreshed(false, false, true);
        assertEquals(true, value);
        value = canTokenBeRefreshed(false, false, false);
        assertEquals(false, value);

    }

    public boolean canTokenBeRefreshed(boolean isCreatedBeforePasswordChange, boolean ignoreExpiration, boolean isExpired){
        return isCreatedBeforePasswordChange || (!ignoreExpiration && isExpired);
    }
}