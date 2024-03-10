package homework1;

import java.io.*;

public class FileIO {
    static Data search(String id,String filename){ // 找id，包data
        Data data = null;
        return data;
    }
    static void record(Data data,String filename){/*
        if(search(data.getId(),filename)!=null){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
                while (true) {
                    Data temp = (Data) ois.readObject();
                    if (temp.getId().equals(id)) {
                        data = temp;
                        return data;
                    }
                }
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }*/
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
