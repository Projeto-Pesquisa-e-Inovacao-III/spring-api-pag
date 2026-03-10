package com.fillipe.pagmodulo.presentation.pagbank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/webhook/pagbank")
public class PagbankWebhookController {

    private static final Logger log = LoggerFactory.getLogger(PagbankWebhookController.class);

    @PostMapping("/update")
    public ResponseEntity<?> updateCheckout(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/payment-update")
    public ResponseEntity<?> paymentUpdateCheckout(){
        return ResponseEntity.ok().build();
    }
}
