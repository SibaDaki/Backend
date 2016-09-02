package com.AttendBackEnd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by User on 2016/08/31.
 */

@Entity
public class Bank  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bank_id;
    private String bank_name;

    public Bank()
    {

    }

    public Bank(Builder builder) {
        this.bank_name = builder.bank_name;
        this.bank_id = builder.bank_id;
    }

    public Long  getBank_id() {
        return bank_id;
    }

    public String getBank_name() {
        return bank_name;
    }


    public static class Builder {
        private String bank_name;

        private Long  bank_id;

        public Builder bank_name(String value) {
            this.bank_name = value;
            return this;
        }

        public Builder bank_id(Long  value) {
            this.bank_id = value;
            return this;
        }

        public Builder copy(Bank value)
        {
            this.bank_name = value.bank_name;
            this.bank_id=value.bank_id;
            return this;
        }

        public Bank build() {
            return new Bank(this);
        }
    }
}
