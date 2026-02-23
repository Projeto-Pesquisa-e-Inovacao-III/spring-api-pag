package com.fillipe.pagmodulo.presentation.controller;

import com.fillipe.pagmodulo.domain.entity.Checkout;
import com.fillipe.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutUseCase;
import com.fillipe.pagmodulo.application.usecase.CreateCheckout.CreateCheckoutCommand;
import com.fillipe.pagmodulo.domain.valueobject.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestCheckoutController {

    private final CreateCheckoutUseCase useCase;

    public TestCheckoutController(CreateCheckoutUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/checkout")
    public Checkout testCheckout() {

        Item item = new Item(
                1L,
                "Pacote Anual - PRESENCIAL",
                "teste",
                1,
                100
        );

        Phone phone = new Phone("+55", "11", "987654321");

        TaxDocument taxDocument = new TaxDocument("01415069034", TaxDocumentType.CPF);

        Customer customer = new Customer(
                1L,
                "João Silva",
                "joao.silva@example.com",
                taxDocument,
                phone
        );

        PaymentMethod paymentMethod = new PaymentMethod(PaymentType.PIX);

        CreateCheckoutCommand command = new CreateCheckoutCommand(
                LocalDateTime.now().plusHours(1),
                customer,
                List.of(item),
                null,
                null,
                List.of(paymentMethod),
                null,
                "https://google.com",
                null,
                List.of("https://tinkly-catchable-aurea.ngrok-free.dev/checkouts/pag-status"),
                null
        );

        return useCase.execute(command);
    }
}
