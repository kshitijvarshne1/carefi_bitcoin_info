/*   Created by IntelliJ IDEA.
 *   Author: Kshitij Varshney (kshitijvarshne1)
 *   Date: 28-May-22
 *   Time: 6:01 PM
 *   File: Response.java
 */

package com.example.carefi_bitcoin_info.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T>{
    private String message;
    private T data;

    public Response(String message, T data) {
        this.message = message;
        this.data = data;
    }
}

