package com.mintdigital.cardscheme.repository;



import com.mintdigital.cardscheme.domain.CardDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


/**
 *	CardDetailsRepository by JCodeGenTool v1.2
 * @Author: Owolabi Babalola
 * @Email: babs.owolabi@cwg-plc.com, owolabi.babalola@cwg-plc.com
 * 
 */
public interface CardDetailsRepository extends JpaRepository<CardDetails, String> {

    Optional<CardDetails> findByCardNumber(String cardNumber);

    @Query("select c from CardDetails c")
    Page<CardDetails> findAllCards(Pageable pageable);
}