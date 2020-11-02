package com.mintdigital.cardscheme.domain.xtraz;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Author : Owolabi Babalola
 * Email *: babs.owolabi@gmail.com
 * date **: November 21, 2016  09:25 AM
 * -------------------------------------------------------------
 */
public interface AbstractEntity<PK extends Serializable> {


    /**
     * @return the primary key as String
     */
    String getIdx();

    /**
     * @return
     */
    PK getPk();


    String getIde();

    /**
     * Helper method to know whether the primary key is set or not.
     *
     * @return true if the primary key is set, false otherwise
     */
    boolean isIdSet();


    String getId();

    void setId(String Id);

    String getCreatedBy();

    void setCreatedBy(String createdBy);

    ZonedDateTime getCreatedDate();

    void setCreatedDate(ZonedDateTime createdDate);

    String getLastModifiedBy();

    void setLastModifiedBy(String lastModifiedBy);

    ZonedDateTime getLastModifiedDate();

    void setLastModifiedDate(ZonedDateTime lastModifiedDate);

}
