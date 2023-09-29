package com.yq.javabasic;

import java.io.*;
import java.util.Base64;

/**
 * @program: JavaDemoRep
 * @description: serialVersionUID
 * @author: Yuqing
 * @create: 2023-09-29 15:09
 **/
public class SerialVersionUIDTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Product product = new Product();
        product.name = "手机";
        product.price = 20000.0;
        String serializedObj  = SerializationUtility.serialObjectToString(product);
        System.out.println("Serialized Product object to string:");
        System.out.println(serializedObj);

        // serializedObj = "rO0ABXNyABhjb20ueXEuamF2YWJhc2ljLlByb2R1Y3QAAAAAAAAACgIAAkwABG5hbWV0ABJMamF2YS9sYW5nL1N0cmluZztMAAVwcmljZXQAEkxqYXZhL2xhbmcvRG91YmxlO3hwdAAG5omL5py6c3IAEGphdmEubGFuZy5Eb3VibGWAs8JKKWv7BAIAAUQABXZhbHVleHIAEGphdmEubGFuZy5OdW1iZXKGrJUdC5TgiwIAAHhwQNOIAAAAAAA";

        Product deserializedObj  = (Product) SerializationUtility.deSerializeObjectFromString(serializedObj);
        System.out.println(deserializedObj);
    }

}

class Product implements Serializable{
    private static final long serialVersionUID = 10L;

    public String name;
    public Double price;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

class SerializationUtility{

    public static String serialObjectToString(Serializable o) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
        objectOutputStream.writeObject(o);
        objectOutputStream.close();
        return Base64.getEncoder().encodeToString(stream.toByteArray());
    }

    public static Object deSerializeObjectFromString(String s) throws IOException, ClassNotFoundException {

        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

}
