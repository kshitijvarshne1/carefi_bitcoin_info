/*   Created by IntelliJ IDEA.
 *   Author: Kshitij Varshney (kshitijvarshne1)
 *   Date: 28-May-22
 *   Time: 2:31 PM
 *   File: BitcoinRepository.java
 */

package com.example.carefi_bitcoin_info.repository;

import com.example.carefi_bitcoin_info.entity.Bitcoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitcoinRepository extends JpaRepository<Bitcoin, Long> {
}

