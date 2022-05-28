/*   Created by IntelliJ IDEA.
 *   Author: Kshitij Varshney (kshitijvarshne1)
 *   Date: 28-May-22
 *   Time: 2:32 PM
 *   File: BitcoinController.java
 */

package com.example.carefi_bitcoin_info.controller;

import com.example.carefi_bitcoin_info.entity.Bitcoin;
import com.example.carefi_bitcoin_info.entity.Response;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.carefi_bitcoin_info.dao.BitcoinDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class BitcoinController {
    @Autowired
    private BitcoinDao bitcoinDao;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @GetMapping("/check")
    public ResponseEntity<Response<String>> check() {
        return new ResponseEntity<>(new Response<>("working",null), HttpStatus.OK);
    }


    @Scheduled(cron = "*/4 * * * * *")
    public void getDataFromApi() throws IOException, JSONException {
        URL url = new URL("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Contents-Type", "application/json");
        httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
        int n = httpURLConnection.getResponseCode();
        String string3 = "";
        if (n == 200) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while ((string3 = bufferedReader.readLine()) != null) {
                stringBuffer.append(string3);
            }
            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            String price = jsonObject.getString("price");
            LocalDateTime now = LocalDateTime.now();
            String time = dtf.format(now);
            bitcoinDao.save(price, time);
        }
    }

    @GetMapping("/getData/{userId}/{pageNo}/{pageSize}")
    public ResponseEntity<Response<List<Bitcoin>>> getData(@PathVariable String userId, @PathVariable int pageNo, @PathVariable int pageSize) {
        if (userId.equals("apk")) {
            return bitcoinDao.getData(pageNo, pageSize);
        }
        ResponseEntity<Response<List<Bitcoin>>> tResponseEntity = new ResponseEntity<>(new Response<>("User id is wrong",null), HttpStatus.BAD_REQUEST);
        return tResponseEntity;
    }


}



