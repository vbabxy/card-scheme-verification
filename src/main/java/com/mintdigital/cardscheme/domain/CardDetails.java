package com.mintdigital.cardscheme.domain;


import com.mintdigital.cardscheme.domain.xtraz.AbstractBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="card_details"
        , uniqueConstraints = @UniqueConstraint(columnNames="card_number"))
@Data
@Builder
@AllArgsConstructor
public class CardDetails  extends AbstractBaseEntity implements java.io.Serializable {


    private static final long serialVersionUID = 1L;

    @Column(name="card_number", unique=true, length=20)
    private String cardNumber;


    @Column(name="number_of_hits")
    private Long numberOfHits;


    @Column(name="card_type", length=20)
    private String cardType;


    @Column(name="card_scheme", length=45)
    private String cardScheme;


    @Column(name="bank_name", length=45)
    private String bankName;


    @Column(name="status", length=1)
    private Character status;



    public CardDetails() {
    }


    @Override
    public String toString() {
        return "CardDetails{" +
                "id='" + id + '\'' +
                ", createdDate='" + getCreatedDate() + '\'' +
                '}';
    }


}
