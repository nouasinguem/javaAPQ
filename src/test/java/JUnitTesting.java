import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JUnitTesting {

    @Test
    void authentificationTest1() {//Wrong credentials
        /* authentication returns:
        1 if you're authenticated with right credential and with admin approval,
        2 if your credentials are right, but you request admin approval,
        and 0 if your credentials are wrong */
        bitRepeated rep = new bitRepeated();
        int authen = rep.authentification("", "");

        Assertions.assertEquals(1, authen); //Test failed
    }


    @Test
    void authentificationTest2() {//Valid credentials and no admin approval
        /* authentication returns:
        1 if you're authenticated with right credential and with admin approval,
        2 if your credentials are right, but you request admin approval,
        and 0 if your credentials are wrong */
        bitRepeated rep = new bitRepeated();
        int authen = rep.authentification("01717173667", "Maxime");
        Assertions.assertEquals(1, authen); //Test failed
    }

    @Test
    void authentificationTest3() {//Valid credential and no admin approval
        /* authentication returns:
        1 if you're authenticated with right credential and with admin approval,
        2 if your credentials are right, but you request admin approval,
        and 0 if your credentials are wrong */
        bitRepeated rep = new bitRepeated();
        int authen = rep.authentification("01717173667", "Maxime");

        Assertions.assertEquals(2, authen); //Test passed
    }

    @Test
    void authentificationTest4() {//Valid credential and  admin approval
        /* authentication returns:
        1 if you're authenticated with right credential and with admin approval,
        2 if your credentials are right, but you request admin approval,
        and 0 if your credentials are wrong */

        bitRepeated rep = new bitRepeated();
        int authen = rep.authentification("25", "Maxime");

        Assertions.assertEquals(1, authen); //Test  passed
    }

    //Check Admin Right Testing

    @Test
    void adminRightTest1() {//Invalid username
        /* the checkAdminRight module returns:
         0 if the user doesn't have admin privilege,
         1 if the user has admin privilege */

        bitRepeated rep = new bitRepeated();
        int right = rep.checkAdminRight("nothing");
        Assertions.assertEquals(1, right); //Test failed
    }

    @Test
    void adminRightTest2() {//Invalid username
        /* the checkAdminRight module returns:
         0 if the user doesn't have admin privilege,
         1 if the user has admin privilege */

        bitRepeated rep = new bitRepeated();
        int right = rep.checkAdminRight("0000000000");
        Assertions.assertEquals(0, right); //Test passed
    }

    @Test
    void adminRightTest3() {//Valid username with no admin right
        /* the checkAdminRight module returns:
         0 if the user doesn't have admin privilege,
         1 if the user has admin privilege */

        bitRepeated rep = new bitRepeated();
        int right = rep.checkAdminRight("07366540448");
        Assertions.assertEquals(1, right); //Test failed
    }

    @Test
    void adminRightTest4() {//Valid username with admin right
        /* the checkAdminRight module returns:
         0 if the user doesn't have admin privilege,
         1 if the user has admin privilege */

        bitRepeated rep = new bitRepeated();
        int right = rep.checkAdminRight("25");
        Assertions.assertEquals(1, right); //Test passed
    }

    // Check Existing Product Testing

    @Test
    void checkExistingProductTest1() {// Invalid barcode number
        /* This function checks if a product exists in the database
         and returns true if so and false in not */

        bitRepeated rep = new bitRepeated();
        boolean result = rep.checkExistingProduct(-1);
        Assertions.assertTrue(result); // Test Failed
    }

    @Test
    void checkExistingProductTest2() {// Valid barcode number not in the database
        /* This function checks if a product exists in the database
         and returns true if so and false in not */

        bitRepeated rep = new bitRepeated();
        boolean result = rep.checkExistingProduct(40);
        Assertions.assertTrue(result); // Test Failed
    }

    @Test
    void checkExistingProductTest3() {// Valid barcode number in the database
        /* This function checks if a product exists in the database
         and returns true if so and false in not */

        bitRepeated rep = new bitRepeated();
        boolean result = rep.checkExistingProduct(3);
        Assertions.assertTrue(result); // Test Passed
    }


    //Valid Phone number Testing

    @Test
    void validPhoneNumberTest1(){//Invalid phone number
        /* This function test the phone number and returns:
         true if it's a UK mobile phone number and false if it's not */

        bitRepeated rep = new bitRepeated();
        boolean result = rep.isValidPhoneNumber("Nothing");
        Assertions.assertTrue(result); //Test passed
    }

    @Test
    void validPhoneNumberTest2(){//Valid landline phone number
        /* This function test the phone number and returns:
         true if it's a UK mobile phone number and false if it's not */

        bitRepeated rep = new bitRepeated();
        boolean result = rep.isValidPhoneNumber("01256786999");
        Assertions.assertTrue(result); //Test failed
    }

    @Test
    void validPhoneNumberTest3(){//Valid landline phone number
        /* This function test the phone number and returns:
         true if it's a UK mobile phone number and false if it's not */

        bitRepeated rep = new bitRepeated();
        boolean result = rep.isValidPhoneNumber("07455609778");
        Assertions.assertTrue(result); //Test passed
    }

}