package com.company;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mariuszgromada.math.mxparser.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static JSONObject line;

    public static void main(String[] args) throws Exception {
        ServerSocket servsock = new ServerSocket(13267);
        while (true) {
            System.out.println("Waiting...");
            Socket sock = servsock.accept();
            System.out.println("Accepted connection : " + sock);
            ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
            try {
                line = new JSONObject((String) ois.readObject());
            } catch (Throwable e){
                line = new JSONObject();
                line.put("result", "Error");
                ObjectOutputStream oss = new ObjectOutputStream(sock.getOutputStream());
                oss.writeObject(line.toString());
                continue;
            }
            ObjectOutputStream oss = new ObjectOutputStream(sock.getOutputStream());
            method();
            oss.writeObject(line.toString());
            ois.close();
            oss.close();
            sock.close();
        }
    }

    private static void method() {
        if(line.get("id").equals("1")){
            double[][] A = new double[line.getInt("razmer")][line.getInt("razmer")];
            double[] B = new double[line.getInt("razmer")];
            JSONArray jsonArrayA, jsonArrayB;
            jsonArrayA = (JSONArray) line.get("masA");
            jsonArrayB = (JSONArray) line.get("masB");
            try {
            for (int i = 0; i < line.getInt("razmer"); i++) {
                for (int j = 0; j < line.getInt("razmer"); j++) {
                    A[i][j] = Double.parseDouble(jsonArrayA.getJSONArray(i).get(j).toString());
                }
                B[i] = Double.parseDouble(jsonArrayB.get(i).toString());
            }
            line.put("result", Kramer.kramer(A, B));
            } catch (Throwable e) {line.put("result", "Error");}
        } else if(line.get("id").equals("2")){
            double res = Integral.integral_sredn_pr9m(new Expression((String) line.get("a")).calculate(), new Expression((String) line.get("b")).calculate(),
                    (String) line.get("integral"));
            try {line.put("result", res);}
            catch (Throwable e){line.put("result", "Error");}
        } else if(line.get("id").equals("3")) {
            double res = Nolinear_ur.solve((String) line.get("nolinear_ur"), new Expression((String) line.get("x0")).calculate(), new Expression((String) line.get("tochnost")).calculate());
            if(Nolinear_ur.getIteration() == Nolinear_ur.getConst()){
                line.put("result", "Neshod");
            } else{
                try {line.put("result", res);}
                catch (Throwable e){line.put("result", "Error");}}
        } else if(line.get("id").equals("4")) {
            double[] res = Cub_ur.solveCubic(new Expression((String) line.get("cub_d")).calculate(), new Expression((String) line.get("cub_a")).calculate(), new Expression((String) line.get("cub_b")).calculate(),
                    new Expression((String) line.get("cub_c")).calculate());
            try {line.put("result", res);}
            catch (Throwable e){line.put("result", "Error");}
        }
    }
}