package com.fillipe.pagmodulo.domain.model.checkout;

import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;
import com.fillipe.pagmodulo.domain.checkout.entity.CheckoutStatus;
import com.fillipe.pagmodulo.domain.checkout.valueobject.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static com.fillipe.pagmodulo.domain.model.checkout.CheckoutTestBuilder.aCheckout;
import static com.fillipe.pagmodulo.domain.model.checkout.CheckoutTestBuilder.anExpiredCheckout;
import static org.junit.jupiter.api.Assertions.*;


public class CheckoutTest {

    @Nested
    class Construction {

        @Test
        void shouldCreateCheckoutWithValidData() {
            Checkout checkout = aCheckout().build();

            assertNotNull(checkout.getUuid());
            assertEquals(CheckoutStatus.CREATING, checkout.getStatus());
            assertFalse(checkout.getCustomerModifiable());
        }

        @Test
        void shouldRejectNullCustomer() {
            assertThrows(IllegalStateException.class,
                    () -> aCheckout().withCustomer(null).build());
        }

        @Test
        void shouldRejectEmptyItems() {
            assertThrows(IllegalStateException.class,
                    () -> aCheckout().withNoItems().build());
        }

        @Test
        void shouldRejectEmptyPaymentMethods() {
            assertThrows(IllegalStateException.class,
                    () -> aCheckout().withNoPaymentMethods().build());
        }

        @Test
        void shouldRejectDuplicatePaymentMethods() {
            assertThrows(IllegalStateException.class,
                    () -> aCheckout()
                            .withPaymentMethods(
                                    new PaymentMethod(PaymentType.PIX),
                                    new PaymentMethod(PaymentType.PIX)
                            )
                            .build());
        }
    }

    @Nested
    class ExpirationBehavior {

        @Test
        void shouldNotBeExpiredWhenExpirationDateIsInFuture() {
            Checkout checkout = aCheckout()
                    .withExpirationDate(OffsetDateTime.now(ZoneOffset.of("-03:00")).plusHours(1))
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
                    .withExpirationDate(OffsetDateTime.now(ZoneOffset.of("-03:00")).minusSeconds(1))
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

            assertThrows(IllegalArgumentException.class,
                    () -> checkout.updateStatus((String) null));
        }

        @Test
        void shouldRejectBlankStatusString() {
            Checkout checkout = aCheckout().build();

            assertThrows(IllegalArgumentException.class,
                    () -> checkout.updateStatus("   "));
        }

        @Test
        void shouldRejectInvalidStatusString() {
            Checkout checkout = aCheckout().build();

            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> checkout.updateStatus("INVALID_STATUS")
            );

            assertTrue(ex.getMessage().contains("INVALID_STATUS"));
            assertTrue(ex.getMessage().contains("invalido"));
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

            assertThrows(IllegalArgumentException.class,
                    () -> checkout.mapCheckoutStatus("DOES_NOT_EXIST"));
        }
    }
}
