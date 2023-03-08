import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorB {
    public static void mandarIntervalos(Socket a, long n, long numeroI, long numeroF) {
        try {
            DataOutputStream dos = new DataOutputStream(a.getOutputStream());
            dos.writeLong(n);
            dos.flush();
            dos.writeLong(numeroI);
            dos.flush();
            dos.writeLong(numeroF);
            dos.flush();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static String recibirRespuesta(Socket a) {
        String mensaje = new String();
        try {
            DataInputStream dis = new DataInputStream(a.getInputStream());
            mensaje = dis.readUTF();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return mensaje;
    }

    public static void main(String[] args) {
        try {
            int puerto = 50001;
            ServerSocket ss = new ServerSocket(puerto);
            // Socket conexion = ss.accept();
            // DataInputStream dis = new DataInputStream(conexion.getInputStream());
            // long numeroCliente = dis.readLong();
            long numeroCliente = 1234567811;
            long k = numeroCliente / 3;
            Socket a1 = new Socket("localhost", 50000);
            Socket a2 = new Socket("localhost", 50000);
            Socket a3 = new Socket("localhost", 50000);
            mandarIntervalos(a1, numeroCliente, 2, k);
            String r1 = recibirRespuesta(a1);
            mandarIntervalos(a2, numeroCliente, k + 1, 2 * k);
            String r2 = recibirRespuesta(a2);
            mandarIntervalos(a3, numeroCliente, (2 * k) + 1, numeroCliente - 1);
            String r3 = recibirRespuesta(a3);
            System.out.println(r1 + r2 + r3);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
