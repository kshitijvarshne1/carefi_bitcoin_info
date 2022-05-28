/*   Created by IntelliJ IDEA.
 *   Author: Kshitij Varshney (kshitijvarshne1)
 *   Date: 28-May-22
 *   Time: 2:28 PM
 *   File: Bitcoin.java
 */

package com.example.carefi_bitcoin_info.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Bitcoin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String price;
    private String timeStamp;

    public Bitcoin(String price, String timeStamp) {
        this.price = price;
        this.timeStamp = timeStamp;
    }

    public Bitcoin() {
    }
}

