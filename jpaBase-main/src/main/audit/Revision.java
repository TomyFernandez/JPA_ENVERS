package config;

import javax.persitence.Entity;
import javax.persistence.GeneratedType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import javax.io.Serializable;

import java.util.Date;


import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import config.CustomRevisionListener;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "REVISION_INFO")
@RevisionEntity(CustomRevisionListener.class)
@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Revision implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="revision_seq")
    @SequenceGenerator(name="revision_seq", sequenceName = "rbac.seq_revision_id")
    @RevisionNumber
    private int id;

    @Column(name="REVISION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @RevisionTimestamp
    private Date date;
}
