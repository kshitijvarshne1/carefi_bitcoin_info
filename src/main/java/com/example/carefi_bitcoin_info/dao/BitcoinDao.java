/*   Created by IntelliJ IDEA.
 *   Author: Kshitij Varshney (kshitijvarshne1)
 *   Date: 28-May-22
 *   Time: 2:32 PM
 *   File: BitcoinDao.java
 */

package com.example.carefi_bitcoin_info.dao;

import com.example.carefi_bitcoin_info.entity.Bitcoin;
import com.example.carefi_bitcoin_info.entity.Response;
import com.example.carefi_bitcoin_info.repository.BitcoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BitcoinDao {
    @Autowired
    BitcoinRepository bitcoinRepository;

    public void save(String price, String time) {
        bitcoinRepository.save(new Bitcoin(price, time));
    }

    public ResponseEntity<Response<List<Bitcoin>>> getData(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Bitcoin> page = bitcoinRepository.findAll(pageable);
        return new ResponseEntity<Response<List<Bitcoin>>>(new Response<>("List",page.toList()), HttpStatus.OK);
    }
}

