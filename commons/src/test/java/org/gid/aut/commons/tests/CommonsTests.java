package org.gid.aut.commons.tests;


import org.gid.aut.common.Mailer;
import org.gid.aut.common.OSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonsTests {

    private final static Logger log = LoggerFactory.getLogger(CommonsTests.class);

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

    @Test(groups = "os")
    public void testOSType(){
        Assert.assertNotNull(OSUtils.getOperatingSystemType());
    }

    @Test(groups = "os")
    public void testOSArchitecture(){
        Assert.assertTrue(OSUtils.is64Architecture());
    }

    @Test(groups = "logger")
    public void testLogger(){
        log.error("error!!!!");
        log.debug("FORMAT {}", 46);
    }
}
