package com.codingshuttle.razorpay.vault.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "vault_card")
public class VaultCard {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    private String lastFour;

    private String bin;

    private byte[] encryptedPan;

    private byte[] encryptedDek;

    private String cardBrand;

    private String exiryMonth;

}
