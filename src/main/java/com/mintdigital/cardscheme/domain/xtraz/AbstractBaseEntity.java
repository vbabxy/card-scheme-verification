package com.mintdigital.cardscheme.domain.xtraz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;


/**
 * Author : Owolabi Babalola
 * Email *: babs.owolabi@gmail.com
 * date **: November 21, 2016  09:25 AM
 * -------------------------------------------------------------
 */

@MappedSuperclass
public class AbstractBaseEntity implements java.io.Serializable, AbstractEntity<String> {


    @Transient
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseEntity.class);


    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 40)
    protected String id;


    @Version
    private long version = 0;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @Basic(optional = false)
    @Column(name = "created_by", nullable = false, length = 40)
    private String createdBy;

    //    @Basic(optional = false)
    @Column(name = "last_modified_by", length = 40)
    @LastModifiedBy
    private String lastModifiedBy;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }


    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }


    @PreUpdate
    public void abstractPreUpdate() {
        lastModifiedDate = ZonedDateTime.now();
    }

    @PrePersist
    public void abstractPrePersist() {
        LOGGER.debug("about to run abstractPrePersist method");
        createdDate = ZonedDateTime.now();
        lastModifiedDate = ZonedDateTime.now();
        if (AppUtil.INSTANCE.stringIsNullOrEmpty(id)) {
            id = ID.generateUUIDString();
        }
        LOGGER.debug("finished running abstractPrePersist method");
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.version);
        hash = 71 * hash + Objects.hashCode(this.lastModifiedDate);
        hash = 71 * hash + Objects.hashCode(this.createdDate);
        hash = 71 * hash + Objects.hashCode(this.createdBy);
        hash = 71 * hash + Objects.hashCode(this.lastModifiedBy);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractBaseEntity that = (AbstractBaseEntity) o;

        if (version != that.version) return false;
        if (LOGGER != null ? !LOGGER.equals(that.LOGGER) : that.LOGGER != null) return false;
        if (lastModifiedDate != null ? !lastModifiedDate.equals(that.lastModifiedDate) : that.lastModifiedDate != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        return !(lastModifiedBy != null ? !lastModifiedBy.equals(that.lastModifiedBy) : that.lastModifiedBy != null);

    }


    @Override
    public String toString() {
        return "AbstractBaseEntity{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", lastModifiedDate=" + lastModifiedDate +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                '}';
    }

    @Transient
    @Override
    public String getIdx() {
        return id;
    }

    @Override
    public String getPk() {
        return id;
    }

    @Override
    public String getIde() {
        String idd = (id == null) ? "" : id;
        return EncDec.INSTANCE.encryptAndEncode(idd);
    }

    @Override
    public boolean isIdSet() {
        return id != null && !id.isEmpty();
    }
}
