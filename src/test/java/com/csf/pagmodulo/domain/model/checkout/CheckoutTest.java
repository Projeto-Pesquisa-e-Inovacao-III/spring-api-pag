package com.csf.pagmodulo.domain.model.checkout;

import com.csf.pagmodulo.domain.checkout.entity.Checkout;
import com.csf.pagmodulo.domain.checkout.enums.CheckoutStatus;
import com.csf.pagmodulo.domain.shared.exceptions.InvalidFieldException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static com.csf.pagmodulo.domain.model.checkout.CheckoutTestBuilder.aCheckout;
import static com.csf.pagmodulo.domain.model.checkout.CheckoutTestBuilder.anExpiredCheckout;
import static org.junit.jupiter.api.Assertions.*;


public class CheckoutTest {

    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(-3);

    @Nested
    class Construction {

        @Test
        void shouldCreateCheckoutWithValidData() {
            Checkout checkout = aCheckout().build();

            assertNotNull(checkout.getId());
            assertEquals(CheckoutStatus.CREATING, checkout.getStatus());
        }

        @Test
        void shouldRejectNullCustomer() {
            assertThrows(InvalidFieldException.class,
                    () -> aCheckout().withCustomer(null).build());
        }

        @Test
        void shouldRejectEmptyItems() {
            assertThrows(InvalidFieldException.class,
                    () -> aCheckout().withNoItems().build());
        }

//        @Test
//        void shouldRejectEmptyPaymentMethods() {
//            assertThrows(InvalidFieldException.class,
//                    () -> aCheckout().withNoPaymentMethods().build());
//        }
//
//        @Test
//        void shouldRejectDuplicatePaymentMethods() {
//            assertThrows(InvalidFieldException.class,
//                    () -> aCheckout()
//                            .withPaymentMethods(
//                                    new PixPaymentMethod(),
//                                    new PixPaymentMethod()
//                            )
//                            .build());
//        }
    }

    @Nested
    class ExpirationBehavior {

        @Test
        void shouldNotBeExpiredWhenExpirationDateIsInFuture() {
            Checkout checkout = aCheckout()
                    .withExpirationDate(OffsetDateTime.now(ZONE_OFFSET).plusHours(1))
                    .build();

            assertFalse(checkout.isExpired());
        }

        @Test
        void shouldBeExpiredWhenExpirationDateIsInPast() {
            Checkout checkout = anExpiredCheckout().build();

            assertTrue(checkout.isExpired());
        }

        @Test
        void shouldBeExpiredWhenExpirationDateIsNow() {
            Checkout checkout = aCheckout()
                    .withExpirationDate(OffsetDateTime.now(ZONE_OFFSET))
                    .build();

            assertTrue(checkout.isExpired());
        }
    }

    @Nested
    class StatusUpdate {

        @Test
        void shouldUpdateStatusWithValidString() {
            Checkout checkout = aCheckout().build();

            checkout.updateStatus("PAID");

            assertEquals(CheckoutStatus.PAID, checkout.getStatus());
        }

        @Test
        void shouldUpdateStatusWithEnum() {
            Checkout checkout = aCheckout().build();

            checkout.updateStatus(CheckoutStatus.PAID);

            assertEquals(CheckoutStatus.PAID, checkout.getStatus());
        }

        @Test
        void shouldRejectNullStatusString() {
            Checkout checkout = aCheckout().build();

            assertThrows(InvalidFieldException.class,
                    () -> checkout.updateStatus((String) null));
        }

        @Test
        void shouldRejectBlankStatusString() {
            Checkout checkout = aCheckout().build();

            assertThrows(InvalidFieldException.class,
                    () -> checkout.updateStatus("   "));
        }

        @Test
        void shouldRejectInvalidStatusString() {
            Checkout checkout = aCheckout().build();

            InvalidFieldException ex = assertThrows(
                    InvalidFieldException.class,
                    () -> checkout.updateStatus("INVALID_STATUS")
            );

            assertTrue(ex.getMessage().contains("INVALID_STATUS"));
            assertTrue(ex.getMessage().contains("desconhecido"));
        }
    }

    @Nested
    class StatusMapping {

        @Test
        void shouldMapValidStatusString() {
            Checkout checkout = aCheckout().build();

            CheckoutStatus status = checkout.mapCheckoutStatus("PAID");

            assertEquals(CheckoutStatus.PAID, status);
        }

        @Test
        void shouldThrowForInvalidStatusString() {
            Checkout checkout = aCheckout().build();

            assertThrows(InvalidFieldException.class,
                    () -> checkout.mapCheckoutStatus("DOES_NOT_EXIST"));
        }
    }
}
