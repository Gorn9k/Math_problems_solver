package sample;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import org.json.JSONObject;

public class Client_ {
    static JSONObject line;

    public void client_go() throws Exception {
        try {
            Socket sock = new Socket("localhost", 13267);
            System.out.println("Connecting...");
            ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
            oos.writeObject(line.toString());
            ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());

            try {
                line = new JSONObject((String)ois.readObject());
            } catch (Throwable var5) {
                line.put("result", "Error");
            }

            oos.close();
            ois.close();
            sock.close();
        } catch (Throwable var6) {
            line.put("result", "Server_error");
        }

    }
}
