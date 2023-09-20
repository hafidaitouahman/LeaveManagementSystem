package com.leave.backend.Entities;

import java.io.Serializable;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user_quota")
public class UserQuota {
      @EmbeddedId
  private UserQuotaId id = new UserQuotaId();

  @ManyToOne
  @MapsId("userId")
  private User user;

  @ManyToOne
  @MapsId("quotaId")
  private LeaveQuota leaveQuota;

  private int year;


  
  private double quota;
  private double residuel;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
   @Embeddable
  public static class UserQuotaId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long quotaId;
//     public UserQuotaId() {}

//   public UserQuotaId(Long userId, Long quotaId){
//     this.userId = userId;
//     this.quotaId = quotaId;

//   }
}
}
