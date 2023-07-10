package br.ufscar.dc.dsw.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Locacao")
public class Locacao extends AbstractEntity<Long> {

    @NotNull
    @Column(nullable = false, columnDefinition = "Time")
    private LocalTime hour;

    @NotNull
    @Column(nullable = false, columnDefinition = "Date")
    private LocalDate date;

    @NotNull(message = "{NotNull.locacao.locadora}")
    @ManyToOne
    @JoinColumn(name = "rentalCompany_id")
    private Locadora rentalCompany;

    @NotNull()
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Cliente client;

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Locadora getRentalCompany() {
        return rentalCompany;
    }

    public void setRentalCompany(Locadora rentalCompany) {
        this.rentalCompany = rentalCompany;
    }

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }
}
