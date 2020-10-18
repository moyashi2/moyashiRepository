/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.example.bot.spring.echo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@SpringBootApplication
@LineMessageHandler
public class EchoApplication {
    private final Logger log = LoggerFactory.getLogger(EchoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        log.info("event: " + event);
        final String originalMessageText = event.getMessage().getText();
    	if( originalMessageText.contains("もやし野郎")){
    		return new TextMessage("なんだよ、もやし野郎");
    	}else if( originalMessageText.contains("何日？") ){
    		LocalDateTime d = LocalDateTime.now();
    		DateTimeFormatter date01 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒 E曜日");
    		String st01 = date01.format(d);
    		return new TextMessage( st01 + "だよ" );
    	}else if( originalMessageText.contains("ウミガメのスープ") ){
    		Random r = new Random();
    		String[] mis = {"女は男が大好きだった。"+ "\n" + "ある朝、男が倒れていたのを目撃した女は救急車を呼ぶことはなく、男は死んでしまった。何故？" + "\n\n\n" + "答え" + "\n" +"女は少女、男はおじいちゃん。救急車を呼びたいと思ったものの携帯は持っておらず、おじいちゃんの家の電話は黒電話だったため、使い方がわからなかった"
    					,"女は高校に入学してしばらくたつと親にも先生にも友達にも嘘ばかりつくようになった。いったいなぜ？" + "\n\n\n" + "答え" + "\n" + "女は高校に入ってしばらくすると、治療法のない難病に侵されていることが判明した。" + "\n" +"入院後、見舞いに来た友人たちには心配させないようにすぐに元気なるよと嘘をついていた"
    					,"男は仕事で失敗をしてしまい、同じ仕事をしていた仲間に頭を下げた。" + "\n" + "その後同じ仕事をしていたにも関わらず、仲間は一気に出世をし年収も増えた。一体どういうことだろうか？" + "\n\n\n" + "答え" + "\n" + "男の仕事は将棋の棋士で対局中に失敗をし負けてしまい頭を下げた。" + "\n" + "その結果仕事仲間は出世をし段が上がった"
    		};
    		return new TextMessage( "ウミガメのスープ問題" + "\n" + mis[r.nextInt(3)] );
    	}else{
    		return new TextMessage(originalMessageText);
    	}
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
