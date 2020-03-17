/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cecropia.udpserver;

import java.io.*;
import java.net.*;
import java.util.Random;

/**
 *
 * @author royquesada
 */
public class Main {
    public static void main(String args[]) throws Exception
      {
         DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String( receivePacket.getData());
                  System.out.println("RECEIVED: " + sentence);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  
                  String capitalizedSentence = sentence.toUpperCase();
                  if (sentence.contains("connect:")){
                      capitalizedSentence="connections:192.168.100.1:192.168.100.2:192.168.100.3";
                  }else if(sentence.contains("sent")){
                      Random rand = new Random();
                      int randInt = rand.nextInt(100);
                      capitalizedSentence = "Hola tu " + randInt;
                  }
                  
                  
                  sendData = capitalizedSentence.getBytes();
                  DatagramPacket sendPacket =
                  new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
               }
      }
    
}
