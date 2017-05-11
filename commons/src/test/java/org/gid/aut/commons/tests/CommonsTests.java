package org.gid.aut.commons.tests;


import org.gid.aut.common.api.Mailer;
import org.testng.annotations.Test;

public class CommonsTests {

    @Test(groups = "mailer")
    public static void testMailer() {
        String from = Mailer.USER_NAME;
        String pass = Mailer.PASSWORD;
        String[] to = { Mailer.DEFAULT_RECIPIENT }; // list of recipient email addresses
        String subject = "Java send mail example";
        String body = "Welcome to JavaMail!";

        // Will not run as it's excluded from suit.
        Mailer.sendFromGMail(from, pass, to, subject, body);
    }
}
