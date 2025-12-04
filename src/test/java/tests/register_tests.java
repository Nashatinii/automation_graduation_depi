package tests;

import org.testng.annotations.Test;

public class register_tests extends test_base {

    @Test
    public void successful_user_registration() {
        home.openRegisterPage();
        register.registerNewUser("Test", "User", "testuser@gmail.com", "0114499de", true);
        register.agreeToPrivacyPolicy();
        register.clickContinue();
        sucseesRegPage.Assert_user_reg_succesful();
        access_newsletter_bage();
        newsletterpage.Assert_user_subs_newslettter();
    }
    @Test
    public void registration_with_existing_email() {
        home.openRegisterPage();
        register.registerNewUser("Test2", "User2", "testuser2@gmail.com", "password123", true);
        register.agreeToPrivacyPolicy();
        register.clickContinue();
        register.Assert_dublicateacc_errormsg();
    }
    @Test
    public void registration_without_accept_policy() {
        home.openRegisterPage();
        register.registerNewUser("Test3", "User3", "testuser3@gmail.com", "password123", true);
        register.clickContinue();
        register.Assert_reg_without_accept_policy();
    }
    @Test
    public void registration_without_subscripe_newsletter() {
        home.openRegisterPage();
        register.registerNewUser("Test4", "User4", "testuser4@gmail.com", "password123", false);
        register.agreeToPrivacyPolicy();
        register.clickContinue();
        access_newsletter_bage();
        newsletterpage.Assert_user_notsubs_newslettter();
    }
    @Test
    public void register_with_invalid_email() {
        home.openRegisterPage();
        register.registerNewUser("Test5", "User5", "invalid-email", "password123",true);
        register.agreeToPrivacyPolicy();
        register.clickContinue();
        register.Assert_invalidacc_errormsg();
    }
    @Test
    public void register_with_empty_fields() {
        home.openRegisterPage();
        register.registerNewUser("", "", "", "",false);
        register.clickContinue();
        register.Assert_emptyfields_errors_msg();
    }
}
