//package com.zac.batch;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//
//import com.zac.batch.fortest.EmailAddress;
//
//
//public class EmailAddressTest {
//
//	private static final String testEmail = "test@test.com";
//
//	@Rule
//	public ExpectedException thrown = ExpectedException.none();
//
//	@Test
//	public void testShoulReturnValidEmail() {
//		EmailAddress email = new EmailAddress(testEmail);
//		assertThat(email.toString()).isEqualTo(testEmail);
//
//	}
//
//	@Test
//	public void testShoulReturnExceptionForInvalidEmailAddress() {
//		thrown.expect(IllegalArgumentException.class);
//		thrown.expectMessage("Email Address is Invalid !");
//		EmailAddress email = new EmailAddress("test@.com");
//		assertThat(email.toString()).isEqualTo(testEmail);
//
//	}
//
//	@Test
//	public void testShoulReturnExceptionForBlankEmailAddress() {
//		thrown.expect(IllegalArgumentException.class);
//		thrown.expectMessage("Email Address is Invalid !");
//		EmailAddress email = new EmailAddress(" ");
//		assertThat(email.toString()).isEqualTo(testEmail);
//
//	}
//
//	@Test
//	public void testShoulReturnExceptionForNuullEmailAddress() {
//		thrown.expect(IllegalArgumentException.class);
//		thrown.expectMessage("Email Address is Invalid !");
//		EmailAddress email = new EmailAddress(null);
//		assertThat(email.toString()).isEqualTo(testEmail);
//
//	}
//
//}
